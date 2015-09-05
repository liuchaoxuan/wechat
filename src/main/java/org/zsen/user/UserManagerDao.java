/**  
* @Title: UserManagerDao.java
* @Package org.zsen.user
* @Description TODO
* @author ZhangSen
* @date 2015年9月2日 下午10:11:19
* @version 
*/
package org.zsen.user;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserManagerDao
 * @Description:
 * @author ZhangSen
 * @date 2015年9月2日 下午10:11:19
 *
 */
@Repository
public class UserManagerDao {
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @Description: 得到一个Session
	 * @return Session
	 * @author ZhangSen
	 * @date 2015年9月2日 下午11:05:51
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 
	 * @Description: 从数据库得到一个User
	 * @param user
	 *            id
	 * @return User
	 * @author ZhangSen
	 * @date 2015年9月2日 下午11:05:05
	 */
	public User getUserById(String id) {
		return (User) getSession().get(User.class, id);
	}

	/**
	 * 
	 * @Description: 通过email查找User
	 * @param email
	 * @return User
	 * @author ZhangSen
	 * @date 2015年9月2日 下午11:35:10
	 */
	public User getUserByEmail(String email) {
		Criteria c = getSession().createCriteria(User.class);
		c.add(Restrictions.eq("email", email)).setReadOnly(true);
		@SuppressWarnings("unchecked")
		List<User> l = c.list();
		if (l.size() == 1)
			return l.get(0);
		else
			return null;
	}

	/**
	 * 
	 * @Description: 通过phoneNum查找User
	 * @param phoneNum
	 * @return
	 * @author ZhangSen
	 * @date 2015年9月2日 下午11:37:15
	 */
	public User getUserByPhoneNum(String phoneNum) {
		Criteria c = getSession().createCriteria(User.class);
		c.add(Restrictions.eq("phone_Num", phoneNum)).setReadOnly(true);
		@SuppressWarnings("unchecked")
		List<User> l = c.list();
		if (l.size() == 1)
			return l.get(0);
		else
			return null;
	}
	public User getUserByName(String name)
	{
		Criteria c = getSession().createCriteria(User.class);
		c.add(Restrictions.eq("name", name)).setReadOnly(true);
		@SuppressWarnings("unchecked")
		List<User> l = c.list();
		if (l.size() == 1)
			return l.get(0);
		else
			return null;
	}

	/**
	 * 
	 * @Description: 对一个User进行持久化
	 * @param User
	 * @author ZhangSen
	 * @date 2015年9月2日 下午11:00:41
	 * @return 操作结果
	 */
	public String addUser(User u) {
		String sql = "from User as u";
		sql += " where u.name='" + u.getName() + "' or u.email='" + u.getEmail() + "' or u.phone_Num='"
				+ u.getPhone_Num() + "' ";
		Query q = getSession().createQuery(sql);
		q.setReadOnly(true);
		try {
			if (q.list().isEmpty()) {
				getSession().save(u);
				return CONSTANT.SUCCESS;
			} else
				return CONSTANT.USER_FIELD_OCCUPIED;
		} catch (HibernateException e) {
			e.printStackTrace();
			return CONSTANT.FAILIED;
		}

	}

	/**
	 * 
	 * @Description: 根据User id删除一个用户
	 * @param id
	 * @return 操作结果
	 * @author ZhangSen
	 * @date 2015年9月2日 下午11:07:13
	 */
	public String deleteUser(String id) {
		try {
			Session s = getSession();
			s.delete(s.get(User.class, id));
		} catch (Exception e) {
			e.printStackTrace();
			return CONSTANT.FAILIED;
		}
		return CONSTANT.SUCCESS;
	}

	/**
	 * 
	 * @Description: 修改一个用户
	 * @param modifiedUser
	 * @return 操作结果
	 * @author ZhangSen
	 * @date 2015年9月2日 下午11:10:32
	 */
	public String modifyUser(User modifiedUser) {
		try {
			Session s = getSession();
			if (s.get(User.class, modifiedUser.getId()) == null)
				return CONSTANT.USER_NOTFOUND + " user_id: " + modifiedUser.getId();
			else
				s.saveOrUpdate(modifiedUser);
		} catch (Exception e) {
			e.printStackTrace();
			return CONSTANT.FAILIED;
		}
		return CONSTANT.SUCCESS;
	}
	
	/**
	 * 
	* @Description: 修改User一个字段
	* @param id： user id
	* @param field ：字段名
	* @param value ： 字段值
	* @return 操作结果
	* @author ZhangSen
	* @date 2015年9月3日 下午4:34:53
	 */
	
	public String modifyUser(String id,String field,String value)
	{
		String hql="update User as u set u."+field+"='"+value+"' where u.id='"+id+"'";
			Query q=getSession().createQuery(hql);
			if(q.executeUpdate()==1)
				return CONSTANT.SUCCESS;
			else 
				return CONSTANT.USER_NOTFOUND;			
	}
	/**
	 * 
	* @Description: 修改User birthday
	* @param id user id
	* @param date birthday
	* @return 操作结果
	* @author ZhangSen
	* @date 2015年9月3日 下午4:55:19
	 */
	public String modifyUserBirthday(String id,Date date)
	{
		String hql="update User set birthday=:date where id=:id";
		Query q=getSession().createQuery(hql);
		q.setDate("date", date);
		q.setString("id", id);
//		q.setDate(0, date);
//		q.setString(1,id);
		if(q.executeUpdate()==1)
			return CONSTANT.SUCCESS;
		else
			return CONSTANT.FAILIED;
	}

	/**
	 * 
	 * @Description: 检查用户名是否被占用
	 * @param name
	 * @return boolean,true代表未被占用
	 * @author ZhangSen
	 * @date 2015年9月2日 下午11:20:49
	 */
	public boolean checkNameIsEmpty(String name) {
		Session s = getSession();
		Criteria c = s.createCriteria(User.class);
		c.add(Restrictions.eq("name", name)).setReadOnly(true);
		if (c.list().isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @Description: 检查邮箱是否被占用
	 * @param email
	 * @return boolean,true代表未被占用
	 * @author ZhangSen
	 * @date 2015年9月2日 下午11:22:32
	 */
	public boolean checkEmailIsEmpty(String email) {
		Session s = getSession();
		Criteria c = s.createCriteria(User.class);
		c.add(Restrictions.eq("email", email)).setReadOnly(true);
		if (c.list().isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @Description: 检查手机号是否被占用
	 * @param email
	 * @return boolean,true代表未被占用
	 * @author ZhangSen
	 * @date 2015年9月2日 下午11:22:32
	 */
	public boolean checkPhoneNumIsEmpty(String phoneNum) {
		Session s = getSession();
		Criteria c = s.createCriteria(User.class);
		c.add(Restrictions.eq("phoneNum", phoneNum)).setReadOnly(true);
		if (c.list().isEmpty())
			return true;
		else
			return false;
	}

	public String addRelation(UserRelation ur) {
		try {
			getSession().save(ur);
			return CONSTANT.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CONSTANT.FAILIED;
		}
	}

	public String deleteRelation(UserRelation ur) {
		try {
			getSession().delete(ur);
			return CONSTANT.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CONSTANT.FAILIED;
		}
	}
	/**
	 * 
	* @Description: 修改UserRelation，使用一个完整的UserRelation对象
	* 更新数据库中的对象，慎用，必须保证对象数据的完整性
	* @param modifiedur 完整的UserRelation对象
	* @return 操作结果
	* @author ZhangSen
	* @date 2015年9月3日 下午4:21:36
	 */
	public String modifyRelation(UserRelation modifiedur) {
		Session s = getSession();
		Criteria c = s.createCriteria(UserRelation.class);
		c.add(Restrictions.eq("id", modifiedur.getId())).setReadOnly(true);
		if (!c.list().isEmpty()) {
			s.saveOrUpdate(modifiedur);
			return CONSTANT.SUCCESS;
		}

		else
			return CONSTANT.RELATION_NOTFOUND;
	}
	
	/**
	 * 
	* @Description: 修改Realtion字段
	* @param id UserRelation id
	* @param field 字段名
	* @param value 字段值
	* @return 操作结果
	* @author ZhangSen
	* @date 2015年9月3日 下午4:20:39
	 */
	public String modifyRelation(String id,String field,String value)
	{
		try {
			String hql="update UserRelation as ur set ur."+field+" ='"+value+"' where id='"+id+"' ";
			Query q=getSession().createQuery(hql);
			q.executeUpdate();
			return CONSTANT.SUCCESS;
		} catch (HibernateException e) {
			e.printStackTrace();
			return CONSTANT.FAILIED;
		}
	}

	public UserRelation getRelation(String user_id, String friend_id) {
		Criteria c = getSession().createCriteria(UserRelation.class);
		c.add(Restrictions.eq("user_id", user_id)).add(Restrictions.eq("friend_id", friend_id)).setReadOnly(true);
		@SuppressWarnings("unchecked")
		List<UserRelation> l = c.list();
		if (l.size() == 1)
			return l.get(0);
		else
			return null;
	}
	public UserRelation getRelation(String id)
	{
		Criteria c=getSession().createCriteria(UserRelation.class);
		c.add(Restrictions.eq("id", id))
		.setReadOnly(true);
		@SuppressWarnings("unchecked")
		List<UserRelation> l=c.list();
		if(l.isEmpty())
			return null;
		else
			return l.get(0);
	}

	/**
	 * 
	 * @Description: 
	 * @param user_id
	 *            user id
	 * @param number
	 *            返回关系最多的数量，0代表不限
	 * @param orderBy
	 *            排序规则
	 * @param isAsc
	 *            是否升序
	 * @return List<UserRelation>
	 * @author ZhangSen
	 * @date 2015年9月3日 上午12:06:45
	 */
	@SuppressWarnings("unchecked")
	public List<UserRelation> getFriends(String user_id, int number, String orderBy, boolean isAsc) {
		Criteria c = getSession().createCriteria(UserRelation.class);
		c.add(Restrictions.eq("user_id", user_id)).setReadOnly(true);
		if (number != 0)
			c.setMaxResults(number);
		if (isAsc)
			c.addOrder(Order.asc(orderBy));
		else
			c.addOrder(Order.desc(orderBy));
		return c.list();

	}
}
