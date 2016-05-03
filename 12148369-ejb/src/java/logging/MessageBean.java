/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Niall
 */

@JMSDestinationDefinition(name = "java:app/MyMsgQueue2", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "MyMsgQueue2")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/MyMsgQueue2"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageBean implements MessageListener {
    
    public MessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("DEBUG: " + message.getBody(String.class));
            
            File file = new File("Log_File.txt");
    		
    		//if file doesnt exists, then create it
    		if(!file.exists()){
                    System.out.println("DEBUG FILE");
    			file.createNewFile();
    		}
    		
    		FileWriter fileWriter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
    	        PrintWriter pw = new PrintWriter(bufferWriter);
    	        pw.println(message.getBody(String.class));
                
    	        bufferWriter.close();
    	        pw.close();
	        
    	}catch(IOException e){
    		e.printStackTrace();
        } catch (JMSException ex) {
            Logger.getLogger(MessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
