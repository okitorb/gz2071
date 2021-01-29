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

import com.gec.domain.Dept;
import com.gec.query.DeptQueryObject;
import com.gec.service.DeptService;
import com.gec.service.impl.DeptServiceImpl;
import com.gec.util.PageModel;


@WebServlet(urlPatterns = { "/deptlist.action", "/deptadd.action", "/deptaddsave.action", "/deptupdate.action",
		"/viewDept.action", "/deptdel.action" })
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();
		if (servletPath.equals("/deptlist.action")) {
			deptList(request, response);
		} else if (servletPath.equals("/deptadd.action")) {
			deptAdd(request, response);
		} else if (servletPath.equals("/deptaddsave.action")) {
			deptAddSave(request, response);
		} else if (servletPath.equals("/deptupdate.action")) {
			deptUpdate(request, response);
		} else if (servletPath.equals("/viewDept.action")) {
			viewDept(request, response);
		} else if (servletPath.equals("/deptdel.action")) {
			deptDel(request, response);
		}
	}

	DeptService service = new DeptServiceImpl();

	private void deptDel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取IDS
		String[] ids = request.getParameterValues("deptIds");
		for (String did : ids) {
			System.out.println(did);
			// 获得ID后，进行删除操作（改变状态码，查询时不显示，代替删除数据的操作）
			Integer id = Integer.valueOf(did);
			service.delete(id);
		}
		response.sendRedirect("deptlist.action");

	}

	private void viewDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 点击进入修改用户信息的页面，拿到id
		String did = request.getParameter("id");

		// 先从数据库找到用户信息，再传到useredit.jsp页面
		if (!"".equals(did) && did != null) {
			Integer id = Integer.parseInt(did);

			// 调用service的方法 findUserById();
			Dept dept = service.findDeptById(id);

			HttpSession session = request.getSession();
			// 返回一个user,再set值传到页面
			session.setAttribute("dept", dept);

			String prefix = "/WEB-INF/jsp/dept/";
			String subfix = ".jsp";
			request.getRequestDispatcher(prefix + "deptedit" + subfix).forward(request, response);
		}

	}

	private void deptUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取id
		HttpSession session = request.getSession();
		Dept ddept = (Dept) session.getAttribute("dept");
		// 拿到值，传进来封装成user
		Integer id = ddept.getId();

		String name = request.getParameter("name");
		String remark = request.getParameter("remark");

		// 封装添加的dept
		Dept dept = new Dept();
		dept.setId(id);
		dept.setName(name);
		dept.setRemark(remark);
		// 调用update方法
		service.update(dept);

		response.sendRedirect("deptlist.action");
	}

	private void deptAddSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String remark = request.getParameter("remark");

		// 封装添加的user
		Dept dept = new Dept();
		dept.setName(name);
		dept.setRemark(remark);

		service.save(dept);

		String str = "添加成功";
		// 提示添加成功 并跳转useradd.jsp
		request.setAttribute("message", str);
		String prefix = "/WEB-INF/jsp/dept/";
		String subfix = ".jsp";
		request.getRequestDispatcher(prefix + "addDept" + subfix).forward(request, response);

	}

	private void deptAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/dept/";
		String subfix = ".jsp";

		// 跳转页面
		RequestDispatcher dispatcher = request.getRequestDispatcher(prefix + "addDept" + subfix);
		dispatcher.forward(request, response);

	}

	private void deptList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/dept/";
		String subfix = ".jsp";

		// 显示所有的用户信息
		// List<User> users = service.findAllUsers();
		// 把users存储到request中 所有查询
		// request.setAttribute("userlist", users);

		if (uri.equals("deptlist.action")) {
			// 分页查询
			// 接收参数 查询的用户名和密码 还有分页的当前页码
			String name = request.getParameter("name");
			String pageIndex = request.getParameter("pageIndex");

			// 转型
			Integer index = pageIndex == null ? 1 : Integer.valueOf(pageIndex);
			
			name = name == null ? name : name.trim();
			// 组装查询条件
			DeptQueryObject obj = new DeptQueryObject();
			obj.setName(name);

			obj.setPageIndex(index);
			// 查询条件回显
			request.setAttribute("dept", obj);

			// 分页组件
			PageModel model = null;

			// 先查总数
			int count = service.getTotalDeptsCount(obj);
			// 查询用户list
			List<Dept> deptlist = service.findDeptsByPage(obj);

			// 存入request
			request.setAttribute("deptlist", deptlist);

			// 分页查询组件
			model = new PageModel(count, obj.getPageIndex(), obj.getPageSize());

			// 存入reqeust
			request.setAttribute("pageModel", model);

			// 跳转页面
			request.getRequestDispatcher(prefix + "selectDept" + subfix).forward(request, response);
		}
	}

}
