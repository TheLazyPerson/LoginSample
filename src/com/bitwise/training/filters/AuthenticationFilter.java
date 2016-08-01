package com.bitwise.training.filters;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitwise.training.login.LoginServlet;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
//@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
	boolean executed = false;
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
       
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		Map<String, String> loginData = new HashMap<>(); 
		loginData.put("trial@bitwiseglobal.com", "1234");
		loginData.put("harsh@bitwiseglobal.com", "1234");
		loginData.put("pooja@bitwiseglobal.com", "1234");
		loginData.put("taher@bitwiseglobal.com", "1234");
		loginData.put("om@bitwiseglobal.com", "1234");
		loginData.put("akanksha@bitwiseglobal.com", "1234");
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		Enumeration<String> params = request.getParameterNames();
		Map<String,String> parameters = new HashMap<>();
		
		while (params.hasMoreElements()) {
			String key = (String) params.nextElement();
			String values = request.getParameter(key);
			parameters.put(key, values);
		}
		
		if (parameters.containsKey("username") && parameters.containsKey("password") &&				
			parameters.get("password").equals(loginData.get(parameters.get("username")))) {
			HttpSession session = request2.getSession(true);
			executed = true;
			chain.doFilter(request2, response2);
			System.out.println("After chaining");
		}else{
			if (!executed) {
				//username or password is wrong
				response2.sendRedirect("/LoginSample/LoginServlet?error="+LoginServlet.errorWrongUsername);
				executed = false;
			}
		}

		// pass the request along the filter chain
		System.out.println("Filter After");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
