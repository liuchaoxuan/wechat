/**  
* @Title: MessageContainerDaoTest.java
* @Package org.zsen
* @Description TODO
* @author ZhangSen
* @date 2015年8月30日 下午3:29:33
* @version 
*/ 
package org.zsen;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zsen.message.DefaultMessageContainer;
import org.zsen.message.TextMessageImpl;

/**
* @ClassName: MessageContainerDaoTest
* @Description: 
* @author ZhangSen
* @date 2015年8月30日 下午3:29:33
*
*/
public class MessageContainerDaoTest {

//	@Test
	public void test() {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		try {
			
			DefaultMessageContainer dmc=ctx.getBean(DefaultMessageContainer.class);
	/*		Message msg=new TextMessageImpl();
			Message ms=new TextMessageImpl();
			ms.setMessageDate(new Date());
			ms.setMessageFrom("ME");
			ms.setMessageTo("U");
			msg.setMessageDate(new Date());
			msg.setMessageFrom("ME");
			msg.setMessageTo("U");
			List<Message> l=new ArrayList<Message>();
			l.add(ms);
			l.add(msg);
			dao.saveMessages(l);*/
			
//			System.out.println(dmc.getLatestTextMessageHistory("ME", "U", 2).toString());
			TextMessageImpl ms=new TextMessageImpl();
			ms.setMessageDate(new Date());
			ms.setMessageFrom("U");
			ms.setMessageTo("ME");
			ms.setText("123");
			dmc.sendMessage(ms);
			for(int i=0;i<10;i++)
			{
				ms.setText(String.valueOf(i));
	
				dmc.sendMessage(ms);
			}
//			System.out.println(dmc.getMessage("ME").toString());
			System.out.println(dmc.getLatestTextMessageHistory("0", "BB", 10).toString());
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ctx.close();
		}
	}

}
