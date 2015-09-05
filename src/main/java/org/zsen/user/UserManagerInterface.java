/**  
* @Title: UserManagerInterface.java
* @Package org.zsen.user
* @Description TODO
* @author ZhangSen
* @date 2015年9月2日 下午9:42:51
* @version 
*/ 
package org.zsen.user;

/**
* @ClassName: UserManagerInterface
* @Description: 
* @author ZhangSen
* @date 2015年9月2日 下午9:42:51
*
*/
public interface UserManagerInterface {

	public String addUser(User u);
	
	public String deleteUser(String u_id);
	
	public String modifyUserInfo(User modifiedUser);
	
	public User searchUserByName(String name);
	
	public User searchUserById(String id);
	
	public User searchUserByEmail(String email);

	public User searchUserByPhoneNum(String phoneNum);
	
	public String modifyPassword(String u_id,String psw);
	
	public String addRelation(UserRelation ur);
	
	public String deleteRelation(String relation_id);
	
	public String modifyRelation(UserRelation modifiedRelation);
	
	public UserRelation searchRelation(String id);
	
	public UserRelation searchRelation(String u_id,String friend_id);
	
}
