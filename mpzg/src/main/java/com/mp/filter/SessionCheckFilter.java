package com.mp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.mp.model.UserInfo;
/**
 * 用于将登陆的用户重定向至系统主页
 * @author dqy
 *
 */
@Component("filter_session")
public class SessionCheckFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest http_request=(HttpServletRequest)request;
		HttpServletResponse http_response=(HttpServletResponse) response;
		HttpSession http_session=http_request.getSession();
		UserInfo user_info=(UserInfo) http_session.getAttribute("UserInfo");
		if(user_info==null){
			chain.doFilter(request, response);
		}
		else{
			http_response.sendRedirect(http_request.getContextPath()+"/zg/view/index");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
