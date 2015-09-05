/**  
* @Title: MessageContainerDaoImpl.java
* @Package org.zsen.message
* @Description TODO
* @author ZhangSen
* @date 2015年8月30日 下午1:57:46
* @version 
*/ 
package org.zsen.message;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @ClassName: MessageContainerDaoImpl
* @Description: 
* @author ZhangSen
* @date 2015年8月30日 下午1:57:46
*
*/
@Component
public class MessageContainerDaoImpl implements MessageContainerDaoInterface {

	
	@Resource
	private SessionFactory sessionFactory;
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	public void saveMessage(Message msg) {
		
		if(StringUtils.isEmpty(msg.getMessageFrom())||StringUtils.isEmpty(msg.getMessageTo()))
		{
			throw new IllegalArgumentException("传入的Message中From或To参数为空！ From:"+msg.getMessageFrom()+" ,TO:"+msg.getMessageTo());
		}
		if(msg instanceof TextMessageImpl)
			getSession().save((TextMessageImpl)msg);
			
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveMessages(List<Message> list) {

		for(Message msg:list)
		{
			saveMessage(msg);
		}
	}
	@SuppressWarnings("unchecked")
	public List<TextMessageImpl> getTextMessages(String from,String to, int number) {

		Criteria c=getSession().createCriteria(TextMessageImpl.class);
		c.addOrder(Order.desc("messageDate"))
		.add(Restrictions.eq("messageFrom", from))
		.add(Restrictions.eq("messageTo", to))
		.setMaxResults(number)
		.setReadOnly(true);	
		return c.list();
	}
	@SuppressWarnings("unchecked")
	public List<TextMessageImpl> getTextMessages(String from,String to, String startDate, String endDate) {
		Criteria c=getSession().createCriteria(TextMessageImpl.class);
		c.add(Restrictions.eq("messageFrom", from))
		.add(Restrictions.eq("messageTo", to))
		.add(Restrictions.gt("messageDate", Date.valueOf(startDate)))
		.add(Restrictions.le("messageDate",Date.valueOf(endDate)))
		.addOrder(Order.desc("messageDate"));
		return c.list();
	}

	public Session openSession()
	{
		return sessionFactory.openSession();
	}
	
	

}
