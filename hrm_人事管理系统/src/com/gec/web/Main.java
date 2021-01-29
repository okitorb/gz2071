package com.gec.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet(urlPatterns = { "/main.action", "/top.action", "/left.action", "/right.action" })
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/";
		String subfix = ".jsp";
		
		RequestDispatcher rd = null;
		//判断
		//整合页面
		if(uri.equals("main.action")) {
			rd = request.getRequestDispatcher(prefix+"main"+subfix);
			rd.forward(request, response);
		}else if(uri.equals("top.action")) {
			rd = request.getRequestDispatcher(prefix+"top"+subfix);
			rd.forward(request, response);
		}else if(uri.equals("left.action")) {
			rd = request.getRequestDispatcher(prefix+"left"+subfix);
			rd.forward(request, response);
		}else if(uri.equals("right.action")) {
			rd = request.getRequestDispatcher(prefix+"right"+subfix);
			rd.forward(request, response);
		}

	}

}
