/**  
* @Title: TextMessage.java
* @Package org.zsen.message
* @Description TODO
* @author ZhangSen
* @date 2015年8月30日 下午12:17:36
* @version 
*/ 
package org.zsen.message;

/**
* @ClassName: TextMessage
* @Description: 
* @author ZhangSen
* @date 2015年8月30日 下午12:17:36
*
*/
public interface TextMessage extends Message {
	public void setText(String msg);
	public String getText();
}
