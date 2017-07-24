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
        String indate = obj.substring(obj.indexOf("inDate")+10,obj.indexOf("inDate")+20);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        Date date1 =new Date();
        try {
            date = f.parse(indate);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        if (date.after(date1))
                return new WebhookResponse(date1.toString(), "https://www.google.com");
        else
                return new WebhookResponse(indate,"hey");
    }
}
