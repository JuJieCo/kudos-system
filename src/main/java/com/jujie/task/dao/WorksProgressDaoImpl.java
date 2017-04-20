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
import com.jujie.task.WorkProgress;

public class WorksProgressDaoImpl extends BaseJdbcDao {
	public WorkProgress queryTaskProgress(final int uuid) throws Exception {
		final WorkProgress taskProgress = new WorkProgress();
	        String sql="select proqress.uuid,proqress.progress_content content,proqress.progress,proqress.input_time,proqress.hold1,proqress.hold2,proqress.remark,task.uuid task_uuid,task.task_code,task.title task_title,task.task_content,task.task_level,task.start_time,task.finish_time,"
			+"task.issued_time,task.status task_status,task.hold1 task_hold1,task.hold2 task_hold2,task.remark task_remark,"
			+"saffer.uuid saffer_uuid,saffer.execute_dept saffer_dept,saffer.execute_userName saffer_name,saffer.execute_rank saffer_rank "
			+"from work_proqress proqress "
			+"inner join works_task task on proqress.task_uuid=task.uuid "
			+"inner join task_saffer tasksaffer on task.uuid=tasksaffer.task_uuid "
			+"inner join execute_user saffer on tasksaffer.saffer_uuid=saffer.uuid "
			+"where proqress.uuid="+uuid;
		this.getJdbcTemplate().query(sql, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				taskProgress.setUuid(rs.getInt("uuid"));
				taskProgress.setContent(rs.getString("content"));
				taskProgress.setProgress(rs.getString("progress"));
				taskProgress.setInputTime(rs.getTimestamp("input_time"));
			 	taskProgress.setHold1(rs.getString("hold1"));
				taskProgress.setHold2(rs.getString("hold2"));
				taskProgress.setRemark(rs.getString("remark"));
				taskProgress.getTask().setUuid(rs.getInt("task_uuid"));
				taskProgress.getTask().setTaskCode(rs.getString("task_code"));
				taskProgress.getTask().setTitle(rs.getString("task_title"));
				taskProgress.getTask().setTaskContent(rs.getString("task_content"));
				taskProgress.getTask().setTaskLevel(rs.getInt("task_level"));
				taskProgress.getTask().setStartTime(rs.getTimestamp("start_time"));
				taskProgress.getTask().setFinishTime(rs.getTimestamp("finish_time"));
				taskProgress.getTask().setIssuedTime(rs.getTimestamp("issued_time"));
				taskProgress.getTask().setStatus(rs.getInt("task_status"));
				taskProgress.getTask().setHold1(rs.getString("task_hold1"));
				taskProgress.getTask().setHold2(rs.getString("task_hold2"));
				taskProgress.getTask().setRemark(rs.getString("task_remark"));
				taskProgress.getTask().getLeader().setUuid(rs.getInt("saffer_uuid"));
				taskProgress.getTask().getLeader().setExecuteDept(rs.getString("saffer_dept"));
				taskProgress.getTask().getLeader().setExecuteUserName(rs.getString("saffer_name"));
				taskProgress.getTask().getLeader().setExecuteRank(rs.getInt("saffer_rank"));
			    taskProgress.setInputUser(taskProgress.getTask().getLeader());
			}
		});
		return taskProgress;
	}
	public List<WorkProgress> queryStatusByUuid(Integer taskuuid) throws Exception{
	     final List<WorkProgress> workProgressList = new ArrayList<WorkProgress>();
		 String sql="select max(progress) progress from work_proqress where task_uuid="+taskuuid+" group by execute_user_uuid";
	     this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
		 public void processRow(ResultSet rs) throws SQLException{
			 WorkProgress workProgress = new WorkProgress ();
			 workProgress.setProgress(rs.getString("progress"));
			 workProgressList.add(workProgress);

		 }
	    });
		return workProgressList;
	 }
	public List<WorkProgress> queryTaskProgressAll(Object[] objs,Page page)
			throws Exception {
		String where = " ";
		List<Object> obj = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(objs[0])){
				where +=" and convert(varchar(10),proqress.input_time,120) >= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[0], "-"));
			}
			if(objs[1]!=null&&!"".equals(objs[1])){
				where +=" and convert(varchar(10),proqress.input_time,120) <= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[1], "-"));
		    }
			if(objs[2]!=null&&!"".equals(objs[2])){
				where +=" and  proqress.task_uuid = ? ";
				obj.add(DataUtil.getInt(objs[2]));
		    }
			if(objs[3]!=null&&!"".equals(objs[3])){
				where +=" and proqress.execute_user_uuid = ? ";
				obj.add(DataUtil.getInt(objs[3]));
		    }
			obj.addAll(obj);
		}
		final List<WorkProgress> taskProgressList = new ArrayList<WorkProgress>();
		String sql="select * from (select proqress.uuid,proqress.progress_content content,proqress.progress,proqress.input_time,proqress.hold1,proqress.hold2,proqress.remark,task.uuid task_uuid,task.task_code,task.title task_title,task.task_content,task.task_level,task.start_time,task.finish_time,"
			+"task.issued_time,task.status task_status,task.hold1 task_hold1,task.hold2 task_hold2,task.remark task_remark "
			+"from work_proqress proqress "
			+"inner join works_task task on proqress.task_uuid=task.uuid "
			+where+") proqress "
		    +"where proqress.uuid in (select max(proqress.uuid) from work_proqress proqress,works_task b,execute_user c where proqress.task_uuid=b.uuid "
		    +where+" group by proqress.task_uuid,proqress.execute_user_uuid"
            +")order by proqress.input_time desc";
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), Page.DATABASE_TYPE_MSSQL),obj.toArray(), new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				WorkProgress taskProgress = new WorkProgress();
				taskProgress.setUuid(rs.getInt("uuid"));
			    taskProgress.setContent(rs.getString("content"));
				taskProgress.setProgress(rs.getString("progress"));
				taskProgress.setInputTime(rs.getTimestamp("input_time"));
				taskProgress.setHold1(rs.getString("hold1"));
				taskProgress.setHold2(rs.getString("hold2"));
				taskProgress.setRemark(rs.getString("remark"));
				taskProgress.getTask().setUuid(rs.getInt("task_uuid"));
				taskProgress.getTask().setTaskCode(rs.getString("task_code"));
				taskProgress.getTask().setTitle(rs.getString("task_title"));
				taskProgress.getTask().setTaskContent(rs.getString("task_content"));
				taskProgress.getTask().setTaskLevel(rs.getInt("task_level"));
				taskProgress.getTask().setStartTime(rs.getTimestamp("start_time"));
				taskProgress.getTask().setFinishTime(rs.getTimestamp("finish_time"));
				taskProgress.getTask().setIssuedTime(rs.getTimestamp("issued_time"));
				taskProgress.getTask().setStatus(rs.getInt("task_status"));
				taskProgress.getTask().setHold1(rs.getString("task_hold1"));
				taskProgress.getTask().setHold2(rs.getString("task_hold2"));
				taskProgress.getTask().setRemark(rs.getString("task_remark"));
			    taskProgressList.add(taskProgress);
			}
		});
		return taskProgressList;
	}
	public List<WorkProgress> queryTaskProgressByTask(Object[] objs) throws Exception {
		String where = "where 1=1 ";
		List<Object> obj = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(objs[0])){
			where +=" and convert(varchar(10),proqress.input_time,120) >= ? ";
			obj.add(DateUtil.DateUtilFormat((Date)objs[0], "-"));
		}
		if(objs[1]!=null&&!"".equals(objs[1])){
			where +=" and convert(varchar(10),proqress.input_time,120) <= ? ";
			obj.add(DateUtil.DateUtilFormat((Date)objs[1], "-"));
		}
		if(objs[2]!=null&&!"".equals(objs[2])){
			where +=" and  proqress.task_uuid = ? ";
				obj.add(DataUtil.getInt(objs[2]));
		    }
		if(objs[3]!=null&&!"".equals(objs[3])){
			where +=" and  proqress.execute_user_uuid = ? ";
				obj.add(DataUtil.getInt(objs[3]));
		    }
	 }
		final List<WorkProgress> taskProgressList = new ArrayList<WorkProgress>();
		String sql="select proqress.uuid,proqress.progress_content content,proqress.progress,proqress.input_time,proqress.hold1,proqress.hold2,proqress.remark,saffer.execute_userName execute_userName,saffer.execute_dept execute_dept "
		+"from work_proqress proqress,execute_user saffer "
		+where+" and saffer.uuid=proqress.execute_user_uuid order by proqress.input_time desc";
		this.getJdbcTemplate().query(sql,obj.toArray(), new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
		    WorkProgress taskProgress = new WorkProgress();
			taskProgress.setUuid(rs.getInt("uuid"));
		    taskProgress.setContent(rs.getString("content"));
			taskProgress.setProgress(rs.getString("progress"));
			taskProgress.setInputTime(rs.getTimestamp("input_time"));
			taskProgress.setHold1(rs.getString("hold1"));
			taskProgress.setHold2(rs.getString("hold2"));
			taskProgress.setRemark(rs.getString("remark")); 
			taskProgress.getInputUser().setExecuteDept(rs.getString("execute_dept"));
			taskProgress.getInputUser().setExecuteUserName(rs.getString("execute_userName"));
		    taskProgressList.add(taskProgress);
			}
		});
		return taskProgressList;
		}
	public List<WorkProgress> queryTaskProgressRemind(Object[] objs) throws Exception {
		String where = " where 1=1 ";
		List<Object> obj = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(objs[0])){
				where +=" and convert(varchar(10),proqress.input_time,120) >= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[0], "-"));
			}
			if(objs[1]!=null&&!"".equals(objs[1])){
				where +=" and convert(varchar(10),proqress.input_time,120) <= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[1], "-"));
		    }
			if(objs[2]!=null&&!"".equals(objs[2])){
				where +=" and  proqress.task_uuid = ? ";
				obj.add(DataUtil.getInt(objs[2]));
		    }
		}
		final List<WorkProgress> taskProgressList = new ArrayList<WorkProgress>();
		String sql="select proqress.uuid,proqress.progress,proqress.input_time,proqress.hold1,proqress.hold2,proqress.remark,task.uuid task_uuid,task.task_code,task.title task_title,task.task_content,task.task_level,task.start_time,task.finish_time,"
			+"task.issued_time,task.status task_status,task.hold1 task_hold1,task.hold2 task_hold2,task.remark task_remark,"
			+"saffer.uuid saffer_uuid,saffer.execute_dept saffer_dept,saffer.execute_userName saffer_name,saffer.execute_rank saffer_rank,"
			+"leader.uuid leader_uuid,leader.execute_dept leader_dept,leader.execute_userName leader_name,leader.execute_rank leader_rank "
			+"from work_proqress proqress "
			+"inner join works_task task on proqress.task_uuid=task.uuid "
			+"inner join execute_user saffer on task.execute_user_uuid=saffer.uuid "
			+"inner join execute_user leader on task.leader_uuid=leader.uuid "
			+where+" order by proqress.input_time desc";
		this.getJdbcTemplate().query(sql,obj.toArray(), new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				WorkProgress taskProgress = new WorkProgress();
				taskProgress.setUuid(rs.getInt("uuid"));
			    taskProgress.setProgress(rs.getString("progress"));
				taskProgress.setInputTime(rs.getTimestamp("input_time"));
			 	taskProgress.setHold1(rs.getString("hold1"));
				taskProgress.setHold2(rs.getString("hold2"));
				taskProgress.setRemark(rs.getString("remark"));
				taskProgress.getTask().setUuid(rs.getInt("task_uuid"));
				taskProgress.getTask().setTaskCode(rs.getString("task_code"));
				taskProgress.getTask().setTitle(rs.getString("task_title"));
				taskProgress.getTask().setTaskContent(rs.getString("task_content"));
				taskProgress.getTask().setTaskLevel(rs.getInt("task_level"));
				taskProgress.getTask().setStartTime(rs.getTimestamp("start_time"));
				taskProgress.getTask().setFinishTime(rs.getTimestamp("finish_time"));
				taskProgress.getTask().setIssuedTime(rs.getTimestamp("issued_time"));
				taskProgress.getTask().setStatus(rs.getInt("task_status"));
				taskProgress.getTask().setHold1(rs.getString("task_hold1"));
				taskProgress.getTask().setHold2(rs.getString("task_hold2"));
				taskProgress.getTask().setRemark(rs.getString("task_remark"));
				taskProgress.getTask().getLeader().setUuid(rs.getInt("saffer_uuid"));
				taskProgress.getTask().getLeader().setExecuteDept(rs.getString("saffer_dept"));
				taskProgress.getTask().getLeader().setExecuteUserName(rs.getString("saffer_name"));
				taskProgress.getTask().getLeader().setExecuteRank(rs.getInt("saffer_rank"));
				 
				taskProgress.setInputUser(taskProgress.getTask().getLeader());
				taskProgressList.add(taskProgress);
			}
		});
		return taskProgressList;
		}
	public int saveTaskProgress(WorkProgress taskProgress) throws Exception {
		final String sql = "insert into work_proqress(task_uuid,progress_content,progress,input_time,execute_user_uuid,hold1,hold2,remark) values(?,?,?,?,?,?,?,?)";
		final Object[] objs = { taskProgress.getTask().getUuid(),taskProgress.getContent(),
				taskProgress.getProgress(),taskProgress.getInputTime(),taskProgress.getInputUser().getUuid(),taskProgress.getHold1(),
				taskProgress.getHold2(), taskProgress.getRemark() };
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			this.getJdbcTemplate().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					int i = 0;
					int n = 0;
					PreparedStatement ps = con.prepareStatement(sql,
							PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setInt(++i, DataUtil.getInt(objs[n++]));
					ps.setString(++i, DataUtil.getStringK(objs[n++]));
					ps.setString(++i, DataUtil.getStringK(objs[n++]));
					ps.setTimestamp(++i, DateUtil.getTimestamp(objs[n++]));
					ps.setInt(++i, DataUtil.getInt(objs[n++]));
					ps.setString(++i, DataUtil.getStringK(objs[n++]));
					ps.setString(++i, DataUtil.getStringK(objs[n++]));
					ps.setString(++i, DataUtil.getStringK(objs[n++]));
					return ps;
				}
			}, keyHolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyHolder.getKey().intValue();
	}

	public void updateTaskProgress(WorkProgress taskProgress) throws Exception {
		String sql = "update work_proqress set task_uuid=?,progress_content=?,progress=?,input_time=?,hold1=?,hold2=?,remark=? where uuid=?";
		final Object[] objs = { taskProgress.getTask().getUuid(),taskProgress.getContent(),
			    taskProgress.getProgress(),
				DateUtil.getTimestamp(taskProgress.getInputTime()), taskProgress.getHold1(),
				taskProgress.getHold2(), taskProgress.getRemark(),taskProgress.getUuid()};
		this.getJdbcTemplate().update(sql, objs);
	}

	public void deleteTaskProgress(int uuid) throws Exception {
		String sql = "delete from work_proqress where task_uuid=" + uuid;
		this.getJdbcTemplate().update(sql);
	}

	public void deleteTaskProgress(String[] uuids) throws Exception {
		String[] sqls = new String[uuids.length];
		if (uuids != null && uuids.length > 0) {
			for (int i = 0; i < uuids.length; i++) {
				sqls[i] = "delete from work_proqress where uuid=" + uuids[i];
			}
			this.getJdbcTemplate().batchUpdate(sqls);
		}

	}

}
