package com.ewell.upload.quartz.util;

import com.ewell.upload.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 执行定时任务
 * @author
 */
@Slf4j
public class QuartzRunnable implements Runnable {

	private Object target;
	private Method method;
	private Object params;

	QuartzRunnable(String beanName, String methodName, Object params)
			throws NoSuchMethodException, SecurityException {
		this.target = SpringContextHolder.getBean(beanName);
		this.params = params;

		if (null != params) {
			this.method = target.getClass().getDeclaredMethod(methodName, params.getClass());
		} else {
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
	}

	@Override
	public void run() {
		try {
			ReflectionUtils.makeAccessible(method);
			if (null != params) {
				method.invoke(target, params);
			} else {
				method.invoke(target);
			}
		} catch (Exception e) {
		    e.printStackTrace();
		    log.error("方法调用异常------>"+e.getMessage());
		}
	}

}
