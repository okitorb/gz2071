package com.gec.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.gec.dao.AdminDao;
import com.gec.domain.Clock_info;
import com.gec.domain.Employee;
import com.gec.domain.Goods;
import com.gec.domain.Sell_info;
import com.gec.domain.Vip;
import com.gec.util.JDBCUtil;

public class AdminDaoImpl implements AdminDao {

	@Override
	public List<Employee> fingAllEmployee() {

		List<Employee> emps = new ArrayList<>();
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from employee where remark = 1";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setNumber(rs.getString("number"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("password"));
				emp.setSex(rs.getString("sex"));
				emp.setPhone(rs.getString("phone"));
				emp.setRole(rs.getInt("role"));
				emp.setRemark(rs.getInt("remark"));
				emps.add(emp);
			}
			return emps;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}

	// 收银员管理
	@Override
	public List<Employee> findAllCashier() {
		List<Employee> cashiers = new ArrayList<>();
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from employee where role = 2 and remark = 1";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setNumber(rs.getString("number"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("password"));
				emp.setSex(rs.getString("sex"));
				emp.setPhone(rs.getString("phone"));
				emp.setRole(rs.getInt("role"));
				emp.setRemark(rs.getInt("remark"));
				cashiers.add(emp);
			}
			return cashiers;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}

	@Override
	public void addCashier() {
		// 输入收银员信息
		Scanner sc = new Scanner(System.in);
		// 创建收银员对象
		Employee cas = new Employee();
		System.out.println("请输入收银员编号");
		cas.setNumber(sc.next());
		System.out.println("请输入收银员姓名");
		cas.setUsername(sc.next());
		System.out.println("请输入密码");
		cas.setPassword(sc.next());
		System.out.println("请输入性别");
		cas.setSex(sc.next());

		System.out.println("请输入手机号码");
		cas.setPhone(sc.next());
		

		// 角色
		cas.setRole(2);
		cas.setRemark(1);
		
		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "insert into employee(number,username,password,sex,phone,role,remark)" + "values('"
					+ cas.getNumber() + "','" + cas.getUsername() + "','" + cas.getPassword() + "','" + cas.getSex()
					+ "'," + cas.getPhone() + ",2," + cas.getRemark() + ")";
			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("添加收银员成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}

	}

	@Override
	public void delCashier() {
		// 提示输入编号
		System.out.println("请输入要删除收银员编号");
		Scanner sc = new Scanner(System.in);
		String num = sc.next();

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "update employee set remark = 0 where number = '" + num + "'";
			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("删除成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}

	}

	@Override
	public void updateCashier(Employee employee) {
		System.out.println(employee);
		String sql = "update employee ";

		Scanner sc = new Scanner(System.in);
		// 修改
		System.out.println("1 修改姓名 2修改密码 3修改性别 4 修改手机号码 ");
		int op = sc.nextInt();
		if (op == 1) {
			System.out.println("请输入要修改的姓名");
			String name = sc.next();
			sql += " set username = '" + name + "' ";
		} else if (op == 2) {
			System.out.println("请输入要修改的密码");
			String password = sc.next();
			sql += " set password = '" + password + "' ";
		} else if (op == 3) {
			System.out.println("请输入要修改的性别");
			String sex = sc.next();
			sql += " set sex = '" + sex + "' ";
		} else {
			System.out.println("请输入要修改的手机号码");
			int phone = sc.nextInt();
			sql += " set phone = " + phone;
		}

		// 加上条件
		sql += " where number = '" + employee.getNumber() + "'";

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql

			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("修改成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}

	}

	@Override
	public Employee findCashierByNumber(String number) {
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from employee where role = 2 and remark = 1 and number='" + number + "'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setNumber(rs.getString("number"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setSex(rs.getString("sex"));
				employee.setPhone(rs.getString("phone"));
				employee.setRole(rs.getInt("role"));
				employee.setRemark(rs.getInt("remark"));

				return employee;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;

	}

	// 采购员管理
	@Override
	public List<Employee> findAllBuyer() {
		List<Employee> buyers = new ArrayList<>();
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from employee where role = 3 and remark = 1";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setNumber(rs.getString("number"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("password"));
				emp.setSex(rs.getString("sex"));
				emp.setPhone(rs.getString("phone"));
				emp.setRole(rs.getInt("role"));
				emp.setRemark(rs.getInt("remark"));
				buyers.add(emp);
			}
			return buyers;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}

	@Override
	public void addBuyer() {
		// 输入收银员信息
		Scanner sc = new Scanner(System.in);
		// 创建收银员对象
		Employee buyer = new Employee();
		System.out.println("请输入采购员编号");
		buyer.setNumber(sc.next());
		System.out.println("请输入采购员姓名");
		buyer.setUsername(sc.next());
		System.out.println("请输入密码");
		buyer.setPassword(sc.next());
		System.out.println("请输入性别");
		buyer.setSex(sc.next());

		System.out.println("请输入手机号码");
		buyer.setPhone(sc.next());
		

		// 角色
		buyer.setRole(3);
		buyer.setRemark(1);

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "insert into employee(number,username,password,sex,phone,role,remark)" + "values('"
					+ buyer.getNumber() + "','" + buyer.getUsername() + "','" + buyer.getPassword() + "','"
					+ buyer.getSex() + "'," + buyer.getPhone() + ",3," + buyer.getRemark() + ")";
			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("添加采购员成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}

	}

	@Override
	public void delBuyer() {
		// 提示输入编号
		System.out.println("请输入要删除采购员编号");
		Scanner sc = new Scanner(System.in);
		String num = sc.next();

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "update employee set remark = 0 where number = '" + num + "'";
			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("删除成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}

	}

	@Override
	public void updateBuyer(Employee employee) {
		System.out.println(employee);
		String sql = "update employee ";

		Scanner sc = new Scanner(System.in);
		// 修改
		System.out.println("1 修改姓名 2修改密码 3修改性别 4 修改手机号码 ");
		int op = sc.nextInt();
		if (op == 1) {
			System.out.println("请输入要修改的姓名");
			String name = sc.next();
			sql += " set username = '" + name + "' ";
		} else if (op == 2) {
			System.out.println("请输入要修改的密码");
			String password = sc.next();
			sql += " set password = '" + password + "' ";
		} else if (op == 3) {
			System.out.println("请输入要修改的性别");
			String sex = sc.next();
			sql += " set sex = '" + sex + "' ";
		} else {
			System.out.println("请输入要修改的手机号码");
			int phone = sc.nextInt();
			sql += " set phone = " + phone;
		}

		// 加上条件
		sql += " where number = '" + employee.getNumber() + "'";

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql

			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("修改成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}

	}

	@Override
	public Employee findBuyerByNumber(String number) {
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from employee where role = 3 and remark = 1 and number='" + number + "'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setNumber(rs.getString("number"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setSex(rs.getString("sex"));
				employee.setPhone(rs.getString("phone"));
				employee.setRole(rs.getInt("role"));
				employee.setRemark(rs.getInt("remark"));

				return employee;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}

	// 会员管理
	@Override
	public List<Vip> findAllVip() {
		List<Vip> vips = new ArrayList<>();
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from vip";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Vip vip = new Vip();
				vip.setV_number(rs.getString("v_number"));
				vip.setV_name(rs.getString("v_name"));
				vip.setV_score(rs.getInt("v_score"));
				vip.setV_phone(rs.getString("v_phone"));
				vip.setV_date(rs.getString("v_date"));

				vips.add(vip);
			}
			return vips;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}

	@Override
	public void addVip() {
		// 输入VIP信息
		Scanner sc = new Scanner(System.in);
		// 创建VIP对象
		Vip vip = new Vip();
		
		System.out.println("请输入VIP姓名");
		vip.setV_name(sc.next());
		System.out.println("请输入VIP的手机号码");
		vip.setV_phone(sc.next());
		
		//处理手机号，留下后4位
		String number = vip.getV_phone().substring(vip.getV_phone().length()-4, vip.getV_phone().length());
		// 处理日期
		String date = getDate();
		String str = date.replaceAll("-", "");
		System.out.println(str);
		// 拼接字符串
		String vip_num = "vip" + str + number;
		System.out.println(vip_num);

		vip.setV_number(vip_num);
		
		// 初始积分
		vip.setV_score(0);
		// 开通会员日期
		vip.setV_date(date);

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "insert into vip(v_number,v_name,v_score,v_phone,v_date)" + "values('" + vip.getV_number()
					+ "','" + vip.getV_name() + "'," + vip.getV_score() + ",'" + vip.getV_phone() + "','"
					+ vip.getV_date() + "')";
			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("添加会员成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}

	}

	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String strDate = sdf.format(d);
		return strDate;
	}

	@Override
	public void delVip() {
		// 提示输入手机号码
		System.out.println("请输入要删除会员手机号码");
		Scanner sc = new Scanner(System.in);
		String phone = sc.next();

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "delete from vip where v_phone = '" + phone + "'";
			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("删除成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}
	}

	@Override
	public void updateVip(Vip vip) {
		System.out.println(vip);
		String sql = "update vip ";

		Scanner sc = new Scanner(System.in);
		// 修改
		System.out.println("1 修改会员编号 2修改姓名 3修改积分 4 修改手机号码 5修改开通日期 ");
		int op = sc.nextInt();
		if (op == 1) {
			System.out.println("请输入要修改的会员编号");
			String number = sc.next();
			sql += " set v_number = '" + number + "' ";
		} else if (op == 2) {
			System.out.println("请输入要修改的姓名");
			String name = sc.next();
			sql += " set v_name = '" + name + "' ";
		} else if (op == 3) {
			System.out.println("请输入要修改的积分");
			int score = sc.nextInt();
			sql += " set v_score = "+ score ;
		} else if(op == 4){
			System.out.println("请输入要修改的手机号码");
			String phone = sc.next();
			sql += " set v_phone = '" + phone + "' ";
		}else if(op == 5){
			System.out.println("请输入要修改的开通日期(yyyy-MM-dd)：");
			String date = sc.next();
			sql += " set v_date = '" + date+ "' ";
		}

		// 加上条件
		sql += " where v_phone = '" + vip.getV_phone() + "'";

		Connection conn = null;
		Statement st = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql

			//System.out.println("sql:" + sql);
			st.executeUpdate(sql);
			System.out.println("修改成功...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, null);
		}

	}

	@Override
	public Vip findVipByPhone(String v_phone) {
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from vip where v_phone='" + v_phone + "'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Vip vip = new Vip();
				vip.setV_number(rs.getString("v_number"));
				vip.setV_name(rs.getString("v_name"));
				vip.setV_score(rs.getInt("v_score"));
				vip.setV_phone(rs.getString("v_phone"));
				vip.setV_date(rs.getString("v_date"));

				return vip;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}
	
	//查询超市的营业额
	//拿到数组
	public List<Sell_info> BusinessVolume() {
		//查询sell_info表的数据
		//尝试把表都连起来
		List<Sell_info> busv = new ArrayList<>();
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select g.c_name,g.c_price,g.vip_price,s.s_quantity,s.s_time,s.s_vip_number from sell_info s,goods g " + 
				"where g.c_number=s.s_c_number";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Sell_info sell = new Sell_info();
				sell.setC_name(rs.getString("c_name"));
				sell.setC_price(rs.getDouble("c_price"));
				sell.setVip_price(rs.getDouble("vip_price"));
				sell.setS_quantity(rs.getInt("s_quantity"));
				sell.setS_time(rs.getString("s_time"));
				sell.setS_vip_number(rs.getString("s_vip_number"));

				busv.add(sell);
			}
			return busv;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
		
	}

	//计算总营业额
	@Override
	public void viewAllBusiness(List<Sell_info> busv) {
		//对数组进行操作，查询，计算营业额
		//System.out.println(busv);
		//Sell_info s_vip_number = busv.get(4);
		//System.out.println(s_vip_number);
		Sell_info sell= null;
		double count = 0;
		for (int i = 0 ;i<busv.size();i++) {
			sell = busv.get(i);
			//System.out.println(sell);
			if(sell.getS_vip_number() == null) {
				double c_price = sell.getC_price();
				int s_quantity = sell.getS_quantity();
				//System.out.println(c_price);
				//System.out.println(s_quantity);
				double c = c_price * s_quantity;
				count += c;
			}else {
				double vip_price = sell.getVip_price();
				int s_quantity = sell.getS_quantity();
				//System.out.println(vip_price);
				//System.out.println(s_quantity);
				double c = vip_price * s_quantity;
				count += c;
			}		
		}
		System.out.println("总营业额为(元)："+count);
	}

	
	
	@Override
	public void viewAllBusinessByDay() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要查询的日期（1号，请输入：1）");
		int day = sc.nextInt();
		
		String sql = "select SUM(CASE WHEN s_vip_number is not null THEN vip_price * s_quantity ELSE c_price * s_quantity END)as 日营业额 "
				+ "from sell_info s,goods g where g.c_number=s.s_c_number and DAY(s_time)='"+ day +"'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				String busv = rs.getString("日营业额");
				if(busv != null) {
					System.out.println("日营业额为：" + busv);
				}else {
					System.out.println("该日没有营业额");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}

	}
	

	@Override
	public void viewAllBusinessByMonth() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要查询的月份(如1月：01)");
		int month = sc.nextInt();
		
		String sql = "select SUM(CASE WHEN s_vip_number is not null THEN vip_price * s_quantity ELSE c_price * s_quantity END)as 月营业额 "
				+ "from sell_info s,goods g where g.c_number=s.s_c_number and MONTH(s_time)='"+ month +"'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				String busv = rs.getString("月营业额");
				if(busv != null) {
					System.out.println("月营业额为：" + busv);
				}else {
					System.out.println("该月没有营业额");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}

	}

	
	@Override
	public void viewAllBusinessBySeason() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要查询的季度数：");
		System.out.println("（1月-3月）为第一季度：输入1...以此类推");
		int quarter = sc.nextInt();
		
		String sql = "select SUM(CASE WHEN s_vip_number is not null THEN vip_price * s_quantity ELSE c_price * s_quantity END)as 季度营业额 "
				+ "from sell_info s,goods g where g.c_number=s.s_c_number and QUARTER(s_time)='"+ quarter +"'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				String busv = rs.getString("季度营业额");
				if(busv != null) {
					System.out.println("季度营业额为：" + busv);
				}else {
					System.out.println("该季度没有营业额");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}	
	}

	
	
	@Override
	public void viewAllBusinessByYear() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要查询的年份");
		int year = sc.nextInt();
		
		String sql = "select SUM(CASE WHEN s_vip_number is not null THEN vip_price * s_quantity ELSE c_price * s_quantity END)as 年营业额 "
				+ "from sell_info s,goods g where g.c_number=s.s_c_number and YEAR(s_time)='"+ year +"'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				String busv = rs.getString("年营业额");
				if(busv != null) {
					System.out.println("年营业额为：" + busv);
				}else {
					System.out.println("该年没有营业额");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		
	}

	//出勤管理
	@Override
	public List<Clock_info> checkAllOnDuty() {
		List<Clock_info> clockinfos = new ArrayList<>();
		// 连接数据库查询
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from clock_info";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Clock_info cinfo = new Clock_info();
				cinfo.setClock_id(rs.getInt("clock_id"));
				cinfo.setEmployee_no(rs.getString("employee_no"));
				cinfo.setClock_in_time(rs.getString("clock_in_time"));
				cinfo.setClock_off_time(rs.getString("clock_off_time"));
				cinfo.setClock_date(rs.getString("clock_date"));

				clockinfos.add(cinfo);
			}
			return clockinfos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;		
	}

	
	@Override
	public  List<Clock_info> checkLatedIn() {
		List<Clock_info> lateinfos = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
			
		String sql = "select employee_no,clock_in_time,clock_date from clock_info "
				+ "where DATE_FORMAT(clock_in_time,'%H:%m:%s') BETWEEN '09:00:00' and '11:00:00'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Clock_info cinfo = new Clock_info();
				cinfo.setEmployee_no(rs.getString("employee_no"));
				cinfo.setClock_in_time(rs.getString("clock_in_time"));
				cinfo.setClock_date(rs.getString("clock_date"));
				lateinfos.add(cinfo);
			}
			return lateinfos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
		
	}

	@Override
	public List<Clock_info> checkEarlyOut() {
		
		List<Clock_info> earlyinfos = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String sql = "select employee_no,clock_off_time,clock_date from clock_info "
				+ "where DATE_FORMAT(clock_off_time,'%H:%m:%s') BETWEEN '16:00:00' and '18:00:00'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Clock_info cinfo = new Clock_info();
				cinfo.setEmployee_no(rs.getString("employee_no"));
				cinfo.setClock_off_time(rs.getString("clock_off_time"));
				cinfo.setClock_date(rs.getString("clock_date"));
				earlyinfos.add(cinfo);
		
			}
			return earlyinfos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		
		return null;
		
	}


	
	public List<Clock_info> checkAllMissing(){
		
		List<Clock_info> missinginfos = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String sql = "select employee_no,clock_in_time,clock_off_time,clock_date from clock_info "
				+ "where DATE_FORMAT(clock_off_time,'%H:%m:%s') < '16:00:00' "
				+ "or clock_in_time is null or clock_off_time is null "
				+ "or DATE_FORMAT(clock_in_time,'%H:%m:%s') > '11:00:00' "
				+ "or (clock_in_time is null and clock_off_time is null)";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Clock_info cinfo = new Clock_info();
				cinfo.setEmployee_no(rs.getString("employee_no"));
				cinfo.setClock_in_time(rs.getString("clock_in_time"));
				cinfo.setClock_off_time(rs.getString("clock_off_time"));
				cinfo.setClock_date(rs.getString("clock_date"));
				missinginfos.add(cinfo);
		
			}
			return missinginfos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}	
		return null;
		
	}
	
	@Override
	public List<Clock_info> checkAllOnDutyByDay(String date) {
		List<Clock_info> onedayinfos = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "select employee_no,clock_in_time,clock_off_time,clock_date from clock_info where clock_date = '" + date + "'";
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Clock_info cinfo = new Clock_info();
				cinfo.setEmployee_no(rs.getString("employee_no"));
				cinfo.setClock_in_time(rs.getString("clock_in_time"));
				cinfo.setClock_off_time(rs.getString("clock_off_time"));
				cinfo.setClock_date(rs.getString("clock_date"));
				onedayinfos.add(cinfo);
				
			}
			return onedayinfos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}
	
	
	@Override
	public List<Clock_info> checkLatedInByDay(String date) {
		List<Clock_info> lateinfos = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
			
		String sql = "select employee_no,clock_in_time,clock_date from clock_info "
				+ "where DATE_FORMAT(clock_in_time,'%H:%m:%s') BETWEEN '09:00:00' and '11:00:00' and clock_date ='"+ date +"'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Clock_info cinfo = new Clock_info();
				cinfo.setEmployee_no(rs.getString("employee_no"));
				cinfo.setClock_in_time(rs.getString("clock_in_time"));
				cinfo.setClock_date(rs.getString("clock_date"));
				lateinfos.add(cinfo);
			}
			return lateinfos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return null;
	}

	@Override
	public List<Clock_info> checkEarlyOutByDay(String date) {
		List<Clock_info> earlyinfos = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String sql = "select employee_no,clock_off_time,clock_date from clock_info "
				+ "where DATE_FORMAT(clock_off_time,'%H:%m:%s') BETWEEN '16:00:00' and '18:00:00' and clock_date ='"+ date +"'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Clock_info cinfo = new Clock_info();
				cinfo.setEmployee_no(rs.getString("employee_no"));
				cinfo.setClock_off_time(rs.getString("clock_off_time"));
				cinfo.setClock_date(rs.getString("clock_date"));
				earlyinfos.add(cinfo);
		
			}
			return earlyinfos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		
		return null;
	}

	@Override
	public List<Clock_info> checkAllMissingByDay(String date) {
		List<Clock_info> missinginfos = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String sql = "select employee_no,clock_in_time,clock_off_time,clock_date from clock_info "
				+ "where DATE_FORMAT(clock_off_time,'%H:%m:%s') < '16:00:00' "
				+ "or clock_in_time is null or clock_off_time is null "
				+ "or DATE_FORMAT(clock_in_time,'%H:%m:%s') > '11:00:00' "
				+ "or (clock_in_time is null and clock_off_time is null) having clock_date ='"+ date +"'";
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Clock_info cinfo = new Clock_info();
				cinfo.setEmployee_no(rs.getString("employee_no"));
				cinfo.setClock_in_time(rs.getString("clock_in_time"));
				cinfo.setClock_off_time(rs.getString("clock_off_time"));
				cinfo.setClock_date(rs.getString("clock_date"));
				missinginfos.add(cinfo);
		
			}
			return missinginfos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}	
		return null;
	}

	
	@Override
	public int countAllOnDutyByDay(String date) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "select COUNT(*) from clock_info where clock_date = '" + date + "'";
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println(date + "的打卡人数为：" + rs.getInt("COUNT(*)"));
				int count = rs.getInt("COUNT(*)");
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return 0;
	}

	
	
	@Override
	public int countLatedIn() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "select COUNT(*) from clock_info where DATE_FORMAT(clock_in_time,'%H:%m:%s') BETWEEN '09:00:00' and '11:00:00'";
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println( "累计迟到总人数为：" + rs.getInt("COUNT(*)"));
				int count = rs.getInt("COUNT(*)");
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return 0;
	}

	
	
	@Override
	public int countEarlyOut() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "select COUNT(*) from clock_info where DATE_FORMAT(clock_off_time,'%H:%m:%s') BETWEEN '16:00:00' and '18:00:00'";
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println( "累计早退总人数为：" + rs.getInt("COUNT(*)"));
				int count = rs.getInt("COUNT(*)");
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return 0;
	}

	@Override
	public int countMissing() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			st = conn.createStatement();
			// 准备sql
			String sql = "select COUNT(*) from clock_info where DATE_FORMAT(clock_off_time,'%H:%m:%s') < '16:00:00' "
					+ "or clock_in_time is null or clock_off_time is null "
					+ "or DATE_FORMAT(clock_in_time,'%H:%m:%s') > '11:00:00' "
					+ "or (clock_in_time is null and clock_off_time is null)";
			//System.out.println("sql:" + sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println( "累计旷工总人数为：" + rs.getInt("COUNT(*)"));
				int count = rs.getInt("COUNT(*)");
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, st, rs);
		}
		return 0;
	}

	
}
