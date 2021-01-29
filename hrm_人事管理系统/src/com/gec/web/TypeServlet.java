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

import com.gec.domain.Type;
import com.gec.query.TypeQueryObject;
import com.gec.service.TypeService;
import com.gec.service.impl.TypeServiceImpl;
import com.gec.util.PageModel;

@WebServlet(urlPatterns = { "/typelist.action", "/typeadd.action", "/typeaddsave.action",
		"/typeupdate.action", "/viewType.action", "/typedel.action" })
@MultipartConfig
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();
		if (servletPath.equals("/typelist.action")) {
			typeList(request, response);
		} else if (servletPath.equals("/typeadd.action")) {
			typeAdd(request, response);
		} else if (servletPath.equals("/typeaddsave.action")) {
			typeAddSave(request, response);
		} else if (servletPath.equals("/typeupdate.action")) {
			typeUpdate(request, response);
		} else if (servletPath.equals("/viewType.action")) {
			viewtype(request, response);
		} else if (servletPath.equals("/typedel.action")) {
			typeDel(request, response);
		} 
	}
	
	TypeService service = new TypeServiceImpl();

	private void typeDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取IDS
		String[] ids = request.getParameterValues("ids");
		for (String typeid : ids) {
			// System.out.println(typeid);
			// 获得ID后，进行删除操作（改变状态码，查询时不显示，代替删除数据的操作）
			Integer id = Integer.valueOf(typeid);
			service.delete(id);
		}
		response.sendRedirect("typelist.action");

	}

	private void viewtype(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 点击进入修改信息的页面，拿到id
		String tid = request.getParameter("id");

		// 先从数据库找到用户信息，再传到showUpdateDocument.jsp页面
		if (!"".equals(tid) && tid != null) {
			Integer id = Integer.parseInt(tid);

			// 调用service的方法 findEmpById();
			Type type = service.findTypeById(id);

			HttpSession session = request.getSession();
			session.setAttribute("type", type);
			
			String prefix = "/WEB-INF/jsp/type/";
			String subfix = ".jsp";
			request.getRequestDispatcher(prefix + "typeedit" + subfix).forward(request, response);
		}

	}

	private void typeUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Type ntype = (Type) session.getAttribute("type");

		Integer id = ntype.getId();
		String name = request.getParameter("name");
		String sstate = request.getParameter("state");
		Integer state = Integer.valueOf(sstate);
		
		Type type = new Type();
		type.setId(id);
		type.setName(name);
		type.setState(state);
		
		service.update(type);

		response.sendRedirect("typelist.action");
	}

	private void typeAddSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		Integer user_id = Integer.valueOf(id);

		//封装
		Type type = new Type();
		type.setName(name);
		type.setUser_id(user_id);
		type.setState(1);

		service.save(type);

		String str = "添加成功";
		// 提示添加成功
		request.setAttribute("message", str);
		String prefix = "/WEB-INF/jsp/type/";
		String subfix = ".jsp";
		request.getRequestDispatcher(prefix + "typeadd" + subfix).forward(request, response);

	}

	private void typeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);
		
		String prefix = "/WEB-INF/jsp/type/";
		String subfix = ".jsp";

		// 跳转页面
		RequestDispatcher dispatcher = request.getRequestDispatcher(prefix + "typeadd" + subfix);
		dispatcher.forward(request, response);

	}

	private void typeList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/type/";
		String subfix = ".jsp";

		if (uri.equals("typelist.action")) {
			// 分页查询
			// 接收参数 查询的用户名和密码 还有分页的当前页码
			String name = request.getParameter("name");
			String pageIndex = request.getParameter("pageIndex");

			// 转型
			Integer index = pageIndex == null ? 1 : Integer.valueOf(pageIndex);
			
			name = name == null ? name : name.trim();
			
			// 组装查询条件
			TypeQueryObject obj = new TypeQueryObject();
			obj.setName(name);
			obj.setPageIndex(index);
			// 查询条件回显
			request.setAttribute("ty", obj);

			// 分页组件
			PageModel model = null;

			// 先查总数
			int count = service.getTotalTypesCount(obj);
			// 查询用户list
			List<Type> typelist = service.findTypesByPage(obj);

			// 存入request
			request.setAttribute("typelist", typelist);

			// 分页查询组件
			model = new PageModel(count, obj.getPageIndex(), obj.getPageSize());

			// 存入reqeust
			request.setAttribute("pageModel", model);

			// 跳转页面
			request.getRequestDispatcher(prefix + "typelist" + subfix).forward(request, response);
		}

	}

}
