package bot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/webhook")
public class BotController {

  
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String obj){
      
        System.out.println(obj);
      
      	String files = null;
        
        String docInfo = obj.substring(obj.indexOf("all_docs")+11,obj.indexOf("\"",obj.indexOf("all_docs")+11));
       
        files = getDocumentURL("docs",docInfo);
          
	if(files != null)
       	    return new WebhookResponse(files,"text");
	else
	    return new WebhookResponse(obj,null);
    }
    
  public static String getDocumentURL(String category, String documentName) {
		String doc_urls = "";
	  	String DOCUMENT_LIST_EXCEL_FILE = "document_list.xlsx";
		
		try {
			FileInputStream fs = new FileInputStream(ResourceUtils.getFile("classpath:"+DOCUMENT_LIST_EXCEL_FILE));
			Workbook wb = new XSSFWorkbook(fs);
			
			Sheet sh = wb.getSheet(category);
			if(sh != null){
				Iterator<Row> it = sh.iterator();
				while(it.hasNext()) {
					Row currentRow = it.next();
					if(currentRow.getCell(0).getStringCellValue().equalsIgnoreCase(documentName)) {
						String file_url = currentRow.getCell(1).getStringCellValue();
						String file_type = currentRow.getCell(2).getStringCellValue();
						
						/**String file_class = "fa fa-file-text-o fa-3x";
						switch (file_type) {
						case "pdf":
							file_class = "fa fa-file-pdf-o fa-3x";
							break;
						case "excel":
							file_class = "fa fa-file-excel-o fa-3x";
							break;
						case "doc":
							file_class = "fa fa-file-word-o fa-3x";
							break;
						case "ppt":
							file_class = "fa fa-file-powerpoint-o fa-3x";
							break;
						default:
							break;
						}*/
						
						String file_link = "<a href=\"" + file_url + "\">" 
										 + documentName +"</a>";
						doc_urls = doc_urls + file_link + "\n";
							
					}
				}
			}

			wb.close();
			fs.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return doc_urls;
	}
    
}
