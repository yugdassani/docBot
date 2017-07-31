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
        
        String finFile = obj.substring(obj.indexOf("finFile")+10,obj.indexOf("\"",obj.indexOf("finFile")+10));
        
        return new WebhookResponse(finFile,"text");
    }
    
    
}
