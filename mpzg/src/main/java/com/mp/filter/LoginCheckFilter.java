package com.mp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.mp.model.UserInfo;
/**
 * 用于拦截未经登录的用户非法请求
 *
 */
@Component("filter_login")
public class LoginCheckFilter implements Filter {

	private final Logger logger=Logger.getLogger(LoginCheckFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest http_request=(HttpServletRequest)request;
		HttpSession http_session=http_request.getSession();
		UserInfo user_info=(UserInfo) http_session.getAttribute("UserInfo");
		if(user_info!=null){
			chain.doFilter(request, response);
			logger.info("user_info!=null" );
		}
		else{
			logger.info("user_info==null" );
			http_request.getRequestDispatcher("/login_check").forward(request, response);
		}
			
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
