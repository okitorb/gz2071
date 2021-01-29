package com.gec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gec.dao.NoticeDao;
import com.gec.domain.Notice;
import com.gec.query.NoticeQueryObject;
import com.gec.util.JDBCUtil;

public class NoticeDaoImpl implements NoticeDao {

	@Override
	public List<Notice> findNoticesByPage(NoticeQueryObject notice) {
		// 准备Sql 方便添加后续条件
		StringBuilder sql = new StringBuilder(
				"select n.*,u.username username, t.name typename from notice_inf n,user_inf u,type_inf t "
						+ "where 1=1 and n.user_id=u.id and t.id = n.type_id and n.content != '已删除'");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (notice != null && !"".equals(notice.getName()) && notice.getName() != null) {
			sql.append(" and n.name like ? ");
			cond.add("%" + notice.getName() + "%");
		}

		// System.out.println("user:" + user);

		// 加上limit
		sql.append(" limit ").append(notice.getStartRow()).append(",").append(notice.getPageSize());

		// 转数组 list转数组后
		Object[] strs = cond.toArray(new Object[cond.size()]);

		ResultSet rs = null;
		try {
			rs = JDBCUtil.executeQuery(sql.toString(), strs);

			List<Notice> notices = new ArrayList<>();
			while (rs.next()) {
				Notice n = new Notice();
				n.setId(rs.getInt("id"));
				n.setName(rs.getString("name"));
				n.setCreate_date(rs.getDate("create_date"));
				n.setUsername(rs.getString("username"));
				n.setContent(rs.getString("content"));
				n.setTypename(rs.getString("typename"));

				notices.add(n);
			}
			return notices;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}

		return null;
	}

	@Override
	public int getTotalNoticesCount(NoticeQueryObject notice) {
		// 准备Sql 方便添加后续条件 count(列名｜*｜数字) from xxx 统计这个表的记录数
		StringBuilder sql = new StringBuilder("select count(*) from notice_inf n,user_inf u,type_inf t "
				+ "where 1=1 and n.user_id=u.id and t.id = n.type_id and n.content != '已删除'");

		// 组装动态条件
		List<Object> cond = new ArrayList<>();

		// 设置参数
		if (notice != null && !"".equals(notice.getName()) && notice.getName() != null) {
			sql.append(" and n.name like ? ");
			cond.add("%" + notice.getName() + "%");
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
	public void save(Notice notice) {
		String sql = "insert into notice_inf(name,create_date,type_id,content,user_id,modify_date)values(?,?,?,?,?,?)";

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modify_date = sdf.format(date);
		String create_date = sdf.format(date);

		Object[] strs = { notice.getName(), create_date, notice.getType_id().toString(), notice.getContent(),
				notice.getUser_id().toString(), modify_date };

		try {
			JDBCUtil.executeOrUpdate(sql, strs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}
	}

	@Override
	public Notice findNoticeById(Integer id) {
		String sql = "select n.*,u.username username, t.name typename from notice_inf n,user_inf u,type_inf t "
				+ "where 1=1 and n.user_id=u.id and t.id = n.type_id and n.content != '已删除' and n.id=?";
		String[] strs = { id.toString() };
		ResultSet rs = JDBCUtil.executeQuery(sql, strs);
		try {
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setId(rs.getInt("id"));
				notice.setName(rs.getString("name"));
				notice.setType_id(rs.getInt("type_id"));
				notice.setUser_id(rs.getInt("user_id"));
				notice.setTypename(rs.getString("typename"));
				notice.setCreate_date(rs.getDate("create_date"));
				notice.setModify_date(rs.getDate("modify_date"));
				notice.setUsername(rs.getString("username"));
				notice.setContent(rs.getString("content"));

				return notice;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), rs);
		}
		return null;
	}

	@Override
	public void update(Notice notice) {

		// 准备Sql
		String sql = "update notice_inf set name=?,content=?,type_id=?,modify_date=? where content != '已删除' and id = ?";

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modify_date = sdf.format(date);

		// 准备参数
		String[] strs = { notice.getName(), notice.getContent(), notice.getType_id().toString(), modify_date,
				notice.getId().toString() };
		// 调用excuteOrupdate
		try {
			JDBCUtil.executeOrUpdate(sql, strs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}
	}

	@Override
	public void delete(Integer id) {
		String sql = "update notice_inf set content = '已删除' where id = ?";
		String[] args = { id.toString() };

		try {
			JDBCUtil.executeOrUpdate(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(JDBCUtil.getConn(), JDBCUtil.getPs(), null);
		}

	}


}
