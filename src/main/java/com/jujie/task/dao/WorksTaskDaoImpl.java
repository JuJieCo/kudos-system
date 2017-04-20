package com.jujie.task.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.global.util.DataUtil;
import com.jujie.global.util.DateUtil;
import com.jujie.global.util.Page;
import com.jujie.global.util.PageUtils;
import com.jujie.task.WorksTask;
import com.jujie.user.User;
import com.thewebpagestudio.util.DataUtils;

import java.sql.Timestamp;
public class WorksTaskDaoImpl extends BaseJdbcDao {

	public WorksTask queryTask(final int uuid) throws Exception{
		final WorksTask task = new WorksTask();
		String sql="select task.task_code,task.title,task.task_content,task.task_level,task.start_time,task.finish_time,"
					+"task.issued_time,task.status,task.hold1,task.hold2,task.remark,"
					+"saffer.uuid saffer_uuid,saffer.execute_dept saffer_dept,saffer.execute_userName saffer_name,"
					+"saffer.execute_rank saffer_rank,"
					+"leader.uuid leader_uuid,leader.execute_dept leader_dept,leader.execute_userName leader_name,"
					+"leader.execute_rank leader_rank "
					+"from works_task task "
					+"inner join task_saffer tasksaffer on task.uuid =tasksaffer.task_uuid "
					+"inner join execute_user saffer on tasksaffer.saffer_uuid=saffer.uuid "
					+"inner join task_leader taskleader on task.uuid =taskleader.task_uuid "
					+"inner join execute_user leader on taskleader.leader_uuid=leader.uuid  "
					+"where task.uuid="+uuid;
		
		this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				task.setUuid(uuid);
				task.setTaskCode(rs.getString("task_code"));
				task.setTitle(rs.getString("title"));
				task.setTaskContent(rs.getString("task_content"));
				task.setTaskLevel(rs.getInt("task_level"));
				task.setStartTime(rs.getTimestamp("start_time"));
				task.setFinishTime(rs.getTimestamp("finish_time"));
				task.setIssuedTime(rs.getTimestamp("issued_time"));
				task.setStatus(rs.getInt("status"));
				task.setHold1(rs.getString("hold1"));
				task.setHold2(rs.getString("hold2"));
				task.setRemark(rs.getString("remark"));
  	}
	  });
		return task;
	}
	
	
	public List<User> queryExeueByTaskuuid(int uuid) throws Exception{
	 
		final List<User> userList = new ArrayList<User>();
		 String sql="select saffer.uuid ,saffer.execute_dept executeDept from execute_user saffer,task_saffer tasksaffer,works_task task where saffer.uuid=tasksaffer.saffer_uuid and task.uuid=tasksaffer.task_uuid and task.uuid="+uuid;
		
		 this.getJdbcTemplate().query(sql, new RowCallbackHandler(){
		 public void processRow(ResultSet rs) throws SQLException{
			 User user = new User ();
			 user.setUuid(rs.getInt("uuid"));
			 user.setExecuteDept(rs.getString("executeDept"));
			 userList.add(user);
 
		 }
	  });
		return userList;
	 }
	public List<WorksTask> queryTaskAll(Object[] objs,Page page) throws Exception{
		String where = " where 1=1 ";
		List<Object> obj = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			if(objs.length>=1&&objs[0]!=null&&!"".equals(objs[0])){
				where += " and task.title like '%'||?||'%' ";
				obj.add(objs[0]);
			}
			if(objs.length>=2&&objs[1]!=null&&!"".equals(objs[1])){
				where +=" and convert(varchar(10),task.start_time,120) >= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[1], "-"));
				
			}
			if(objs.length>=3&&objs[2]!=null&&!"".equals(objs[2])){
				where +=" and convert(varchar(10),task.finish_time,120) <= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[2], "-"));
		    }
			if(objs.length>=4&&objs[3]!=null&&!"".equals(objs[3])){
				if(DataUtil.getInt(objs[3])==-100){
					where +=" and  task.status!= 0 ";
				}else if(DataUtil.getInt(objs[3])==-1000){
					where +="";
				}else{
					where +=" and  task.status = ? ";
					obj.add(objs[3]);
				}
		    }
			if(objs.length>=5&&objs[4]!=null&&!"".equals(objs[4])){
				where +=" and leader.leader_uuid= ? ";
				obj.add(objs[4]);
		    }
			
			
		 }
		final List<WorksTask> taskList = new ArrayList<WorksTask>();
		 String sql="select task.uuid,task.task_code,task.title,task.task_content,task.task_level,task.start_time,task.finish_time,"
			+"task.issued_time,task.status,task.hold1,task.hold2,task.remark "
			+"from works_task task, task_leader leader "
			+where+" and task.uuid=leader.task_uuid order by task.start_time desc";
		
		 this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), Page.DATABASE_TYPE_MSSQL),obj.toArray(), new RowCallbackHandler(){
		 public void processRow(ResultSet rs) throws SQLException{
			WorksTask task = new WorksTask ();
			task.setUuid(rs.getInt("uuid"));
			task.setTaskCode(rs.getString("task_code"));
			task.setTitle(rs.getString("title"));
			task.setTaskContent(rs.getString("task_content"));
			task.setTaskLevel(rs.getInt("task_level"));
			task.setStartTime(rs.getTimestamp("start_time"));
			task.setFinishTime(rs.getTimestamp("finish_time"));
			task.setIssuedTime(rs.getTimestamp("issued_time"));
			task.setStatus(rs.getInt("status"));
			task.setHold1(rs.getString("hold1"));
			task.setHold2(rs.getString("hold2"));
			task.setRemark(rs.getString("remark"));
			taskList.add(task);
 
		 }
	  });
		return taskList;
	 }
	public List<WorksTask> queryTaskBySearch(Object[] objs,Page page) throws Exception{
		String where = " where 1=1 ";
		List<Object> obj = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			if(objs.length>=1&&objs[0]!=null&&!"".equals(objs[0])){
				where += " and task.title like '%'||?||'%' ";
				obj.add(objs[0]);
			}
			if(objs.length>=2&&objs[1]!=null&&!"".equals(objs[1])){
				where +=" and convert(varchar(10),task.start_time,120) >= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[1], "-"));
				
			}
			if(objs.length>=3&&objs[2]!=null&&!"".equals(objs[2])){
				where +=" and convert(varchar(10),task.finish_time,120) <= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[2], "-"));
		    }
			if(objs.length>=4&&objs[3]!=null&&!"".equals(objs[3])){
				if(DataUtil.getInt(objs[3])==-100){
					where +=" and  task.status!= 0 ";
				}else if(DataUtil.getInt(objs[3])==-1000){
					where +="";
				}else{
					where +=" and  task.status = ? ";
					obj.add(objs[3]);
				}
		    }
			if(objs.length>=5&&objs[4]!=null&&!"".equals(objs[4])){
				where +=" and  saffer.execute_dept= ? ";
				obj.add(objs[4]);
		    }
			if(objs.length>=6&&objs[5]!=null&&!"".equals(objs[5])){
				where +=" and  saffer.uuid= ? ";
				obj.add(objs[5]);
		    }
			if(objs.length>=7&&objs[6]!=null&&!"".equals(objs[6])){
				where +=" and leader.leader_uuid= ? ";
				obj.add(objs[6]);
		    }
			
		 }
		final List<WorksTask> taskList = new ArrayList<WorksTask>();
		 String sql="select task.uuid,task.task_code,task.title,task.task_content,task.task_level,task.start_time,task.finish_time,leaderuser.execute_userName leaderName,leaderuser.execute_dept leaderdept,"
			+"task.issued_time,task.status,task.hold1,task.hold2,task.remark ,saffer.execute_userName executeUserName,saffer.execute_dept executeDept "
			+"from works_task task left join task_saffer tasksaffer on task.uuid =tasksaffer.task_uuid left join execute_user saffer on tasksaffer.saffer_uuid=saffer.uuid "
			+"left join task_leader leader on task.uuid=leader.task_uuid "
			+"left join execute_user leaderuser on leader.leader_uuid=leaderuser.uuid "
			+where+" order by task.start_time desc";
		
		 this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), Page.DATABASE_TYPE_MSSQL),obj.toArray(), new RowCallbackHandler(){
		 public void processRow(ResultSet rs) throws SQLException{
			WorksTask task = new WorksTask ();
			task.setUuid(rs.getInt("uuid"));
			task.setTaskCode(rs.getString("task_code"));
			task.setTitle(rs.getString("title"));
			task.setTaskContent(rs.getString("task_content"));
			task.setTaskLevel(rs.getInt("task_level"));
			task.setStartTime(rs.getTimestamp("start_time"));
			task.setFinishTime(rs.getTimestamp("finish_time"));
			task.setIssuedTime(rs.getTimestamp("issued_time"));
			task.setStatus(rs.getInt("status"));
			task.setHold1(rs.getString("hold1"));
			task.setHold2(rs.getString("hold2"));
			task.setRemark(rs.getString("remark"));
			task.getLeader().setExecuteDept(rs.getString("leaderdept"));
			task.getLeader().setExecuteUserName(rs.getString("leaderName"));
			User user =new User();
			user.setExecuteDept(rs.getString("executeDept"));
			user.setExecuteUserName(rs.getString("executeUserName"));
			task.getSafferList().add(user);
			taskList.add(task);
 
		 }
	  });
		return taskList;
	 }
	public List<WorksTask> queryWorkRemind(final Object[] objs,Page page) throws Exception{
		String where = " ";
		List<Object> obj = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(objs[0])){
				where += " and task.title like '%'||?||'%' ";
				obj.add(objs[0]);
			}
			if(objs[1]!=null&&!"".equals(objs[1])){
				where +=" and convert(varchar(10),task.start_time,120) >= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[1], "-"));
				
			}
			if(objs[2]!=null&&!"".equals(objs[2])){
				where +=" and convert(varchar(10),task.finish_time,120) <= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[2], "-"));
		    }
			if(objs[3]!=null&&!"".equals(objs[3])){
				if(DataUtil.getInt(objs[3])==-1){
					where +=" and  task.status!= 0 ";
				}else{
					where +=" and  task.status = ? ";
					obj.add(objs[3]);
				}
		    }else{ 
		    	where +=" and task.status in(1,-8,-9) ";
		    }  
			
			if(objs[4]!=null&&!"".equals(objs[4])){
				where +=" and saffer.uuid= ? ";
				obj.add(objs[4]);
		    }
		 }
		
		final List<WorksTask> taskList = new ArrayList<WorksTask>();
		 String sql="select task.uuid,task.task_code,task.title,task.task_content,task.task_level,task.start_time,task.finish_time,"
			+"task.issued_time,task.status,task.hold1,DATEDIFF(day,getdate(),task.finish_time) remindDay,DATEDIFF(hour,getdate(),task.finish_time) remindHour,task.remark,"
			//+"saffer.execute_userName,saffer.execute_dept "
			//+"from works_task task inner join task_saffer tasksaffer on task.uuid=tasksaffer.task_uuid inner join execute_user saffer on tasksaffer.saffer_uuid=saffer.uuid  where 1=1 and task.status in(1,-8,-9)"
			+"saffer.execute_userName saffer_execute_userName,saffer.execute_dept saffer_execute_dept,saffer.execute_rank saffer_execute_rank, "
			+"leader.execute_userName leader_execute_userName,leader.execute_dept leader_execute_dept,leader.execute_rank leader_execute_rank "
			+"from works_task task inner join task_leader taskleader on task.uuid=taskleader.task_uuid inner join execute_user leader on taskleader.leader_uuid=leader.uuid  " +
					             " inner join task_saffer tasksaffer on task.uuid=tasksaffer.task_uuid inner join execute_user saffer on tasksaffer.saffer_uuid=saffer.uuid  " 
			+"where 1=1 and task.status in(1,-8,-9)"+where+" order by task.start_time desc";
		
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), Page.DATABASE_TYPE_MSSQL),obj.toArray(), new RowCallbackHandler(){
		 public void processRow(ResultSet rs) throws SQLException{
			WorksTask task = new WorksTask ();
			task.setUuid(rs.getInt("uuid"));
			task.setTaskCode(rs.getString("task_code"));
			task.setTitle(rs.getString("title"));
			task.setTaskContent(rs.getString("task_content"));
			task.setTaskLevel(rs.getInt("task_level"));
			task.setStartTime(rs.getTimestamp("start_time"));
			task.setFinishTime(rs.getTimestamp("finish_time"));
			task.setIssuedTime(rs.getTimestamp("issued_time"));
			task.setStatus(rs.getInt("status"));
			//if("0".equals(rs.getString("remindDay"))){
		    task.setHold2(rs.getString("remindHour"));
			// }else{
			//  task.setHold2(rs.getString("remindDay"));
		  // 	}
			task.setRemark(rs.getString("remark"));
			List<User> safferList = new ArrayList<User>();
			User saffer = new User();
			saffer.setExecuteDept(rs.getString("saffer_execute_dept"));
			saffer.setExecuteRank(rs.getInt("saffer_execute_rank"));
			saffer.setExecuteUserName(rs.getString("saffer_execute_userName"));
			saffer.setUuid(DataUtils.getInt(objs[4]));
			safferList.add(saffer);
			task.setSafferList(safferList);
			task.getLeader().setExecuteDept(rs.getString("leader_execute_dept"));
			task.getLeader().setExecuteUserName(rs.getString("leader_execute_userName"));
			task.getLeader().setExecuteRank(rs.getInt("leader_execute_rank"));
		    taskList.add(task);
		 }
	  });
		return taskList;
	 }
 	public int saveTask(WorksTask task) throws Exception{
		final String sql = "insert into works_task(task_code,title,task_content,task_level,start_time,finish_time,issued_time,status,hold1,hold2,remark) values(?,?,?,?,?,?,?,?,?,?,?)";
		final Object[] objs = {task.getTaskCode(),task.getTitle(),task.getTaskContent(),task.getTaskLevel(),new Timestamp(task.getStartTime().getTime()),new Timestamp(task.getFinishTime().getTime()),new Timestamp(task.getIssuedTime().getTime()),task.getStatus(),task.getHold1(),task.getHold2(),task.getRemark()};
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
				    ps.setInt(++i,    DataUtil.getInt(objs[n++]));
					ps.setTimestamp(++i,   DateUtil.getTimestamp(objs[n++]));
					ps.setTimestamp(++i,   DateUtil.getTimestamp(objs[n++]));
				    ps.setTimestamp(++i,   DateUtil.getTimestamp(objs[n++]));
					ps.setInt(++i,    DataUtil.getInt(objs[n++]));
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
 	public void saveTaskSaffer(int taskUuid,int safferUuid) throws Exception{
		String sql ="insert into task_saffer(task_uuid,saffer_uuid) values(?,?)";
		final Object[] objs = {taskUuid,safferUuid};
	    this.getJdbcTemplate().update(sql,objs);
	}
 	public void saveTaskLeader(int taskUuid,int leaderUuid) throws Exception{
		String sql ="insert into task_leader(task_uuid,leader_uuid) values(?,?)";
		final Object[] objs = {taskUuid,leaderUuid};
	    this.getJdbcTemplate().update(sql,objs);
	}
	public void updateTask(WorksTask task) throws Exception{
		String sql ="update works_task set task_code=?,title=?,task_content=?,task_level=?,start_time=?,finish_time=?,issued_time=?,status=?,hold1=?,hold2=?,remark=? where uuid=?";
        final Object[] objs = {task.getTaskCode(),task.getTitle(),task.getTaskContent(),task.getTaskLevel(),new Timestamp(task.getStartTime().getTime()),new Timestamp(task.getFinishTime().getTime()),new Timestamp(task.getIssuedTime().getTime()),task.getStatus(),task.getHold1(),task.getHold2(),task.getRemark(),task.getUuid()};
	    this.getJdbcTemplate().update(sql,objs);
	}
	public void updateTaskStatus(int uuid,int status) throws Exception{
		String sql ="update works_task set status=? where uuid=?";
        final Object[] objs = {status,uuid};
	    this.getJdbcTemplate().update(sql,objs);
	}
	public void deleteTask(int uuid) throws Exception{
		String sql ="delete from works_task where uuid="+uuid;
	    this.getJdbcTemplate().update(sql);
	}
	
	public void deleteTask(String[] uuids) throws Exception{
		if(uuids!=null&&uuids.length>0){
			String[] sqls = new String[uuids.length];
			for (int i = 0 ; i < uuids.length ; i++) {
				sqls[i] = "delete from works_task where uuid="+uuids[i];
			}
			this.getJdbcTemplate().batchUpdate(sqls);
		}
	}
	public void deleteTaskSaffer(int uuid) throws Exception{
		String sql ="delete from task_saffer where task_uuid="+uuid;
	    this.getJdbcTemplate().update(sql);
	}
	public void deleteTaskLeader(int uuid) throws Exception{
		String sql ="delete from task_leader where task_uuid="+uuid;
	    this.getJdbcTemplate().update(sql);
	}
	
	
}
