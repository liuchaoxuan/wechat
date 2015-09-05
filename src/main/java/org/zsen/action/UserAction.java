/**  
* @Title: LoginAction.java
* @Package org.zsen.action
* @Description TODO
* @author ZhangSen
* @date 2015年9月4日 下午7:25:48
* @version 
*/ 
package org.zsen.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zsen.user.CONSTANT;
import org.zsen.user.User;
import org.zsen.user.UserManager;

import com.alibaba.fastjson.JSON;

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

	/**
	 * 
	* @Description: 登录
	* @return
	* @author ZhangSen
	* @date 2015年9月5日 下午5:04:52
	 */
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
	
	/**
	 * 
	* @Description: 注册用户
	* @return
	* @author ZhangSen
	* @date 2015年9月5日 下午5:05:06
	 */
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
	/**
	 * 
	* @Description: 获取好友列表
	* @author ZhangSen
	* @date 2015年9月5日 下午5:09:39
	 */
	public void getFriendList()
	{
		User u=(User) getSession().getAttribute(CONSTANT.USER_USER);
		String outstr=null;
		if(u==null)
			outstr=JSON.toJSONString("error");
		else
			outstr=JSON.toJSONString(um.getAllFriends(u.getId()));
				PrintWriter out;
				try {
					out = getWriter();
					out.println(outstr);
					out.flush();
					out.close();	
				} catch (IOException e) {
					e.printStackTrace();
				}
						
		
	}
	
	/**
	 * 
	* @Description: 登出
	* @return
	* @author ZhangSen
	* @date 2015年9月5日 下午5:09:52
	 */
	public String logout()
	{
		getSession().invalidate();
		return CONSTANT.ACTION_LOGIN;
		
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

