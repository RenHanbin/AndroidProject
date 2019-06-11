package net.onest.go2school.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		HttpSession session=request.getSession();
		String url=request.getRequestURI();
		System.out.println("访问地址"+request.getRequestURI());
		if(!request.getRequestURI().contains("login")) {
				if(session.getAttribute("manager")==null) {
					if(url.contains("assets")) {
						System.out.println("manager未登录，且访问的是login样式页，通过过滤器");
						arg2.doFilter(arg0, arg1);
					}else {
						System.out.println("manager未登录，切访问的不是login样式页，过滤器过滤跳转");
						response.sendRedirect(request.getContextPath()+"/login.jsp");
					}
					
				}else {
					System.out.println("通过过滤器");
					arg2.doFilter(arg0, arg1);
			}
		}else {
			System.out.println("经过的是login页通过过滤器");
			arg2.doFilter(arg0, arg1);
		}
		
	
	
	}
	
	

}
