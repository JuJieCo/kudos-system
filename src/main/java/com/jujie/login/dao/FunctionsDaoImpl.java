package com.jujie.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.objectweb.asm.xwork.Type;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.global.util.DataUtil;
import com.jujie.login.Functions;

public class FunctionsDaoImpl extends BaseJdbcDao {
	
	public int saveFunctions(Functions functions) throws Exception {
		final String sql = "insert into functions(fun_name,fun_url,fun_pid,fun_sign) values(?,?,?,?)";
		final Object[] objs = {functions.getFunName(),functions.getFunUrl(),functions.getFunPID(),functions.getFunSign()};
	    KeyHolder keyHolder = new GeneratedKeyHolder();     
		try{
			this.getJdbcTemplate().update(new PreparedStatementCreator(){         
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
					int i = 0;
					int n = 0;
					PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);                                                 
					ps.setString(++i, DataUtil.getStringK(objs[n++]));    
					ps.setString(++i, DataUtil.getStringK(objs[n++]));  
					if(DataUtil.getInt(objs[n])==0){
						ps.setNull(++i, Type.INT);
					}else{
						ps.setInt(++i, DataUtil.getInt(objs[n++]));  
					}
					ps.setString(++i, DataUtil.getStringK(objs[n++]));
					return ps;    
				}             
			}, keyHolder);    
		}catch(Exception e){
			e.printStackTrace();
		}
		return keyHolder.getKey().intValue();
	}
	
	public void editFunctions(Functions functions) throws Exception {
		String sql = "update functions set fun_name=?,fun_url=?,fun_pid=?,fun_sign=? where uuid=?";
		Object[] objs = {functions.getFunName(),functions.getFunUrl(),functions.getFunPID()==0?null:functions.getFunPID(),functions.getFunSign(),functions.getUuid()};
		this.getJdbcTemplate().update(sql, objs);
	}
	
	public void deleteFunctions(int uuid){
		String[] sqls = new String[2];
		sqls[0] = "delete from role_fun where fun_uuid="+uuid;
		sqls[1] = "delete from functions where uuid="+uuid;
		this.getJdbcTemplate().batchUpdate(sqls);
	}
	
	public Functions getOneFunctions(int uuid) throws Exception {
		String sql = "select *,uuid fun_uuid from functions where uuid="+uuid;
		List<Functions> list = this.getJdbcTemplate().query(sql, new Functions());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public List<Functions> getFunctionssByRole(int RoleUuid) throws Exception {
		String sql = "select f.*,f.uuid fun_uuid from functions f inner join role_fun rf on f.uuid=rf.fun_uuid where rf.role_uuid="+RoleUuid+" order by f.fun_pid desc";
		return this.getJdbcTemplate().query(sql, new Functions());
	}
	
	public List<Functions> getAllFunctions() throws Exception {
		String sql = "select *,uuid fun_uuid from functions order by fun_pid desc";
		return this.getJdbcTemplate().query(sql, new Functions());
	}
	
	public List<Functions> getAllFunctionsByLogin(int loginUuid) throws Exception{
		String sql = "select distinct f.*,f.uuid fun_uuid from functions f inner join role_fun rf on f.uuid=rf.fun_uuid "
					+" inner join role r on rf.role_uuid=r.uuid inner join login_role lr on r.uuid=lr.role_uuid "
					+" inner join login l on lr.login_uuid=l.uuid "
					+" where l.uuid="+loginUuid+" order by f.fun_pid desc ";
		return this.getJdbcTemplate().query(sql, new Functions());
	}
}
