/**  
* @Title: Message.java
* @Package org.zsen.message
* @Description TODO
* @author ZhangSen
* @date 2015年8月27日 下午11:19:30
* @version 
*/ 
package org.zsen.message;

import java.util.Date;

/**
* @ClassName: Message
* @Description: 
* @author ZhangSen
* @date 2015年8月27日 下午11:19:30
*
*/
public interface Message {
	public void setMessageFrom(String messageFrom);
	public String getMessageFrom();
	public void setMessageTo(String messageTo);
	public String getMessageTo();
	public void setMessageDate(Date messageDate);
	public Date getMessageDate();
	public void setType(String type);
	public String getType();
	public void setMessageId(String messageId);
	public String getMessageId();
}
