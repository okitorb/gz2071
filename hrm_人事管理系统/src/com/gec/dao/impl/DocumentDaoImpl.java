package com.gec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gec.dao.DeptDao;
import com.gec.dao.DocumentDao;
import com.gec.domain.Dept;
import com.gec.domain.Document;
import com.gec.domain.User;
import com.gec.query.DeptQueryObject;
import com.gec.query.DocQueryObject;
import com.gec.util.JDBCUtil;

public class DocumentDaoImpl implements DocumentDao {

	@Override
	public List<Document> findDocsByPage(DocQueryObject doc) {
		// 准备Sql 方便添加后续条件
		StringBuilder sql = new StringBuilder("select d.*,u.username username from document_inf d,user_inf u "
				+ "where 1=1 and d.user_id=u.id and d.remark != '已删除'");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (doc != null && !"".equals(doc.getTitle()) && doc.getTitle() != null) {
			sql.append(" and title like ? ");
			cond.add("%" + doc.getTitle() + "%");
		}

		// System.out.println("user:" + user);

		// 加上limit
		sql.append(" limit ").append(doc.getStartRow()).append(",").append(doc.getPageSize());

		// 转数组 list转数组后
		Object[] strs = cond.toArray(new Object[cond.size()]);

		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql.toString(), strs);

			List<Document> docs = new ArrayList<>();
			while (rs.next()) {
				Document d = new Document();
				d.setId(rs.getInt("id"));
				d.setTitle(rs.getString("title"));
				d.setCreate_date(rs.getDate("create_date"));
				d.setUsername(rs.getString("username"));
				d.setRemark(rs.getString("remark"));

				docs.add(d);
			}
			return docs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}

		return null;
	}

	@Override
	public int getTotalDocsCount(DocQueryObject doc) {
		// 准备Sql 方便添加后续条件 count(列名｜*｜数字) from xxx 统计这个表的记录数
		StringBuilder sql = new StringBuilder("select count(*) from document_inf d,user_inf u where 1=1 and d.user_id=u.id and d.remark != '已删除'");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (doc != null && !"".equals(doc.getTitle()) && doc.getTitle() != null) {
			sql.append(" and title like ? ");
			cond.add("%" + doc.getTitle() + "%");
		}

		// 转数组 list转数组后
		Object[] strs = cond.toArray(new Object[cond.size()]);

		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql.toString(), strs);
			while (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return 0;
	}

	@Override
	public void save(Document doc) {
		String sql = "insert into document_inf(title,filename,filetype,filebytes,remark,create_date,user_id)values(?,?,?,?,?,?,?)";

		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		
		Object[] strs = { doc.getTitle(),doc.getFilename(),doc.getFiletype(),doc.getFilebytes(),doc.getRemark(),ts.toString(),doc.getUser_id().toString()};

		try {
			JDBCUtil.executeOrUpdate(sql, strs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}
	}

	@Override
	public Document findDocById(Integer id) {
		String sql = "select d.*,u.username username from document_inf d,user_inf u "
				+ "where 1=1 and d.user_id=u.id and d.remark != '已删除' and d.id=?";
		String[] strs = { id.toString() };
		ResultSet rs = JDBCUtil.executeQuery(sql, strs);
		try {
			while (rs.next()) {
				Document doc = new Document();
				doc.setId(rs.getInt("id"));
				doc.setTitle(rs.getString("title"));
				doc.setFilename(rs.getString("filename"));
				doc.setFiletype(rs.getString("filetype"));
				doc.setFilebytes(rs.getBytes("filebytes"));
				doc.setCreate_date(rs.getDate("create_date"));
				doc.setUsername(rs.getString("username"));
				doc.setRemark(rs.getString("remark"));
				
				return doc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return null;
	}

	
	@Override
	public void update(Document doc) {

		// 调用excuteOrupdate
		try {
			if(doc.getFilename() == null || "".equals(doc.getFilename())) {
				String sql2 ="update document_inf set title=?,remark=? where remark != '已删除' and id = ?";
				String[] strs2 = { doc.getTitle(), doc.getRemark(),doc.getId().toString() };
				JDBCUtil.executeOrUpdate(sql2, strs2);
			}else {
				// 准备Sql
				String sql = "update document_inf set title=?,remark=?,filename=?,filetype=?,filebytes=? where remark != '已删除' and id = ?";
				// 准备参数
				String[] strs = { doc.getTitle(), doc.getRemark(),doc.getFilename(),doc.getFiletype(),doc.getFilebytes().toString(),doc.getId().toString() };
				JDBCUtil.executeOrUpdate(sql, strs);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}
	}

	@Override
	public void delete(Integer id) {
		String sql = "update document_inf set remark = '已删除' where id = ?";
		String[] args = { id.toString() };

		try {
			JDBCUtil.executeOrUpdate(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}
		
	}

	@Override
	public List<Document> findAllDocs() {
		String sql = "select * from dept_inf where remark != '已删除'";
		List<Document> docs = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql, null);
			while (rs.next()) {
				// 创建部门对象
				Document doc = new Document();
				doc.setId(rs.getInt("id"));
				doc.setFilename(rs.getString("filename"));
				doc.setRemark(rs.getString("remark"));

				docs.add(doc);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return docs;
	}

}
