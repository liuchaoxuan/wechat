/**  
* @Title: UserRelation.java
* @Package org.zsen.user
* @Description TODO
* @author ZhangSen
* @date 2015年9月2日 下午9:27:46
* @version 
*/ 
package org.zsen.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* @ClassName: UserRelation
* @Description: 
* @author ZhangSen
* @date 2015年9月2日 下午9:27:46
*
*/
@Entity
@Table(name="User_Relation")
public class UserRelation {
	private String id;
	private String user_id;
	private String friend_id;
	private String  remark_name;
	private boolean careful;
	private Date create_date;
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}
	public String getRemark_name() {
		return remark_name;
	}
	public void setRemark_name(String remark_name) {
		this.remark_name = remark_name;
	}
	public boolean isCareful() {
		return careful;
	}
	public void setCareful(boolean careful) {
		this.careful = careful;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	

}
