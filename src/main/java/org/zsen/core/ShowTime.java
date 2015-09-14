/**  
* @Title: ShowTime.java
* @Package org.zsen.core
* @Description TODO
* @author ZhangSen
* @date 2015年9月5日 下午8:00:25
* @version 
*/ 
package org.zsen.core;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.zsen.action.UserAction;


/**
* @ClassName: ShowTime
* @Description: 
* @author ZhangSen
* @date 2015年9月5日 下午8:00:25
*
*/
@Component
@Aspect
public class ShowTime {

	private Logger log=LogManager.getLogger(ShowTime.class);
	public ShowTime()
	{
	}
	@Pointcut("execution(* org.zsen.action.UserAction.*(..))")
	public void time()
	{
		
	}
	@Around("time()")
	public Object surround(ProceedingJoinPoint pjp) throws Throwable
	{
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		long start=System.currentTimeMillis();
        Object object = pjp.proceed();
        System.out.println(method.toString()+"  "+(System.currentTimeMillis()-start)+"ms");
        log.debug(method.toString()+"  "+(System.currentTimeMillis()-start)+"ms");
        return object;  
	}
}
