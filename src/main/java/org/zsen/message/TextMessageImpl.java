/**  
* @Title: TextMessageImpl.java
* @Package org.zsen.message
* @Description TODO
* @author ZhangSen
* @date 2015年8月30日 下午2:40:54
* @version 
*/ 
package org.zsen.message;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
* @ClassName: TextMessageImpl
* @Description: 
* @author ZhangSen
* @date 2015年8月30日 下午2:40:54
*
*/
@Entity
@Table(name="TextMessage")
@Component
public class TextMessageImpl implements TextMessage,Serializable {

	private static final long serialVersionUID = -3303756414570648202L;
	@Id
	private String messageId;
	private String messageFrom;
	private String messageTo;
	private Date messageDate;
	private String type;
	private String text;


	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	public String getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "TextMessageImpl [messageId=" + messageId + ", messageFrom=" + messageFrom + ", messageTo=" + messageTo
				+ ", messageDate=" + messageDate + ", type=" + type + ", text=" + text + "]";
	}
	
	

}
