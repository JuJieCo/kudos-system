package com.jujie.user.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.global.util.DataUtil;
import com.jujie.global.util.Page;
import com.jujie.global.util.PageUtils;
import com.jujie.user.User;

public class UserDaoImpl extends BaseJdbcDao {
		
	public User queryOneUser(int uuid) throws Exception{
		final User user = new User();
		String sql = "select * from execute_user where uuid="+uuid;
		this.getJdbcTemplate().query(sql,new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				user.setUuid(rs.getInt("uuid"));
				user.setExecuteUserName(rs.getString("execute_userName"));
				user.setExecuteUserCode(rs.getString("execute_userCode"));
				user.setExecuteDept(rs.getString("execute_dept"));
				user.setExecuteStatus(rs.getInt("execute_status"));
				user.setExecuteRank(rs.getInt("execute_rank"));
			}
		});
		return user;
	}
	public User queryOneLeader(int taskuuid) throws Exception{
		final User user = new User();
		String sql = "select leader.* from task_leader taskleader left join works_task task on taskleader.task_uuid=task.uuid left join execute_user leader on taskleader.leader_uuid=leader.uuid  where task.uuid="+taskuuid;
		this.getJdbcTemplate().query(sql,new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				user.setUuid(rs.getInt("uuid"));
				user.setExecuteUserName(rs.getString("execute_userName"));
				user.setExecuteUserCode(rs.getString("execute_userCode"));
				user.setExecuteDept(rs.getString("execute_dept"));
				user.setExecuteStatus(rs.getInt("execute_status"));
				user.setExecuteRank(rs.getInt("execute_rank"));
			}
		});
		return user;
	}
	
	public List<User> queryAllDept() throws Exception{
		final List<User> deptList = new ArrayList<User>();
		String sql = "select DISTINCT execute_dept from execute_user ";
	    this.getJdbcTemplate().query(sql,new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				user.setExecuteDept(rs.getString("execute_dept"));
				deptList.add(user);
			}
		});
		return deptList;
	}
	
	public List<User> queryAllUser(Object[] cons,Page page) throws Exception{
		final List<User> userList = new ArrayList<User>();
		String sql = "select * from execute_user where 1=1 and uuid!=1 and execute_status=1 ";
		List<Object> conList = new ArrayList<Object>();
		if(cons!=null&&cons.length>0){
			if(cons[0]!=null&&!"".equals(cons[0].toString())){
				sql += "and execute_dept=? ";
				conList.add(cons[0]);
			}
		}
		sql +=" order by uuid desc ";
		Object[] objs = conList.toArray();
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, objs, page, this.getJdbcTemplate(), Page.DATABASE_TYPE_MSSQL),objs,new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				user.setUuid(rs.getInt("uuid"));
				user.setExecuteUserName(rs.getString("execute_userName"));
				user.setExecuteUserCode(rs.getString("execute_userCode"));
				user.setExecuteDept(rs.getString("execute_dept"));
				user.setExecuteRank(rs.getInt("execute_rank"));
				user.setExecuteStatus(rs.getInt("execute_status"));
				userList.add(user);
			}
		});
		return userList;
	}
	
	public int saveOneUser(User user) throws Exception{
		final String sql = "insert into execute_user(execute_dept,execute_userCode,execute_userName,execute_rank,execute_status) values(?,?,?,?,?)";
		final Object[] objs = {user.getExecuteDept(),user.getExecuteUserCode(),user.getExecuteUserName(),user.getExecuteRank()};
		
		KeyHolder keyHolder = new GeneratedKeyHolder();     
		try{
			this.getJdbcTemplate().update(new PreparedStatementCreator(){         
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
					int i = 0;
					int n = 0;
					PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);                                                 
					ps.setString(++i, DataUtil.getStringK(objs[n++]));    
					ps.setString(++i, DataUtil.getStringK(objs[n++]));    
					ps.setString(++i, DataUtil.getStringK(objs[n++]));    
					ps.setInt(++i, DataUtil.getInt(objs[n++]));    
					ps.setInt(++i, 1);    
					return ps;                   
				}             
			}, keyHolder);    
		}catch(Exception e){
			e.printStackTrace();
		}
		return keyHolder.getKey().intValue();     
	}
	
	public void editOneUser(User user) throws Exception{
		final String sql = "update execute_user set execute_dept=?,execute_userCode=?,execute_userName=?,execute_rank=? where uuid=?";
		final Object[] objs = {user.getExecuteDept(),user.getExecuteUserCode(),user.getExecuteUserName(),user.getExecuteRank(),user.getUuid()};
		this.getJdbcTemplate().update(sql,objs);   
	}
	
	public void deleteOneUser(int uuid) throws Exception{
		//String sql = "delete from execute_user where uuid="+uuid;
		String sql = "update execute_user set execute_status=0 where uuid="+uuid;
		this.getJdbcTemplate().update(sql);
	}
	
	public List<User> querySafferByuuid(final int uuid) throws Exception{
		final String sql = "select saffer.* from execute_user saffer where saffer.uuid in (select saffer_uuid from task_saffer where task_uuid="+uuid+") and saffer.uuid!=1 ";
		final List<User> userList = new ArrayList<User>();
		this.getJdbcTemplate().query(sql,new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				user.setUuid(rs.getInt("uuid"));
				user.setExecuteUserName(rs.getString("execute_userName"));
				user.setExecuteUserCode(rs.getString("execute_userCode"));
				user.setExecuteDept(rs.getString("execute_dept"));
				user.setExecuteRank(rs.getInt("execute_rank"));
				userList.add(user);
			}
		});
		return userList;
	 }
}
