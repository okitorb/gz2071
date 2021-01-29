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

import com.gec.domain.Job;
import com.gec.query.JobQueryObject;
import com.gec.service.JobService;
import com.gec.service.impl.JobServiceImpl;
import com.gec.util.PageModel;

@WebServlet(urlPatterns = { "/joblist.action", "/jobadd.action", "/jobaddsave.action", "/jobupdate.action",
		"/viewJob.action", "/jobdel.action" })
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();
		if (servletPath.equals("/joblist.action")) {
			jobList(request, response);
		} else if (servletPath.equals("/jobadd.action")) {
			jobAdd(request, response);
		} else if (servletPath.equals("/jobaddsave.action")) {
			jobAddSave(request, response);
		} else if (servletPath.equals("/jobupdate.action")) {
			jobUpdate(request, response);
		} else if (servletPath.equals("/viewJob.action")) {
			viewJob(request, response);
		} else if (servletPath.equals("/jobdel.action")) {
			jobDel(request, response);
		}
	}

	JobService service = new JobServiceImpl();

	private void jobDel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取IDS
		String[] ids = request.getParameterValues("jobIds");
		for (String jid : ids) {
			System.out.println(jid);
			// 获得ID后，进行删除操作（改变状态码，查询时不显示，代替删除数据的操作）
			Integer id = Integer.valueOf(jid);
			service.delete(id);
		}
		response.sendRedirect("joblist.action");

	}

	private void viewJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 点击进入修改用户信息的页面，拿到id
		String jid = request.getParameter("id");

		// 先从数据库找到用户信息，再传到useredit.jsp页面
		if (!"".equals(jid) && jid != null) {
			Integer id = Integer.parseInt(jid);

			// 调用service的方法 findUserById();
			Job job = service.findJobById(id);

			HttpSession session = request.getSession();
			// 返回一个user,再set值传到页面
			session.setAttribute("job", job);

			String prefix = "/WEB-INF/jsp/job/";
			String subfix = ".jsp";
			request.getRequestDispatcher(prefix + "jobedit" + subfix).forward(request, response);
		}

	}

	private void jobUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取id
		HttpSession session = request.getSession();
		Job jjob = (Job) session.getAttribute("job");
		// 拿到值，传进来封装成user
		Integer id = jjob.getId();

		String name = request.getParameter("name");
		String remark = request.getParameter("remark");

		// 封装添加的job
		Job job = new Job();
		job.setId(id);
		job.setName(name);
		job.setRemark(remark);
		// 调用update方法
		service.update(job);

		response.sendRedirect("joblist.action");
	}

	private void jobAddSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String remark = request.getParameter("remark");

		// 封装添加的user
		Job job = new Job();
		job.setName(name);
		job.setRemark(remark);

		service.save(job);

		String str = "添加成功";
		// 提示添加成功 并跳转useradd.jsp
		request.setAttribute("message", str);
		String prefix = "/WEB-INF/jsp/job/";
		String subfix = ".jsp";
		request.getRequestDispatcher(prefix + "addJob" + subfix).forward(request, response);

	}

	private void jobAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/job/";
		String subfix = ".jsp";

		// 跳转页面
		RequestDispatcher dispatcher = request.getRequestDispatcher(prefix + "addJob" + subfix);
		dispatcher.forward(request, response);

	}

	private void jobList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/job/";
		String subfix = ".jsp";

		if (uri.equals("joblist.action")) {
			// 分页查询
			// 接收参数 查询的用户名和密码 还有分页的当前页码
			String name = request.getParameter("name");
			String pageIndex = request.getParameter("pageIndex");

			// 转型
			Integer index = pageIndex == null ? 1 : Integer.valueOf(pageIndex);
			
			name = name == null ? name : name.trim();
			// 组装查询条件
			JobQueryObject obj = new JobQueryObject();
			obj.setName(name);

			obj.setPageIndex(index);
			// 查询条件回显
			request.setAttribute("job", obj);

			// 分页组件
			PageModel model = null;

			// 先查总数
			int count = service.getTotalJobsCount(obj);
			// 查询用户list
			List<Job> joblist = service.findJobsByPage(obj);

			// 存入request
			request.setAttribute("joblist", joblist);

			// 分页查询组件
			model = new PageModel(count, obj.getPageIndex(), obj.getPageSize());

			// 存入reqeust
			request.setAttribute("pageModel", model);

			// 跳转页面
			request.getRequestDispatcher(prefix + "selectJob" + subfix).forward(request, response);
		}
	}

}
