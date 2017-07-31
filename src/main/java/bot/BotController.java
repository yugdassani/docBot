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
      
      List<String> files = new ArrayList<String>();
        
        String finFile = obj.substring(obj.indexOf("finFile")+10,obj.indexOf("\"",obj.indexOf("finFile")+10));
        String pmFile = obj.substring(obj.indexOf("pmFile")+9,obj.indexOf("\"",obj.indexOf("pmFile")+9));
        String eFile = obj.substring(obj.indexOf("file")+7,obj.indexOf("\"",obj.indexOf("file")+7));
        
        if(finFile.length()>0)
          files = getDocumentURL("docs",finFile);
      
        else if(pmFile.length()>0)
          files = getDocumentURL("docs",pmFile);
        
        else if(eFile.length()>0)
          files = getDocumentURL("docs",eFile);
          
        return new WebhookResponse(files.toString(),"text");
    }
    
  public static List<String> getDocumentURL(String category, String documentName) {
		List<String> doc_urls = new ArrayList<String>();
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
						
						String file_link = "<a href='" + file_url + " download>" 
										 + documentName +"</a>";
						doc_urls.add(file_link);
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
