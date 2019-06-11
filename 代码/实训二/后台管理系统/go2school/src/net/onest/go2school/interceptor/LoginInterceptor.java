package net.onest.go2school.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;


public class LoginInterceptor implements org.springframework.web.servlet.HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	//在拦截点之前进行拦截，返回false不执行拦截，返回值为true执行拦截
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("访问路径"+arg0.getRequestURI());
		HttpSession  session=arg0.getSession();
		if(session.getAttribute("manager")!=null) {
			//manager已经登录
			
			System.out.println("manager已经登录");
			return false;
		}else {
			arg1.sendRedirect(arg0.getContextPath()+"/login.jsp");
			return true;
		}
	
	}

}
