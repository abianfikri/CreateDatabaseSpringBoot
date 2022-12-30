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
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @RequestMapping("/getNama")
    public String getNameById(){
        
        try{
            data = dtcrl.findC2022ws(1);
        }
        catch(Exception error){
            
        }
        
        return data.getName();
    }
    
    
    @RequestMapping("/getBorn")
    public String getBornById(){
        
        try{
            
            data = dtcrl.findC2022ws(1);
            
        }
        catch(Exception error){
            
        }
        
        return "tanggal = "+ data.getBirthdate().toString();
    }
    
    @RequestMapping("/getData")
    public String getdataById(){
        
        try{
            data = dtcrl.findC2022ws(1); 
        }
        catch(Exception error){
            
        }
        
        String result = data.getName() + "<p/>" + data.getBirthdate().toString();
        
        return result;
    }
    
    @RequestMapping("/getData/{id}")
    public String getData(@PathVariable("id") int id){
        data = dtcrl.findC2022ws(id);
        
        String result = data.getName() + "<p/>" + data.getBirthdate().toString();
        
        return result;
        
    }
    
    
    @RequestMapping(value = "/sendData", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
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
}
