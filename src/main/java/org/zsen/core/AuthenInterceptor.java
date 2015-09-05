/**  
* @Title: AuthenInterceptor.java
* @Package org.zsen.core
* @Description TODO
* @author ZhangSen
* @date 2015年9月4日 下午8:55:11
* @version 
*/
package org.zsen.core;

import org.zsen.action.BaseAction;
import org.zsen.user.CONSTANT;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

/**
 * @ClassName: AuthenInterceptor
 * @Description:
 * @author ZhangSen
 * @date 2015年9月4日 下午8:55:11
 *
 */
public class AuthenInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 3993747602708651171L;

	/**
	 * @Description: TODO
	 * @param invocation
	 * @return
	 * @throws Exception
	 * @author ZhangSen
	 * @date 2015年9月4日 下午10:54:46
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		BaseAction baseAction = (BaseAction) invocation.getAction();
		if (baseAction.getSession().getAttribute(CONSTANT.USER_USER) == null)
			return CONSTANT.ACTION_LOGIN;
		else
			return invocation.invoke();
	}

}
