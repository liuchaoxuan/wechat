/**  
* @Title: DefaultMessageContainer.java
* @Package org.zsen.message
* @Description TODO
* @author ZhangSen
* @date 2015年8月30日 下午9:26:40
* @version 
*/ 
package org.zsen.message;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
* @ClassName: DefaultMessageContainer
* @Description: 
* @author ZhangSen
* @date 2015年8月30日 下午9:26:40
*
*/
@Component
public class DefaultMessageContainer extends MessageContainer {

	@Autowired
	private MessageContainerDaoImpl dao;
	@Override
	@Transactional
	public void sendMessage(String messageTo, Message msg) {
		// TODO Auto-generated method stub
		setPrimaryKey(msg);
		super.sendMessage(messageTo, msg);
		dao.saveMessage(msg);
		
	}

	@Override
	@Transactional
	public void sendMessage(Message msg) {
		// TODO Auto-generated method stub
		setPrimaryKey(msg);
		super.sendMessage(msg);
	}

	@Override
	public List<Message> getMessage(String messageTo) {
		List<Message> msgs=null;
		try {
			msgs = super.getMessage(messageTo);
			super.delete(messageTo);
			return msgs;
		} catch (Exception e) {
			e.printStackTrace();
			if(!super.containsMessage(messageTo))
			{
				if(msgs!=null)
				for(Message msg:msgs)
				super.sendMessage(msg);
			}
			return null;
		}
		
	}
	
	@Transactional(readOnly=true)
	public List<TextMessageImpl> getLatestTextMessageHistory(String messageFrom,String messageTo,int number)
	{
		return dao.getTextMessages(messageFrom, messageTo, number);
	}
	@Transactional(readOnly=true)
	public List<TextMessageImpl> getTextMessageHistoryByDate(String messageFrom,String messageTo,String start,String end)
	
	{
		return dao.getTextMessages(messageFrom, messageTo, start, end);
	}
	private void setPrimaryKey(Message msg)
	{
//		if(StringUtils.isEmpty(msg.getMessageId()))
		msg.setMessageId(UUID.randomUUID().toString());
	}
	

}
