package com.gec.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.domain.User;
import com.gec.service.UserService;
import com.gec.service.impl.UserServiceImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = {"/login.action","/loginForm.action"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();//这是端口后面的字符串
		//截取字符串处理  /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/")+1, uri.length());
		
		System.out.println("uri:" + uri);
		
		String prefix = "/WEB-INF/jsp/";
		String subfix = ".jsp";
		
		if(uri.equals("loginForm.action")) {
			//跳转到登录页
			request.getRequestDispatcher(prefix +"loginForm"+subfix).forward(request, response);
		}else {
			//接收用户名和密码
			String loginname = request.getParameter("loginname");
			String password = request.getParameter("password");
			//new UserServiceImpl()
			UserService service = new UserServiceImpl();
			//login
			User user = service.login(loginname, password);
			//有用户
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user_session", user);
				
				//跳转到主页
				request.getRequestDispatcher("main.action").forward(request, response);
			}else {
				//没有用户
				request.getRequestDispatcher(prefix +"loginForm"+subfix).forward(request, response);

			}
			
			
		}
	}

}
