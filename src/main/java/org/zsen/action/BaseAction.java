/**  
* @Title: BaseAction.java
* @Package org.zsen.action
* @Description TODO
* @author ZhangSen
* @date 2015年9月4日 下午8:20:25
* @version 
*/ 
package org.zsen.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
* @ClassName: BaseAction
* @Description: 
* @author ZhangSen
* @date 2015年9月4日 下午8:20:25
*
*/
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = -6366388718338911187L;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpSession session=request.getSession();
	private HttpServletResponse response=ServletActionContext.getResponse();
	public BaseAction()
	{
		response.setContentType("text/html;charset=utf-8");
	}
	
	public PrintWriter getWriter() throws IOException
	{
		return response.getWriter();
	}
	public HttpSession getSession()
	{
		return session;
	}
	public HttpServletRequest getRequst()
	{ 
		return request;
	}
	public HttpServletResponse getResponse()
	{
		
		return response;
	}

}
