/**  
* @Title: LoginAction.java
* @Package org.zsen.action
* @Description TODO
* @author ZhangSen
* @date 2015年9月4日 下午7:25:48
* @version 
*/ 
package org.zsen.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zsen.user.CONSTANT;
import org.zsen.user.User;
import org.zsen.user.UserManager;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

/**
* @ClassName: LoginAction
* @Description: 
* @author ZhangSen
* @date 2015年9月4日 下午7:25:48
*
*/
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction{

	private static final long serialVersionUID = 7516302852211194294L;
	private User user;
	private UserManager um;

	
	public String login()
	{
		User u=um.searchUserByEmail(user.getEmail());
		if(u.getPassword().equals(user.getPassword()))
		{
			getSession().setAttribute(CONSTANT.USER_USER, u);
			return CONSTANT.SUCCESS;			
		}
		
		else
			return CONSTANT.ACTION_LOGIN;
	}
	
	public String register()
	{
		if(CONSTANT.SUCCESS.equals(um.addUser(user)))
		{
			getSession().setAttribute(CONSTANT.USER_USER, um.searchUserByEmail(user.getEmail()));;
			return CONSTANT.SUCCESS;
		}
		else
			return CONSTANT.FAILIED;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public UserManager getUm() {
		return um;
	}
	@Autowired
	public void setUm(UserManager um) {
		this.um = um;
	}

}

