/**  
* @Title: MessageAction.java
* @Package org.zsen.action
* @Description TODO
* @author ZhangSen
* @date 2015年9月5日 下午6:57:47
* @version 
*/ 
package org.zsen.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zsen.message.DefaultMessageContainer;
import org.zsen.message.TextMessageImpl;

/**
* @ClassName: MessageAction
* @Description: 
* @author ZhangSen
* @date 2015年9月5日 下午6:57:47
*
*/
@Controller("messageAction")
@Scope("prototype")
public class MessageAction {

	private DefaultMessageContainer mc;
	private TextMessageImpl msg;

	public void sendMessage()
	{
		mc.sendMessage(msg);
	}
	public DefaultMessageContainer getMc() {
		return mc;
	}

	@Autowired
	public void setMc(DefaultMessageContainer mc) {
		this.mc = mc;
	}
	public TextMessageImpl getMsg() {
		return msg;
	}
	public void setMsg(TextMessageImpl msg) {
		this.msg = msg;
	}
	
}
