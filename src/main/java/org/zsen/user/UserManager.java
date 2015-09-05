/**  
* @Title: UserManager.java
* @Package org.zsen.user
* @Description TODO
* @author ZhangSen
* @date 2015年9月2日 下午10:10:51
* @version 
*/
package org.zsen.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: UserManager
 * @Description:
 * @author ZhangSen
 * @date 2015年9月2日 下午10:10:51
 *
 */
@Service
public class UserManager implements UserManagerInterface {

	public UserManagerDao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(UserManagerDao dao) {
		this.dao = dao;
	}

	private UserManagerDao dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public String addUser(User u) {
		if(StringUtils.isEmpty(u.getId()))
		u.setId(UUID.randomUUID().toString());
		if(u.getCreate_date()==null)
			u.setCreate_date(new java.util.Date());
		return getDao().addUser(u);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String deleteUser(String u_id) {

		return getDao().deleteUser(u_id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String modifyUserInfo(User modifiedUser) {
		
		String id=modifiedUser.getId();
		User u = getDao().getUserById(id);
		if (u == null)
			throw new IllegalArgumentException(CONSTANT.USER_NOTFOUND);
		
		if (modifiedUser.getBirthday() != null)
			getDao().modifyUserBirthday(id, (Date)modifiedUser.getBirthday());
		if (!StringUtils.isEmpty(modifiedUser.getCity()))
			getDao().modifyUser(id, "city", modifiedUser.getCity());
		if (!StringUtils.isEmpty(modifiedUser.getFavorites()))
			getDao().modifyUser(id, "favorites", modifiedUser.getFavorites());
		if (!StringUtils.isEmpty(modifiedUser.getNick_name()))
			getDao().modifyUser(id, "nick_name", modifiedUser.getNick_name());
		if (!StringUtils.isEmpty(modifiedUser.getReal_Name()))
				getDao().modifyUser(id, "real_Name", modifiedUser.getReal_Name());
		if(!StringUtils.isEmpty(modifiedUser.getSex()))
			getDao().modifyUser(id, "sex", modifiedUser.getSex());
		return CONSTANT.SUCCESS;
	}

	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public User searchUserById(String id) {
		
		return getDao().getUserById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)	
	public User searchUserByName(String name)
	{
		return getDao().getUserByName(name);
	}
	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public User searchUserByEmail(String email) {
		
		return getDao().getUserByEmail(email);
	}

	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public User searchUserByPhoneNum(String phoneNum) {
		
		return getDao().getUserByPhoneNum(phoneNum);
	}
	
	/**
	 * 
	* @Description: 修改User password
	* @param u_id User id
	* @param psw	password
	* @return 操作结果
	* @author ZhangSen
	* @date 2015年9月3日 下午4:37:17
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public String modifyPassword(String u_id, String psw) 
	{
		return getDao().modifyUser(u_id, "password", psw);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public String addRelation(UserRelation ur) {
		if(StringUtils.isEmpty(ur.getId()))
			ur.setId(UUID.randomUUID().toString());
		if(ur.getCreate_date()==null)
			ur.setCreate_date(new java.util.Date());
		
		if(getDao().getRelation(ur.getUser_id(), ur.getFriend_id())!=null)
			return CONSTANT.FAILIED;
		return getDao().addRelation(ur);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String deleteRelation(String relation_id) {
		UserRelation ur=getDao().getRelation(relation_id);
		return  getDao().deleteRelation(ur);
	}
	
	/**
	 * 
	* @Description: TODO
	* @param modifiedRelation
	* @return
	* @author ZhangSen
	* @date 2015年9月3日 下午4:24:30
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public String modifyRelation(UserRelation modifiedRelation) {
		UserRelation ur=getDao().getRelation(modifiedRelation.getId());
		if(ur==null)
			throw new IllegalArgumentException(CONSTANT.RELATION_NOTFOUND);
		if(!StringUtils.isEmpty(modifiedRelation.getRemark_name()))
			getDao().modifyRelation(modifiedRelation.getId(), "Remark_name", modifiedRelation.getRemark_name());
		return CONSTANT.SUCCESS;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public UserRelation searchRelation(String id) {
		
		return getDao().getRelation(id);
	}

	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public UserRelation searchRelation(String u_id, String friend_id) {
		
		return getDao().getRelation(u_id, friend_id);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<User> getAllFriends(String user_id)
	{
		List<UserRelation> list=getDao().getFriends(user_id, 0, "create_date", false);
		List<User> l=new ArrayList<User>(list.size());
		for(UserRelation ur:list)
		l.add(getDao().getUserById(ur.getFriend_id()));
		return l;
		
	}

}
