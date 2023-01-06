/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ws.c.mantan;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Abian
 */
@Controller
@ResponseBody
@CrossOrigin
public class myController {
    
    C2022ws data = new C2022ws();
    C2022wsJpaController dtcrl = new C2022wsJpaController();
    
    @RequestMapping("/GET")
    public String getdataById(){
        
        List<C2022ws> datas = new ArrayList<>();
        
        try{
            datas = dtcrl.findC2022wsEntities(); 
        }
        catch(Exception error){
            
        }
        
        return datas.toString();
    }
    
    @RequestMapping("/getData/{id}")
    public String getData(@PathVariable("id") int id){
        data = dtcrl.findC2022ws(id);
        
        String result = data.getName() + "<p/>" + data.getBirthdate().toString();
        
        return result;
        
    }
    
    
    @RequestMapping(value = "/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> kiriman) throws Exception{
        String json_receive = kiriman.getBody();
        String message = "no action";
        
        
        ObjectMapper mapper = new ObjectMapper();
        C2022ws data = new C2022ws();
        
        data= mapper.readValue(json_receive, C2022ws.class);
        dtcrl.create(data);
        message = data.getName() + " Saved";
        
        
        return message;
    }
    
    @RequestMapping(value = "/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String json_receive = kiriman.getBody();
        String message = "no action";
        
        
        ObjectMapper mapper = new ObjectMapper();
        C2022ws data = new C2022ws();
        
        data= mapper.readValue(json_receive, C2022ws.class);
        dtcrl.edit(data);
        message = data.getName() + " Update";
        
        
        return message;
    }
    
    @RequestMapping(value = "/DEL/{id}", method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(@RequestParam("id") int id) throws Exception{
        
        String message = "no action";
        dtcrl.destroy(id);
        message = id + " delete";
        
        return message;
    }
    
}
