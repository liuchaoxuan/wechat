/**  
* @Title: MessageProducer.java
* @Package org.zsen.core
* @Description TODO
* @author ZhangSen
* @date 2015年9月12日 下午11:07:23
* @version 
*/ 
package org.zsen.core;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zsen.message.DefaultMessageContainer;
import org.zsen.message.TextMessageImpl;
import org.zsen.user.CONSTANT;
import org.zsen.user.User;
import org.zsen.user.UserManager;

/**
* @ClassName: MessageProducer
* @Description: 
* @author ZhangSen
* @date 2015年9月12日 下午11:07:23
*
*/
@Component
public class MessageProducer {

	private UserManager um;
	private DefaultMessageContainer mc;


	public UserManager getUm() {
		return um;
	}
	
	@Autowired
	public void setUm(UserManager um) {
		this.um = um;
	}
	
	public DefaultMessageContainer getMc() {
		return mc;
	}
	
	@Autowired
	public void setMc(DefaultMessageContainer mc) {
		this.mc = mc;
	}
	/**
	* @Description: TODO
	* @author ZhangSen
	* @date 2015年9月12日 下午11:07:43      
	*/ 
	@Scheduled(cron="0/5 * *  * * ? ")
	public void run() {
		
			User u=um.searchUserByEmail("fishwithwater0@163.com");
			List<User> list=um.getAllFriends(u.getId());
			for(User friend:list)
			{
				TextMessageImpl msg=new TextMessageImpl();
				msg.setMessageDate(new Date());
				msg.setMessageFrom(friend.getId());
				msg.setMessageTo(u.getId());
				msg.setText("我是消息啦啦啦啦！你个傻逼酷酷滴！哼!消息来自用户（昵称）:"+friend.getNick_name()+"  当前时间： "+new Date().toString());
				msg.setType(CONSTANT.MESSAGE_TYPE_USER_TEXT);
				mc.sendMessage(msg);
			}
	
		
	}

}
