package com.gec.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.domain.Notice;
import com.gec.domain.Type;
import com.gec.query.NoticeQueryObject;
import com.gec.service.NoticeService;
import com.gec.service.TypeService;
import com.gec.service.impl.NoticeServiceImpl;
import com.gec.service.impl.TypeServiceImpl;
import com.gec.util.PageModel;

@WebServlet(urlPatterns = { "/noticelist.action", "/noticeadd.action", "/noticeaddsave.action",
		"/noticeupdate.action", "/viewNotice.action", "/noticedel.action" })
@MultipartConfig
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();
		if (servletPath.equals("/noticelist.action")) {
			noticeList(request, response);
		} else if (servletPath.equals("/noticeadd.action")) {
			noticeAdd(request, response);
		} else if (servletPath.equals("/noticeaddsave.action")) {
			noticeAddSave(request, response);
		} else if (servletPath.equals("/noticeupdate.action")) {
			noticeUpdate(request, response);
		} else if (servletPath.equals("/viewNotice.action")) {
			viewNotice(request, response);
		} else if (servletPath.equals("/noticedel.action")) {
			noticeDel(request, response);
		} 
	}

	NoticeService service = new NoticeServiceImpl();
	TypeService typeservice = new TypeServiceImpl();

	private void noticeDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取IDS
		String[] ids = request.getParameterValues("ids");
		for (String noticeid : ids) {
			// System.out.println(noticeid);
			// 获得ID后，进行删除操作（改变状态码，查询时不显示，代替删除数据的操作）
			Integer id = Integer.valueOf(noticeid);
			service.delete(id);
		}
		response.sendRedirect("noticelist.action");

	}

	private void viewNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 点击进入修改用户信息的页面，拿到id
		String nid = request.getParameter("id");

		// 先从数据库找到用户信息，再传到showUpdateDocument.jsp页面
		if (!"".equals(nid) && nid != null) {
			Integer id = Integer.parseInt(nid);

			// 调用service的方法 findEmpById();
			Notice notice = service.findNoticeById(id);

			HttpSession session = request.getSession();
			session.setAttribute("notice", notice);
			
			//需要typelist
			List<Type> types = typeservice.findAllTypes();
			request.setAttribute("typeList", types);

			String prefix = "/WEB-INF/jsp/notice/";
			String subfix = ".jsp";
			request.getRequestDispatcher(prefix + "noticeedit" + subfix).forward(request, response);
		}

	}

	private void noticeUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Notice nnotice = (Notice) session.getAttribute("notice");

		Integer id = nnotice.getId();
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String tid = request.getParameter("type_id");
		
		Integer type_id = Integer.valueOf(tid);
		
		Notice notice = new Notice();
		notice.setId(id);
		notice.setName(name);
		notice.setContent(content);
		notice.setType_id(type_id);
		
		service.update(notice);

		response.sendRedirect("noticelist.action");
	}

	private void noticeAddSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String tid = request.getParameter("type_id");
		
		Integer user_id = Integer.valueOf(id);
		Integer type_id = Integer.valueOf(tid);

		//封装
		Notice notice = new Notice();
		notice.setName(name);
		notice.setContent(content);
		notice.setUser_id(user_id);
		notice.setType_id(type_id);

		service.save(notice);

		String str = "添加成功";
		// 提示添加成功
		request.setAttribute("message", str);
		String prefix = "/WEB-INF/jsp/notice/";
		String subfix = ".jsp";
		request.getRequestDispatcher(prefix + "noticeadd" + subfix).forward(request, response);

	}

	private void noticeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);
		
		//需要typelist
		List<Type> types = typeservice.findAllTypes();
		request.setAttribute("typeList", types);
		
		String prefix = "/WEB-INF/jsp/notice/";
		String subfix = ".jsp";

		// 跳转页面
		RequestDispatcher dispatcher = request.getRequestDispatcher(prefix + "noticeadd" + subfix);
		dispatcher.forward(request, response);

	}

	private void noticeList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/notice/";
		String subfix = ".jsp";

		if (uri.equals("noticelist.action")) {
			// 分页查询
			// 接收参数 查询的用户名和密码 还有分页的当前页码
			String name = request.getParameter("name");
			String pageIndex = request.getParameter("pageIndex");

			// 转型
			Integer index = pageIndex == null ? 1 : Integer.valueOf(pageIndex);
			
			name = name == null ? name : name.trim();
			// 组装查询条件
			NoticeQueryObject obj = new NoticeQueryObject();
			obj.setName(name);
			obj.setPageIndex(index);
			// 查询条件回显
			request.setAttribute("note", obj);

			// 分页组件
			PageModel model = null;

			// 先查总数
			int count = service.getTotalNoticesCount(obj);
			// 查询用户list
			List<Notice> noticelist = service.findNoticesByPage(obj);

			// 存入request
			request.setAttribute("noticelist", noticelist);

			// 分页查询组件
			model = new PageModel(count, obj.getPageIndex(), obj.getPageSize());

			// 存入reqeust
			request.setAttribute("pageModel", model);

			// 跳转页面
			request.getRequestDispatcher(prefix + "noticelist" + subfix).forward(request, response);
		}

	}

}
