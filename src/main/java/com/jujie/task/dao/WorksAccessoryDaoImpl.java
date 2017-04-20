package com.jujie.task.dao;

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
import com.jujie.task.WorksAccessory;
public class WorksAccessoryDaoImpl extends BaseJdbcDao {

	public List<WorksAccessory> queryTaskAccessory(int task_uuid) throws Exception{
		final List<WorksAccessory> worksAccessoryList = new ArrayList<WorksAccessory>();
		String sql="select * from work_accessory where task_uuid="+task_uuid;
		this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				WorksAccessory worksAccessory = new WorksAccessory();
				worksAccessory.setUuid(rs.getInt("uuid"));
				worksAccessory.setAccessorySize(rs.getString("accessory_size"));
				worksAccessory.setAccessoryType(rs.getString("accessory_type"));
				worksAccessory.setFilename(rs.getString("filename"));
				worksAccessory.setRoute(rs.getString("route"));
				worksAccessory.setTaskUuid(rs.getInt("task_uuid"));
				worksAccessoryList.add(worksAccessory);
	 	}
    });
		return worksAccessoryList;
	}
	
	public WorksAccessory queryOneTaskAccessory(int uuid) throws Exception{
		final WorksAccessory worksAccessory = new WorksAccessory();
		String sql="select * from work_accessory where uuid="+uuid;
		this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				worksAccessory.setUuid(rs.getInt("uuid"));
				worksAccessory.setAccessorySize(rs.getString("accessory_size"));
				worksAccessory.setAccessoryType(rs.getString("accessory_type"));
				worksAccessory.setFilename(rs.getString("filename"));
				worksAccessory.setRoute(rs.getString("route"));
				worksAccessory.setTaskUuid(rs.getInt("task_uuid"));
	 	}
    });
		return worksAccessory;
	}
	 
	public int saveTaskAccessory(WorksAccessory worksAccessory) throws Exception{
		final String sql = "insert into work_accessory(task_uuid,route,filename,accessory_type,accessory_size) values(?,?,?,?,?)";
	    final Object[] objs = {worksAccessory.getTaskUuid(),worksAccessory.getRoute(),worksAccessory.getFilename(),worksAccessory.getAccessoryType(),worksAccessory.getAccessorySize()};
	    KeyHolder keyHolder = new GeneratedKeyHolder();     
		try{
			this.getJdbcTemplate().update(new PreparedStatementCreator(){         
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
					int i = 0;
					int n = 0;
					PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);                                                 
					ps.setInt(++i, DataUtil.getInt(objs[n++]));  
					ps.setString(++i, DataUtil.getStringK(objs[n++]));    
					ps.setString(++i, DataUtil.getStringK(objs[n++]));    
					ps.setString(++i, DataUtil.getStringK(objs[n++]));    
					ps.setString(++i, DataUtil.getStringK(objs[n++]));    
					return ps;                   
				}             
			}, keyHolder);    
		}catch(Exception e){
			e.printStackTrace();
		}
		return keyHolder.getKey().intValue();  
	}
	public void updateTaskAccessory(WorksAccessory worksAccessory) throws Exception{
		String sql ="update work_accessory set task_uuid=?,route=?,filename=?,accessory_type=?,accessory_size=? where uuid="+worksAccessory.getUuid();
	    final Object[] objs = {worksAccessory.getTaskUuid(),worksAccessory.getRoute(),worksAccessory.getFilename(),worksAccessory.getAccessoryType(),worksAccessory.getAccessorySize()};
		this.getJdbcTemplate().update(sql,objs);
	}
	public void deleteTaskAccessory(int uuid) throws Exception{
		String sql ="delete from work_accessory where uuid="+uuid;
	  	this.getJdbcTemplate().update(sql);
	}
 
	public void deleteTaskAccessoryByTask(int taskUuid) throws Exception{
		String sql ="delete from work_accessory where task_uuid="+taskUuid;
	  	this.getJdbcTemplate().update(sql);
	}

}
