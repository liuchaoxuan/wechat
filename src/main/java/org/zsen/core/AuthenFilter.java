/**  
* @Title: AuthenFilter.java
* @Package org.zsen.core
* @Description TODO
* @author ZhangSen
* @date 2015年9月5日 上午12:10:36
* @version 
*/ 
package org.zsen.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zsen.user.CONSTANT;

/**
* @ClassName: AuthenFilter
* @Description: 
* @author ZhangSen
* @date 2015年9月5日 上午12:10:36
*
*/
public class AuthenFilter implements Filter{

	/**
	* @Description: TODO
	* @author ZhangSen
	* @date 2015年9月5日 上午12:11:33      
	*/ 
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	* @Description: TODO
	* @param arg0
	* @param arg1
	* @param arg2
	* @throws IOException
	* @throws ServletException
	* @author ZhangSen
	* @date 2015年9月5日 上午12:11:33      
	*/ 
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) arg0;
		   HttpServletResponse response = (HttpServletResponse) arg1;
		 if(request.getSession().getAttribute(CONSTANT.USER_USER)==null)
			 response.sendRedirect("error.jsp");
		 else
			 arg2.doFilter(arg0, arg1);
	}
	

	/**
	* @Description: TODO
	* @param arg0
	* @throws ServletException
	* @author ZhangSen
	* @date 2015年9月5日 上午12:11:33      
	*/ 
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
