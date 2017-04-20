package com.jujie.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.global.util.DataUtil;
import com.jujie.global.util.Page;
import com.jujie.global.util.PageUtils;
import com.jujie.login.Functions;
import com.jujie.login.Role;

public class RoleDaoImpl extends BaseJdbcDao {
	
	public int saveRole(Role role) throws Exception {
		final String sql = "insert into role(role_name) values(?)";
		final Object[] objs = {role.getRoleName()};
	    KeyHolder keyHolder = new GeneratedKeyHolder();     
		try{
			this.getJdbcTemplate().update(new PreparedStatementCreator(){         
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
					int i = 0;
					int n = 0;
					PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);                                                 
					ps.setString(++i, DataUtil.getStringK(objs[n++]));    
					return ps;    
				}             
			}, keyHolder);    
		}catch(Exception e){
			e.printStackTrace();
		}
		return keyHolder.getKey().intValue();
	}
	
	public void editRole(Role role) throws Exception {
		String sql = "update role set role_name=? where uuid=?";
		Object[] objs = {role.getRoleName(),role.getUuid()};
		this.getJdbcTemplate().update(sql, objs);
	}
	
	public void deleteRole(int uuid){
		String[] sqls = new String[3];
		sqls[0] = "delete from role_fun where role_uuid="+uuid;
		sqls[1] = "delete from login_role where role_uuid="+uuid;
		sqls[2] = "delete from role where uuid="+uuid;
		this.getJdbcTemplate().batchUpdate(sqls);
	}
	
	public void authorizeRole(Role role){
		String[] sqls = new String[role.getFunList().size()+1];
		int i = 0;
		sqls[i++] = "delete from role_fun where role_uuid="+role.getUuid();
		for (Functions functions : role.getFunList()) {
			sqls[i++] = "insert into role_fun(role_uuid,fun_uuid) values("+role.getUuid()+","+functions.getUuid()+")";
		}
		this.getJdbcTemplate().batchUpdate(sqls);
	}
	
	public Role getOneRole(int uuid) throws Exception {
		String sql = "select *,uuid role_uuid from role where uuid="+uuid;
		List<Role> list = this.getJdbcTemplate().query(sql, new Role());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public List<Role> getRolesByLogin(int loginUuid) throws Exception {
		String sql = "select r.*,r.uuid role_uuid from role r inner join login_role lr on r.uuid=lr.role_uuid where lr.login_uuid="+loginUuid;
		return this.getJdbcTemplate().query(sql, new Role());
	}
	
	public List<Role> getAllRoles(Object[] objs,Page page) throws Exception {
		String sql = "select *,uuid role_uuid from role where 1=1 and uuid!=1 ";
		
		List<Object> list = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(DataUtil.getStringK(objs[0]))){
				sql += " and role_name like '%'||?||'%' ";
				list.add(objs[0]);
			}
		}
		sql += " order by uuid desc ";
		return this.getJdbcTemplate().query(PageUtils.fyPage(sql, list.toArray(), page, this.getJdbcTemplate(), Page.DATABASE_TYPE_MSSQL),list.toArray(), new Role());
	}
	
}
