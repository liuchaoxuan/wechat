/**  
* @Title: MessageAction.java
* @Package org.zsen.action
* @Description TODO
* @author ZhangSen
* @date 2015年9月5日 下午6:57:47
* @version 
*/ 
package org.zsen.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zsen.message.DefaultMessageContainer;
import org.zsen.message.Message;
import org.zsen.message.TextMessageImpl;
import org.zsen.user.CONSTANT;
import org.zsen.user.User;

import com.alibaba.fastjson.JSON;

/**
* @ClassName: MessageAction
* @Description: 
* @author ZhangSen
* @date 2015年9月5日 下午6:57:47
*
*/
@Controller("messageAction")
@Scope("prototype")
public class MessageAction extends BaseAction{

	private static final long serialVersionUID = 816078129077987088L;
	private DefaultMessageContainer mc;
	private String text;
	private String to;

	
	public void send()
	{

		TextMessageImpl m=new TextMessageImpl();
		User u=(User)getSession().getAttribute(CONSTANT.USER_USER);
		m.setMessageFrom(u.getId());
		m.setMessageTo(to);
		m.setText(text);
		m.setType(CONSTANT.MESSAGE_TYPE_USER_TEXT);
		mc.sendMessage(m);
	}
	
	public void getMessage()
	{
		User u=(User)getSession().getAttribute(CONSTANT.USER_USER);
		List<Message> list=mc.getMessage(u.getId());
		printOut(JSON.toJSONString(list));
	}
	
	public DefaultMessageContainer getMc() {
		return mc;
	}
	@Autowired
	public void setMc(DefaultMessageContainer mc) {
		this.mc = mc;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
