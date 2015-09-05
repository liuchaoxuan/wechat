/**  
* @Title: UserDaoTest.java
* @Package org.zsen
* @Description TODO
* @author ZhangSen
* @date 2015年9月3日 下午2:18:59
* @version 
*/ 
package org.zsen.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zsen.user.User;
import org.zsen.user.UserManagerDao;

/**
* @ClassName: UserDaoTest
* @Description: 
* @author ZhangSen
* @date 2015年9月3日 下午2:18:59
*
*/
public class UserDaoTest {
	

	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		cas c=ctx.getBean(cas.class);
		c.addUser();
	}


	

}
@Component
class cas
{
	
	private UserManagerDao dao;
	public UserManagerDao getDao() {
		return dao;
	}
	@Autowired
	public void setDao(UserManagerDao dao) {
		this.dao = dao;
	}
	@Transactional
	public  void deleteUser()
	{
		if(dao==null)
			throw new IllegalArgumentException("dao is null");
		for(int i=0;i<100;i++)
		{
			User u=dao.getUserByName("kaidi20"+i);
			if(u!=null)
			{
				System.out.println(dao.deleteUser(u.getId()));
				
			}
			else
				System.out.println("error");
		}
	}
		@Transactional
		public  void addUser()
		{
			User u1=new User();
			long s=System.currentTimeMillis();
			for(int i=0;i<5;i++)
			{
			
			u1.setId(UUID.randomUUID().toString());
			u1.setName("kaidi20"+i);
			u1.setPassword("123456");
			u1.setEmail("fishwithwater"+i+"@foxmail.com");
			u1.setPhone_Num("186681286"+i);
			System.out.println(dao.addUser(u1));
			}
			System.out.println(System.currentTimeMillis()-s);
		}
}
