/**  
* @Title: MessageContainerTest.java
* @Package org.zsen.user
* @Description TODO
* @author ZhangSen
* @date 2015年9月4日 下午3:12:27
* @version 
*/ 
package org.zsen.user;

import java.sql.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
* @ClassName: MessageContainerTest
* @Description: 
* @author ZhangSen
* @date 2015年9月4日 下午3:12:27
*
*/
public class UserManagerTest {
	ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
	UserManager um=ctx.getBean(UserManager.class);
	long start;
	@Before
	public void start()
	{
		start=System.currentTimeMillis();
	}
	@After
	public void end()
	{
		System.out.println(System.currentTimeMillis()-start);
	}
	
	public void testModifyRelation()
	{
		
	}
//	@Test
	public void testAddRelation()
	{
		UserRelation ur=new UserRelation();
		for(int i=0;i<50;i++)
		{
			ur.setId(UUID.randomUUID().toString());
			ur.setUser_id(um.searchUserByName("kaidi"+i).getId());
			ur.setFriend_id(um.searchUserByName("kaidi"+(i+50)).getId());
			um.addRelation(ur);
		}
	}
	
//	@Test
	public void testModifyUser()
	{
		User u=new User();
		u.setId("ef5041ea-6282-4f70-ac5c-2d667acc099c");
		u.setCity("Hz");
		u.setBirthday(new java.sql.Date(2015, 10, 1));
		u.setSex(User.SEX.男.toString());
		u.setNick_name("bibi");
		u.setReal_Name("大痘痘");
		u.setFavorites("吃喝玩乐");
		um.modifyUserInfo(u);
	}
	
//	@Test
	public void testAddUser() {
		User u=new User();
		for(int i=0;i<100;i++)
		{
		u.setId(UUID.randomUUID().toString());
		u.setName("kaidi"+i);
		u.setPassword("123456");
		u.setEmail("fishwithwater"+i+"@163.com");
		u.setPhone_Num("186"+i);
		System.out.println(um.addUser(u));
		}
	}
	
//	@Test
	public void testDeleteUser()
	{
		for(int i=0;i<100;i++)
		um.deleteUser(um.searchUserByName("kaidi"+i).getId());
	}


}
