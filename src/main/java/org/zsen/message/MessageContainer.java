/**  
* @Package org.zsen.service
* @Description TODO
* @author ZhangSen
* @date 2015年8月27日 下午11:17:14
* @version 
*/ 
package org.zsen.message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
* @ClassName: MessageContainer
* @Description: 
* @author ZhangSen
* @date 2015年8月27日 下午11:17:14
*
*/
public class MessageContainer implements MessageContainerInterface{

	private HashMap<String, List<Message>> messages=new HashMap<String, List<Message>>(128);
	
	public void sendMessage(String messageTo, Message msg) {
	
			if(StringUtils.isEmpty(messageTo))
			{
				throw new IllegalArgumentException("messageTo参数为空！ "+msg.toString());
			}
			if(messages.containsKey(messageTo))
			{
				synchronized(messages.get(messageTo))
				{
				 if(messages.containsKey(messageTo))
					 addMessage(messageTo, msg);
				 else
					 addMsgByLockingContainer(messageTo, msg);
				}
			}
			else
			{
				addMsgByLockingContainer(messageTo, msg);
			}
	

	}

	public List<Message> getMessage(String messageTo) {
		if(StringUtils.isEmpty(messageTo))
		{
			throw new IllegalArgumentException("messageTo参数为空！");
		}
		if(messages.containsKey(messageTo))
		{
			return messages.get(messageTo);
		}
		else
			return null;
	}

	protected void addMessage(String id, Message msg)
	{
		messages.get(id).add(msg);
	}

	protected void createList(String id, Message msg)
	{
		List<Message> list=new LinkedList<Message>();
		list.add(msg);
		messages.put(id, list);
	}
	protected void addMsgByLockingContainer(String id, Message msg)
	{
		synchronized(messages)
		{
			if(!messages.containsKey(id))
				createList(id, msg);
			else
				addMessage(id, msg);
		}
	}

	public void sendMessage(Message msg) {
		sendMessage(msg.getMessageTo(),msg);
		
	}
	protected void delete(String messageTo)
	{
		if(StringUtils.isEmpty(messageTo))
		{
			throw new IllegalArgumentException("messageTo参数为空！");
		}
		if(messages.containsKey(messageTo))
		{
			synchronized(messages.get(messageTo))
			{
				if(messages.containsKey(messageTo))
					messages.remove(messageTo);
			}
		}

	
	}
	protected boolean containsMessage(String messageTo)
	{
		return messages.containsKey(messageTo);
	}

}
