/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourBeans;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author Niall
 */
@Named(value = "loggerBean")
@SessionScoped
public class LoggerBean implements Serializable{

    @Resource(mappedName = "java:app/MyMsgQueue2")
    private Queue java_appMyMsgQueue;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    private String message;

    /**
     * Get the value of message
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the value of message
     *
     * @param message new value of message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Creates a new instance of LoggingBean
     */
    public LoggerBean() {
    }
    
    public void sendMessage() {
        sendJMSMessageToMyMsgQueue(message);
    }

    private void sendJMSMessageToMyMsgQueue(String messageData) {
        context.createProducer().send(java_appMyMsgQueue, messageData);
    }
    
}
