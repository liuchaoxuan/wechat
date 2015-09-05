/**  
* @Title: MutiThreadTest.java
* @Package org.zsen
* @Description TODO
* @author ZhangSen
* @date 2015年8月30日 下午11:51:43
* @version 
*/ 
package org.zsen;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zsen.message.DefaultMessageContainer;
import org.zsen.message.TextMessageImpl;

/**
* @ClassName: MutiThreadTest
* @Description: 
* @author ZhangSen
* @date 2015年8月30日 下午11:51:43
*
*/
public class MutiThreadTest implements Runnable{
	static ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
	static DefaultMessageContainer dmc=ctx.getBean(DefaultMessageContainer.class);
	static int x=10;
	public static void main(String[] args) {
		int y=10;
		long start=System.currentTimeMillis();
		for(int i=0;i<y;i++)
		{
			Thread th=new Thread(new MutiThreadTest());
			th.start();
		}
		while(Thread.activeCount()!=1)
		{
			
		}
		long end=System.currentTimeMillis();
		System.out.println(end-start);
	}

	public void run() {
		TextMessageImpl msg=new TextMessageImpl();
		msg.setText(Thread.currentThread().toString());
		
		msg.setMessageTo("BB");
		for(int i=0;i<x;i++)
		{
			msg.setMessageFrom(String.valueOf(i));
			dmc.sendMessage(msg);
		}
		
	}
	
}
