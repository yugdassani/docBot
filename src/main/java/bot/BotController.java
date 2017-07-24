package bot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/webhook")
public class BotController {

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String obj){
  System.out.println(obj);
        String indate = obj.substring(obj.indexOf("inDate"),obj.indexOf("inDate")+20);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        String d = "2017-07-23";
        Date date1;
        try {
            date = f.parse(indate);
           date1 = f.parse(d);
            
            if (date.after(date1))
                return new WebhookResponse(date1.toString(), "https://www.google.com");
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
           return new WebhookResponse(obj,"hey");
    }
}
