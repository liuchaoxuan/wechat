/**  
* @Title: FriendAction.java
* @Package org.zsen.action
* @Description TODO
* @author ZhangSen
* @date 2015年9月5日 下午5:13:48
* @version 
*/ 
package org.zsen.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zsen.user.CONSTANT;
import org.zsen.user.User;
import org.zsen.user.UserManager;
import org.zsen.user.UserRelation;

/**
* @ClassName: FriendAction
* @Description: 
* @author ZhangSen
* @date 2015年9月5日 下午5:13:48
*
*/
@Controller("friendAction")
@Scope("prototype")
public class FriendAction extends BaseAction {
	private static final long serialVersionUID = -1692352052591223178L;
	private String friend_name;
	private UserManager um;
	
	public void addFriend()
	{
		if(StringUtils.isEmpty(friend_name))
		{
			throw new IllegalArgumentException("参数非法");
		}
		else
		{
			String friend_id=um.searchUserByName(friend_name).getId();
			UserRelation ur=new UserRelation();
			User u=(User) getSession().getAttribute(CONSTANT.USER_USER);
			ur.setUser_id(u.getId());
			ur.setFriend_id(friend_id);
			um.addRelation(ur);
			
		}
			
	}

	public String getFriend_name() {
		return friend_name;
	}

	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}

	public UserManager getUm() {
		return um;
	}
	
	@Autowired
	public void setUm(UserManager um) {
		this.um = um;
	}
	
}
