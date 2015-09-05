/**  
* @Title: User.java
* @Package org.zsen.user
* @Description TODO
* @author ZhangSen
* @date 2015年9月2日 下午9:21:08
* @version 
*/ 
package org.zsen.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
* @ClassName: User
* @Description: 
* @author ZhangSen
* @date 2015年9月2日 下午9:21:08
*
*/
@Entity
@Table(name="Users",uniqueConstraints={@UniqueConstraint(columnNames={"name","email","phone_Num"})})
public class User implements Serializable{
	private static final long serialVersionUID = -8429601850281625284L;

	public static enum SEX{
		男,
		女
	}
	private String id;
	private String name;
	private String password;
	private String email;
	private String sex;
	private String nick_name;
	private Date birthday;
	private String phone_Num;
	private String favorites;
	private String real_Name;
	private String city;
	private Date create_date;
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone_Num() {
		return phone_Num;
	}
	public void setPhone_Num(String phone_Num) {
		this.phone_Num = phone_Num;
	}
	public String getFavorites() {
		return favorites;
	}
	public void setFavorites(String favorites) {
		this.favorites = favorites;
	}
	public String getReal_Name() {
		return real_Name;
	}
	public void setReal_Name(String real_Name) {
		this.real_Name = real_Name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
