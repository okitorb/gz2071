package com.gec.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.gec.domain.Document;
import com.gec.query.DocQueryObject;
import com.gec.service.DocService;
import com.gec.service.impl.DocServiceImpl;
import com.gec.util.PageModel;

@WebServlet(urlPatterns = { "/documentlist.action", "/documentadd.action", "/documentaddsave.action",
		"/documentupdate.action", "/viewDocument.action", "/documentdel.action", "/documentdownload.action" })
@MultipartConfig
public class DocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();
		if (servletPath.equals("/documentlist.action")) {
			docList(request, response);
		} else if (servletPath.equals("/documentadd.action")) {
			docAdd(request, response);
		} else if (servletPath.equals("/documentaddsave.action")) {
			docAddSave(request, response);
		} else if (servletPath.equals("/documentupdate.action")) {
			docUpdate(request, response);
		} else if (servletPath.equals("/viewDocument.action")) {
			viewDoc(request, response);
		} else if (servletPath.equals("/documentdel.action")) {
			docDel(request, response);
		} else if (servletPath.equals("/documentdownload.action")) {
			docDownload(request, response);
		}
	}

	DocService service = new DocServiceImpl();

	private void docDownload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		Integer file_id = Integer.valueOf(id);

		Document doc = service.findDocById(file_id);

		String sqlfilename = doc.getFilename();
		System.out.println(sqlfilename);
		String filePath = "C:\\Users\\Kayan\\Downloads";// 存放的路径

		String filename = URLEncoder.encode(sqlfilename, "utf-8");
		System.out.println(filename);
		response.setHeader("content-disposition", "attachement;filename*=utf-8'zh_cn'" + filename);
		response.setHeader("Content-Type", "application/octet-stream");

		byte[] filebytes = doc.getFilebytes();
		System.out.println(filebytes.length);
		
		ByteArrayInputStream bis = null;
		OutputStream os = null;
		File file=null;
		try {
			file = new File(filePath, sqlfilename);
			if (!file.exists()) {
				file.createNewFile();
			}
			os = new FileOutputStream(file);
			bis = new ByteArrayInputStream(filebytes);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = bis.read(b)) > 0) {
				System.out.println(len);
				os.write(b,0,len);
				os.flush();
			}	
			os.close();
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void docDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取IDS
		String[] ids = request.getParameterValues("ids");
		for (String docid : ids) {
			// System.out.println(docid);
			// 获得ID后，进行删除操作（改变状态码，查询时不显示，代替删除数据的操作）
			Integer id = Integer.valueOf(docid);
			service.delete(id);
		}
		response.sendRedirect("documentlist.action");

	}

	private void viewDoc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 点击进入修改用户信息的页面，拿到id
		String docid = request.getParameter("id");

		// 先从数据库找到用户信息，再传到showUpdateDocument.jsp页面
		if (!"".equals(docid) && docid != null) {
			Integer id = Integer.parseInt(docid);

			// 调用service的方法 findEmpById();
			Document doc = service.findDocById(id);

			HttpSession session = request.getSession();
			session.setAttribute("document", doc);

			String prefix = "/WEB-INF/jsp/document/";
			String subfix = ".jsp";
			request.getRequestDispatcher(prefix + "showUpdateDocument" + subfix).forward(request, response);
		}

	}

	private void docUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Document ddoc = (Document) session.getAttribute("document");

		Integer id = ddoc.getId();
		String title = request.getParameter("title");
		String remark = request.getParameter("remark");

		Part part = request.getPart("file");
		String filename = part.getSubmittedFileName();
		String filetype = part.getContentType();

		byte[] fileBytes = getFileByte(part);

		Document doc = new Document();
		doc.setId(id);
		doc.setTitle(title);
		doc.setRemark(remark);
		if (part.getSize() != 0) {
			doc.setFilename(filename);
			doc.setFiletype(filetype);
			doc.setFilebytes(fileBytes);
		}

		service.update(doc);

		response.sendRedirect("documentlist.action");
	}

	private void docAddSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String remark = request.getParameter("remark");

		Integer user_id = Integer.valueOf(id);

		// 发文件
		Part part = request.getPart("file");
		String filename = part.getSubmittedFileName();
		String filetype = part.getContentType();

		byte[] fileBytes = getFileByte(part);

		Document doc = new Document();
		doc.setUser_id(user_id);
		doc.setTitle(title);
		doc.setRemark(remark);
		doc.setFilename(filename);
		doc.setFiletype(filetype);
		doc.setFilebytes(fileBytes);

		service.save(doc);

		String str = "添加成功";
		// 提示添加成功
		request.setAttribute("message", str);
		String prefix = "/WEB-INF/jsp/document/";
		String subfix = ".jsp";
		request.getRequestDispatcher(prefix + "documentadd" + subfix).forward(request, response);

	}

	private byte[] getFileByte(Part part) {
		ByteArrayOutputStream bos = null;
		try (InputStream is = part.getInputStream();) {
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = is.read(b)) > 0) {
				bos.write(b, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

	private void docAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/document/";
		String subfix = ".jsp";

		// 跳转页面
		RequestDispatcher dispatcher = request.getRequestDispatcher(prefix + "documentadd" + subfix);
		dispatcher.forward(request, response);

	}

	private void docList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/document/";
		String subfix = ".jsp";

		// 显示所有的用户信息
		// List<User> users = service.findAllUsers();
		// 把users存储到request中 所有查询
		// request.setAttribute("userlist", users);

		if (uri.equals("documentlist.action")) {
			// 分页查询
			// 接收参数 查询的用户名和密码 还有分页的当前页码
			String title = request.getParameter("title");
			String pageIndex = request.getParameter("pageIndex");

			// 转型
			Integer index = pageIndex == null ? 1 : Integer.valueOf(pageIndex);
			
			title = title == null ? title : title.trim();
			// 组装查询条件
			DocQueryObject obj = new DocQueryObject();
			obj.setTitle(title);

			obj.setPageIndex(index);
			// 查询条件回显
			request.setAttribute("doc", obj);

			// 分页组件
			PageModel model = null;

			// 先查总数
			int count = service.getTotalDocsCount(obj);
			// 查询用户list
			List<Document> documentlist = service.findDocsByPage(obj);

			// 存入request
			request.setAttribute("documentlist", documentlist);

			// 分页查询组件
			model = new PageModel(count, obj.getPageIndex(), obj.getPageSize());

			// 存入reqeust
			request.setAttribute("pageModel", model);

			// 跳转页面
			request.getRequestDispatcher(prefix + "documentlist" + subfix).forward(request, response);
		}

	}

}
