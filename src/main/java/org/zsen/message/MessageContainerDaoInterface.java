/**  
* @Title: MessageContainerDaoInterface.java
* @Package org.zsen.message
* @Description TODO
* @author ZhangSen
* @date 2015年8月30日 下午12:22:20
* @version 
*/ 
package org.zsen.message;

import java.util.List;

/**
* @ClassName: MessageContainerDaoInterface
* @Description: 
* @author ZhangSen
* @date 2015年8月30日 下午12:22:20
*
*/
public interface MessageContainerDaoInterface {
	public void saveMessage(Message msg);
	public void saveMessages(List<Message> list);
	public List<TextMessageImpl> getTextMessages(String from,String to,int number);
	public List<TextMessageImpl> getTextMessages(String from,String to,String startDate,String endDate);
}
