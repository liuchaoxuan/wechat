/**  
* @Title: MessageContainerInterface.java
* @Package org.zsen.message
* @Description TODO
* @author ZhangSen
* @date 2015年8月27日 下午11:19:06
* @version 
*/ 
package org.zsen.message;

import java.util.List;

/**
* @ClassName: MessageContainerInterface
* @Description: 
* @author ZhangSen
* @date 2015年8月27日 下午11:19:06
*
*/
public interface MessageContainerInterface {
	
	public void sendMessage(String messageTo,Message msg);
	public  List<Message> getMessage(String messageTo);
	public void sendMessage(Message msg);

}
