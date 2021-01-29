package com.gec.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.domain.User;
import com.gec.query.UserQueryObject;
import com.gec.service.UserService;
import com.gec.service.impl.UserServiceImpl;
import com.gec.util.PageModel;


@WebServlet(urlPatterns = { "/userlist.action", "/useradd.action", "/useraddsave.action", "/userupdate.action",
		"/viewUser.action", "/userdel.action" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();
		if (servletPath.equals("/userlist.action")) {
			userList(request, response);
		} else if (servletPath.equals("/useradd.action")) {
			userAdd(request, response);
		} else if (servletPath.equals("/useraddsave.action")) {
			userAddSave(request, response);
		} else if (servletPath.equals("/userupdate.action")) {
			userUpdate(request, response);
		} else if (servletPath.equals("/viewUser.action")) {
			viewUser(request, response);
		} else if (servletPath.equals("/userdel.action")) {
			userDel(request, response);
		}
	}

	private void userDel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取Ids
		String[] ids = request.getParameterValues("userIds");
		for (String uid : ids) {
			System.out.println(uid);
			// 获得ID后，进行删除操作（改变状态码，查询时不显示，代替删除数据的操作）
			UserService service = new UserServiceImpl();
			Integer id = Integer.valueOf(uid);
			service.delete(id);
		}
		response.sendRedirect("userlist.action");
	}

	private void viewUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 点击进入修改用户信息的页面，拿到id
		String uid = request.getParameter("id");

		// 先从数据库找到用户信息，再传到useredit.jsp页面
		if (!"".equals(uid) && uid != null) {
			Integer id = Integer.parseInt(uid);
			UserService service = new UserServiceImpl();

			// 调用service的方法 findUserById();
			User user = service.findUserById(id);

			HttpSession session = request.getSession();
			// 返回一个user,再set值传到页面
			session.setAttribute("user", user);

			String prefix = "/WEB-INF/jsp/user/";
			String subfix = ".jsp";
			request.getRequestDispatcher(prefix + "useredit" + subfix).forward(request, response);
		}

	}

	private void userUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取id
		HttpSession session = request.getSession();
		User uuser = (User) session.getAttribute("user");
		// 拿到值，传进来封装成user
		Integer id = uuser.getId();

		String loginname = request.getParameter("loginname");
		String status = request.getParameter("status");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Integer stat = Integer.valueOf(status);

		// 封装添加的user
		UserService service = new UserServiceImpl();
		User user = new User();
		user.setId(id);
		user.setLoginname(loginname);
		user.setPassword(password);
		user.setUsername(username);
		user.setStatus(stat);
		// 调用update方法
		service.update(user);

		response.sendRedirect("userlist.action");
	}

	private void userAddSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String loginname = request.getParameter("loginname");
		String status = request.getParameter("status");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Integer stat = Integer.valueOf(status);
		// 封装添加的user
		UserService service = new UserServiceImpl();
		User user = new User();
		user.setLoginname(loginname);
		user.setPassword(password);
		user.setUsername(username);
		user.setStatus(stat);

		service.save(user);

		String str = "添加成功";
		// 提示添加成功 并跳转useradd.jsp
		request.setAttribute("message", str);
		String prefix = "/WEB-INF/jsp/user/";
		String subfix = ".jsp";
		request.getRequestDispatcher(prefix + "useradd" + subfix).forward(request, response);

	}

	private void userAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/user/";
		String subfix = ".jsp";

		// 跳转页面
		RequestDispatcher dispatcher = request.getRequestDispatcher(prefix + "useradd" + subfix);
		dispatcher.forward(request, response);

	}

	UserService service = new UserServiceImpl();

	private void userList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/user/";
		String subfix = ".jsp";

		// 显示所有的用户信息
		// List<User> users = service.findAllUsers();
		// 把users存储到request中 所有查询
		// request.setAttribute("userlist", users);

		if (uri.equals("userlist.action")) {
			// 分页查询
			// 接收参数 查询的用户名和密码 还有分页的当前页码
			String loginname = request.getParameter("loginname");
			String username = request.getParameter("username");
			String pageIndex = request.getParameter("pageIndex");
			String status = request.getParameter("status");
			
			// 转型
			Integer index = pageIndex == null ? 1 : Integer.valueOf(pageIndex);
			//去空格处理
			loginname = loginname == null ? loginname : loginname.trim();
			username = username == null ? username : username.trim();
			status = status == null ? status : status.trim();
			
			// 组装查询条件
			UserQueryObject obj = new UserQueryObject();
			obj.setLoginname(loginname);
			obj.setUsername(username);

			Integer s = (status == null || status == "") ? 1 : Integer.valueOf(status);
			obj.setStatus(s);
			obj.setPageIndex(index);
			// 查询条件回显
			request.setAttribute("user", obj);

			// 分页组件
			PageModel model = null;

			// 先查总数
			int count = service.getTotalUsersCount(obj);
			// 查询用户list
			List<User> userlist = service.findUsersByPage(obj);

			// System.out.println(userlist);
			// 存入request
			request.setAttribute("userlist", userlist);

			// 分页查询组件
			model = new PageModel(count, obj.getPageIndex(), obj.getPageSize());

			// 存入reqeust
			request.setAttribute("pageModel", model);

			// 把users存储到request中 所有查询
			// request.setAttribute("userlist", users);

			// 跳转页面
			request.getRequestDispatcher(prefix + "userlist" + subfix).forward(request, response);
		}

	}

}
