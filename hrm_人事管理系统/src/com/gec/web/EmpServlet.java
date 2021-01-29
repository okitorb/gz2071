package com.gec.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.domain.Dept;
import com.gec.domain.Employee;
import com.gec.domain.Job;
import com.gec.query.EmpQueryObject;
import com.gec.service.DeptService;
import com.gec.service.EmpService;
import com.gec.service.JobService;
import com.gec.service.impl.DeptServiceImpl;
import com.gec.service.impl.EmpServiceImpl;
import com.gec.service.impl.JobServiceImpl;
import com.gec.util.PageModel;

/**
 * Servlet implementation class EmpServlet
 */
@WebServlet(urlPatterns = { "/employeelist.action", "/employeeadd.action", "/employeeaddsave.action",
		"/employeeupdate.action", "/viewEmployee.action", "/employeedel.action" })
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();
		if (servletPath.equals("/employeelist.action")) {
			empList(request, response);
		} else if (servletPath.equals("/employeeadd.action")) {
			empAdd(request, response);
		} else if (servletPath.equals("/employeeaddsave.action")) {
			try {
				empAddSave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (servletPath.equals("/employeeupdate.action")) {
			try {
				empUpdate(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (servletPath.equals("/viewEmployee.action")) {
			viewEmp(request, response);
		} else if (servletPath.equals("/employeedel.action")) {
			empDel(request, response);
		}
	}

	EmpService service = new EmpServiceImpl();
	DeptService deptservice = new DeptServiceImpl();
	JobService jobservice = new JobServiceImpl();

	private void empDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取IDS
		String[] ids = request.getParameterValues("empIds");
		for (String eid : ids) {
			//System.out.println(eid);
			// 获得ID后，进行删除操作（改变状态码，查询时不显示，代替删除数据的操作）
			Integer id = Integer.valueOf(eid);
			service.delete(id);
		}
		response.sendRedirect("employeelist.action");

	}

	private void viewEmp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 点击进入修改用户信息的页面，拿到id
		String eid = request.getParameter("id");

		// 先从数据库找到用户信息，再传到employeeedit.jsp页面
		if (!"".equals(eid) && eid != null) {
			Integer id = Integer.parseInt(eid);

			// 调用service的方法 findEmpById();
			Employee emp = service.findEmpById(id);

			HttpSession session = request.getSession();
			// 返回一个user,再set值传到页面
			session.setAttribute("employee", emp);

			// 部门、职位list
			List<Dept> deptlist = deptservice.findAllDepts();
			List<Job> joblist = jobservice.findAllJobs();
			request.setAttribute("depts", deptlist);
			request.setAttribute("jobs", joblist);

			String prefix = "/WEB-INF/jsp/employee/";
			String subfix = ".jsp";
			request.getRequestDispatcher(prefix + "employeeedit" + subfix).forward(request, response);
		}

	}

	private void empUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		// 获取id
		HttpSession session = request.getSession();
		Employee eemp = (Employee) session.getAttribute("employee");
		// 拿到值，传进来封装成user
		Integer id = eemp.getId();

		String name = request.getParameter("name");
		String card_id = request.getParameter("card_id");
		String ssex = request.getParameter("sex");
		String ddept_id = request.getParameter("dept_id");
		String jjob_id = request.getParameter("job_id");
		String party = request.getParameter("party");
		String address = request.getParameter("address");
		String post_code = request.getParameter("post_code");
		String tel = request.getParameter("tel");
		String phone = request.getParameter("phone");
		String qq_num = request.getParameter("qq_num");
		String email = request.getParameter("email");
		String bbirthday = request.getParameter("birthday");
		String race = request.getParameter("race");
		String education = request.getParameter("education");
		String hobby = request.getParameter("hobby");
		String speciality = request.getParameter("speciality");
		String remark = request.getParameter("remark");

		Integer dept_id = Integer.valueOf(ddept_id);
		Integer job_id = Integer.valueOf(jjob_id);
		Integer sex = Integer.valueOf(ssex);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = sdf.parse(bbirthday);

		// 封装添加的emp
		Employee emp = new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setRemark(remark);
		emp.setDept_id(dept_id);
		emp.setJob_id(job_id);
		emp.setCard_id(card_id);
		emp.setAddress(address);
		emp.setPost_code(post_code);
		emp.setTel(tel);
		emp.setPhone(phone);
		emp.setQq_num(qq_num);
		emp.setEmail(email);
		emp.setSex(sex);
		emp.setParty(party);
		emp.setBirthday(birthday);
		emp.setRace(race);
		emp.setEducation(education);
		emp.setSpeciality(speciality);
		emp.setHobby(hobby);

		// 调用update方法
		service.update(emp);

		response.sendRedirect("employeelist.action");

	}

	private void empAddSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		String name = request.getParameter("name");
		String card_id = request.getParameter("card_id");
		String ssex = request.getParameter("sex");
		String ddept_id = request.getParameter("dept_id");
		String jjob_id = request.getParameter("job_id");
		String party = request.getParameter("party");
		String address = request.getParameter("address");
		String post_code = request.getParameter("post_code");
		String tel = request.getParameter("tel");
		String phone = request.getParameter("phone");
		String qq_num = request.getParameter("qq_num");
		String email = request.getParameter("email");
		String bbirthday = request.getParameter("birthday");
		String race = request.getParameter("race");
		String education = request.getParameter("education");
		String hobby = request.getParameter("hobby");
		String speciality = request.getParameter("speciality");
		String remark = request.getParameter("remark");

		Integer dept_id = Integer.valueOf(ddept_id);
		Integer job_id = Integer.valueOf(jjob_id);
		Integer sex = Integer.valueOf(ssex);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = sdf.parse(bbirthday);

		// 封装添加的emp
		Employee emp = new Employee();
		emp.setName(name);
		emp.setRemark(remark);
		emp.setDept_id(dept_id);
		emp.setJob_id(job_id);
		emp.setCard_id(card_id);
		emp.setAddress(address);
		emp.setPost_code(post_code);
		emp.setTel(tel);
		emp.setPhone(phone);
		emp.setQq_num(qq_num);
		emp.setEmail(email);
		emp.setSex(sex);
		emp.setParty(party);
		emp.setBirthday(birthday);
		emp.setRace(race);
		emp.setEducation(education);
		emp.setSpeciality(speciality);
		emp.setHobby(hobby);

		// 调用save方法
		service.save(emp);

		String str = "添加成功";
		// 提示添加成功
		request.setAttribute("message", str);
		String prefix = "/WEB-INF/jsp/employee/";
		String subfix = ".jsp";
		request.getRequestDispatcher(prefix + "employeeadd" + subfix).forward(request, response);

	}

	private void empAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/employee/";
		String subfix = ".jsp";

		List<Dept> deptlist = deptservice.findAllDepts();
		List<Job> joblist = jobservice.findAllJobs();
		request.setAttribute("deptList", deptlist);
		request.setAttribute("jobList", joblist);
		// 跳转页面
		RequestDispatcher dispatcher = request.getRequestDispatcher(prefix + "employeeadd" + subfix);
		dispatcher.forward(request, response);

	}

	private void empList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收请求的uri
		String uri = request.getRequestURI();// 这是端口后面的字符串
		// 截取字符串处理 /hrm/loginForm.action
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());

		System.out.println("uri:" + uri);

		String prefix = "/WEB-INF/jsp/employee/";
		String subfix = ".jsp";

		if (uri.equals("employeelist.action")) {

			// 创建部门和职位list
			List<Dept> deptlist = deptservice.findAllDepts();
			List<Job> joblist = jobservice.findAllJobs();
			request.setAttribute("deptList", deptlist);
			request.setAttribute("jobList", joblist);
			
			List<Integer> sexlist = new ArrayList<Integer>();
			sexlist.add(1);
			sexlist.add(2);
			request.setAttribute("sexlist", sexlist);

			// 分页查询
			// 接收参数 查询的用户名和密码 还有分页的当前页码
			String name = request.getParameter("name");
			String job_id = request.getParameter("job_id");
			String card_id = request.getParameter("card_id");
			String sex = request.getParameter("sex");
			String phone = request.getParameter("phone");
			String dept_id = request.getParameter("dept_id");
			
			name = name == null ? name : name.trim();
			job_id = job_id == null ? job_id : job_id.trim();
			card_id = card_id == null ? card_id : card_id.trim();
			sex = sex == null ? sex : sex.trim();
			phone = phone == null ? phone : phone.trim();
			dept_id = dept_id == null ? dept_id : dept_id.trim();

			Integer j = (job_id == null || job_id == "") ? 0 : Integer.valueOf(job_id);
			Integer s = (sex == null || sex == "") ? 0 : Integer.valueOf(sex);
			Integer d = (dept_id == null || dept_id == "") ? 0 : Integer.valueOf(dept_id);

			String pageIndex = request.getParameter("pageIndex");
			// 转型
			Integer index = pageIndex == null ? 1 : Integer.valueOf(pageIndex);
			
			// 组装查询条件
			EmpQueryObject obj = new EmpQueryObject();
			obj.setName(name);
			obj.setJob_id(j);
			obj.setCard_id(card_id);
			obj.setSex(s);
			obj.setPhone(phone);
			obj.setDept_id(d);

			obj.setPageIndex(index);
			
			// 查询条件回显
			request.setAttribute("employee", obj);


			// 分页组件
			PageModel model = null;

			// 先查总数
			int count = service.getTotalEmpsCount(obj);
			// 查询Emplist
			List<Employee> emplist = service.findEmpsByPage(obj);

			// 存入request
			request.setAttribute("employeelist", emplist);

			// 分页查询组件
			model = new PageModel(count, obj.getPageIndex(), obj.getPageSize());

			// 存入reqeust
			request.setAttribute("pageModel", model);

			// 跳转页面
			request.getRequestDispatcher(prefix + "employeelist" + subfix).forward(request, response);
		}
	}

}
