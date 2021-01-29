package com.gec.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout.action")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		HttpSession session = request.getSession();
		session.removeAttribute("user_session");
		// 跳转到主页
		///hrm_人事管理系统/WebContent/index.jsp
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
