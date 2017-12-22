package com.java1234;

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

import com.alibaba.fastjson.JSON;
import com.java1234.util.HttpRequest;

public class LoginFilter implements Filter {

	public void destroy() {
		
	}
	
	public static void main(String[] args) {
		String param = "token=" + "" +"&userId=" + "";
		String sendPost = HttpRequest.sendPost("http://127.0.0.1:8080/blog_sso/login/verify", param);
		
		System.out.println(sendPost);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Object isLogin = session.getAttribute("isLogin");
		if (isLogin != null && String.valueOf(isLogin).equals("true")) {
			chain.doFilter(request, response);
		}else{
			String token = request.getParameter("token");
			String userId = request.getParameter("userId");
			if (token != null && userId != null) {
				//校验token
				if (verify(token, userId)) {
					session.setAttribute("isLogin", "true");
					session.setAttribute("userId", userId);
					chain.doFilter(request, response);
					return ;
				}
			}
			//去SSO认证
			String requestURL = req.getRequestURL().toString();
//			String result = HttpRequest.sendPost("http://127.0.0.1:8080/blog_sso/login/hasToken", null);
//			TokenResult tokenResult = JSON.parseObject(result, TokenResult.class);
//			if (tokenResult != null && TokenConstant.OK.equals(tokenResult.getStatus())) {
//				//校验token
//				if (verify(tokenResult.getToken(), tokenResult.getUserId())) {
//					session.setAttribute("isLogin", "true");
//					session.setAttribute("userId", userId);
//					chain.doFilter(request, response);
//					return ;
//				}
//			}
			//没有登录或token失效
			res.sendRedirect("http://127.0.0.1:8080/blog_sso/login/loginPage?redirectUrl="+requestURL);
			return;
		}
	}
	
	private boolean verify(String token,String userId){
		//校验token
		String param = "token=" + token +"&userId=" + userId;
		String sendPost = HttpRequest.sendPost("http://127.0.0.1:8080/blog_sso/login/verify", param);
		TokenResult tokenResult = JSON.parseObject(sendPost, TokenResult.class);
		if (TokenConstant.OK.equals(tokenResult.getStatus())) 
			return true;
		return false;
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
