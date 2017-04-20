package com.jujie.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.global.util.DataUtil;
import com.jujie.global.util.DateUtil;
import com.jujie.global.util.Page;
import com.jujie.global.util.PageUtils;
import com.jujie.notice.Notice;

public class NoticeDaoImpl extends BaseJdbcDao {
	public List<Notice> publishNotices(Object[] objs, String title , String userid , Page page) throws Exception{
		final List<Notice> noticeList = new ArrayList<Notice>();
		List<Object> obj = new ArrayList<Object>();
		String where = " where 1=1 ";
		if(objs!=null&&objs.length>0){
			if(objs.length>=1&&objs[0]!=null&&!"".equals(objs[0])){
				where +=" and convert(varchar(10),n.start_time,120) >= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[0], "-"));	
			}
			if(objs.length>=2&&objs[1]!=null&&!"".equals(objs[1])){
				where +=" and convert(varchar(10),n.end_time,120) <= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[1], "-"));
		    }
		}
		if(title!=null&&!"".equals(title)){
			where +=" and n.title like '%"+title+"%'";
		}
		String sql = "select n.uuid , n.title , n.content , n.public_uuid , n.public_time , n.start_time ," +
				" n.end_time , n.status , n.rank , n.hold1 , n.hold2 , n.remark , e.execute_userName , e.execute_dept " +
				" from notice n , execute_user e" +
				where +" and n.public_uuid = e.uuid and n.public_uuid = "+userid +" order by n.uuid desc";
		 this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), Page.DATABASE_TYPE_MSSQL),obj.toArray(), new RowCallbackHandler(){
			 public void processRow(ResultSet rs) throws SQLException{
				 Notice notice = new Notice();
				 notice.setUuid(rs.getInt("uuid"));
				 notice.setTitle(rs.getString("title"));
				 notice.setContent(rs.getString("content"));
				 notice.setPublic_uuid(rs.getInt("public_uuid"));
				 notice.setPublic_time(rs.getDate("public_time"));
				 notice.setStart_time(rs.getDate("start_time"));
				 notice.setEnd_time(rs.getDate("end_time"));
				 notice.setStatus(rs.getInt("status"));
				 notice.setRank(rs.getInt("rank"));
				 notice.setHold1(rs.getString("hold1"));
				 notice.setHold2(rs.getString("hold2"));
				 notice.setRemark(rs.getString("remark"));	 
				 notice.setExecute_userName(rs.getString("execute_userName"));
				 notice.setExecute_dept(rs.getString("execute_dept"));
				 noticeList.add(notice);
			 }
		 });
		return noticeList;
	}
	public List<Notice> ratifyNotices(Object[] objs, String title , String userid , Page page) throws Exception{
		final List<Notice> noticeList = new ArrayList<Notice>();
		List<Object> obj = new ArrayList<Object>();
		String where = " where 1=1 ";
		if(objs!=null&&objs.length>0){
			if(objs.length>=1&&objs[0]!=null&&!"".equals(objs[0])){
				where +=" and convert(varchar(10),n.start_time,120) >= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[0], "-"));	
			}
			if(objs.length>=2&&objs[1]!=null&&!"".equals(objs[1])){
				where +=" and convert(varchar(10),n.end_time,120) <= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[1], "-"));
		    }
		}
		if(title!=null&&!"".equals(title)){
			where +=" and n.title like '%"+title+"%'";
		}	
		if(ifLeader(userid)){
			where +="and n.hold1 between 1 and 2 and n.public_uuid = e.uuid and n.status !=0  ";
		}else{
			where +=" and n.public_uuid in (select e.uuid  from execute_user e where e.execute_dept = (select e.execute_dept from execute_user e where e.uuid = "+userid+")) "+
			" and  n.hold1>(select execute_rank from execute_user where uuid = "+userid+") and n.status!=0 and n.public_uuid = e.uuid  and n.public_uuid!= "+userid ;
		}
			 String sql = "select n.uuid , n.title , n.content , n.public_uuid , n.public_time , n.start_time ," +
				" n.end_time , n.status , n.rank , n.hold1 , n.hold2 , n.remark , e.execute_dept , e.execute_userName" +
				" from notice n  ,execute_user e "+where +" order by n.uuid desc ";	
		 this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), Page.DATABASE_TYPE_MSSQL),obj.toArray(), new RowCallbackHandler(){
			 public void processRow(ResultSet rs) throws SQLException{
				 Notice notice = new Notice();
				 notice.setUuid(rs.getInt("uuid"));
				 notice.setTitle(rs.getString("title"));
				 notice.setContent(rs.getString("content"));
				 notice.setPublic_uuid(rs.getInt("public_uuid"));
				 notice.setPublic_time(rs.getDate("public_time"));
				 notice.setStart_time(rs.getDate("start_time"));
				 notice.setEnd_time(rs.getDate("end_time"));
				 notice.setStatus(rs.getInt("status"));
				 notice.setRank(rs.getInt("rank"));
				 notice.setHold1(rs.getString("hold1"));
				 notice.setHold2(rs.getString("hold2"));
				 notice.setRemark(rs.getString("remark")); 
				 notice.setExecute_userName(rs.getString("execute_userName"));
				 notice.setExecute_dept(rs.getString("execute_dept"));	 
				 noticeList.add(notice);
			 }
		 });
		return noticeList;
	}
	public List<Notice> watchNotices(Object[] objs, String title , String userid , Page page) throws Exception{
		final List<Notice> noticeList = new ArrayList<Notice>();
		List<Object> obj = new ArrayList<Object>();
		String where = " where 1 = 1";
		if(objs!=null&&objs.length>0){
			if(objs.length>=1&&objs[0]!=null&&!"".equals(objs[0])){
				where +=" and convert(varchar(10),n.start_time,120) >= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[0], "-"));	
			}
			if(objs.length>=2&&objs[1]!=null&&!"".equals(objs[1])){
				where +=" and convert(varchar(10),n.end_time,120) <= ? ";
				obj.add(DateUtil.DateUtilFormat((Date)objs[1], "-"));
		    }
		}
		if(title!=null&&!"".equals(title)){
			where +=" and n.title like '%"+title+"%'";
		}	
		String row ="select n.uuid , n.title , n.content , n.public_uuid , n.public_time , n.start_time ," +
		" n.end_time , n.status , n.rank , n.hold1 , n.hold2 , n.remark , e.execute_userName , e.execute_dept " +
		" from notice n , execute_user e";
		String sql = "";
		if(this.ifLeader(userid)){
		 sql = row +where +" and n.public_uuid = e.uuid  and n.status =2 order by n.uuid desc ";
		}else{
		 sql ="select * from ("
					+row + " where n.public_uuid in  "
						 + " (select e.uuid from execute_user e where e.execute_dept = (select e.execute_dept from execute_user e  where e.uuid = "+userid+ ")) "
						 + " and n.public_uuid = e.uuid  and n.status = 2 "
						 + " union "
				    +row + " where n.public_uuid not in "
				   		 + " (select e.uuid from execute_user e where e.execute_dept = (select e.execute_dept from execute_user e  where e.uuid = "+userid +")) "
				   		 + " and n.public_uuid = e.uuid  and n.status = 2 " 
				   		 + " and n.rank = 0 ) n " + where +" order by n.uuid desc ";	 
		}
		 this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), Page.DATABASE_TYPE_MSSQL),obj.toArray(), new RowCallbackHandler(){
			 public void processRow(ResultSet rs) throws SQLException{
				 Notice notice = new Notice();
				 notice.setUuid(rs.getInt("uuid"));
				 notice.setTitle(rs.getString("title"));
				 notice.setContent(rs.getString("content"));
				 notice.setPublic_uuid(rs.getInt("public_uuid"));
				 notice.setPublic_time(rs.getDate("public_time"));
				 notice.setStart_time(rs.getDate("start_time"));
				 notice.setEnd_time(rs.getDate("end_time"));
				 notice.setStatus(rs.getInt("status"));
				 notice.setRank(rs.getInt("rank"));
				 notice.setHold1(rs.getString("hold1"));
				 notice.setHold2(rs.getString("hold2"));
				 notice.setRemark(rs.getString("remark"));	 
				 notice.setExecute_userName(rs.getString("execute_userName"));
				 notice.setExecute_dept(rs.getString("execute_dept"));
				 noticeList.add(notice);
			 }
		 });
		return noticeList;
	}
	public Notice showNotice(String uuid) throws Exception{	
		 String sql="select e.execute_userName , e.execute_dept , n.uuid , n.title , n.content , n.public_uuid , " +
		 		"n.public_time , n.start_time , n.end_time , n.status , n.rank , n.hold1 , n.hold2 , n.remark " +
		 		"from notice n , execute_user e  " +
		 		" where n.public_uuid = e.uuid  and  n.uuid="+uuid;
		final Notice notice = new Notice();
		 this.getJdbcTemplate().query(sql, new RowCallbackHandler(){
			 public void processRow(ResultSet rs) throws SQLException{
				
				 notice.setUuid(rs.getInt("uuid"));
				 notice.setTitle(rs.getString("title"));
				 notice.setContent(rs.getString("content"));
				 notice.setPublic_uuid(rs.getInt("public_uuid"));
				 notice.setPublic_time(rs.getDate("public_time"));
				 notice.setStart_time(rs.getDate("start_time"));
				 notice.setEnd_time(rs.getDate("end_time"));
				 notice.setStatus(rs.getInt("status"));
				 notice.setRank(rs.getInt("rank"));
				 notice.setHold1(rs.getString("hold1"));
				 notice.setHold2(rs.getString("hold2"));
				 notice.setRemark(rs.getString("remark"));
				 
				 notice.setExecute_userName(rs.getString("execute_userName"));
				 notice.setExecute_dept(rs.getString("execute_dept"));
			 }
		 });	
		return notice;
	}
	public Notice showAdd(String userid) throws Exception{
		 String sql= "select uuid ,execute_userName , execute_dept , execute_rank from execute_user where uuid = "+userid;
		 final Notice notice = new Notice();
		 this.getJdbcTemplate().query(sql, new RowCallbackHandler(){
			 public void processRow(ResultSet rs) throws SQLException{
				 notice.setPublic_uuid(rs.getInt("uuid"));
				 notice.setExecute_userName(rs.getString("execute_userName"));
				 notice.setExecute_dept(rs.getString("execute_dept"));
				 notice.setHold1(String.valueOf(rs.getInt("execute_rank"))); 
			 }
		 });
		 return notice;
	}
	public int addNotice(Notice notice) throws Exception {
		final String sql = "insert into notice(title,content,public_uuid,public_time,start_time,end_time,status,rank," +
				"hold1,hold2,remark ) values(?,?,?,?,?,?,?,?,?,?,?)";
		final Object[] objs ={notice.getTitle(),notice.getContent(),notice.getPublic_uuid(),notice.getPublic_time(),notice.getStart_time()
				,notice.getEnd_time(),notice.getStatus(),notice.getRank(),notice.getHold1(),notice.getHold2(),notice.getRemark()};
		 KeyHolder keyHolder = new GeneratedKeyHolder();     	
		 try {
			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
				int i = 0;
				int n = 0;
				PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);  
				ps.setString(++i, DataUtil.getStringK(objs[n++]));    
				ps.setString(++i, DataUtil.getStringK(objs[n++]));   
				ps.setInt(++i, DataUtil.getInt(objs[n++]));   
				ps.setDate(++i,   DateUtil.getSqlDate(objs[n++]));
				ps.setDate(++i,   DateUtil.getSqlDate(objs[n++]));
				ps.setDate(++i,   DateUtil.getSqlDate(objs[n++]));
				ps.setInt(++i, DataUtil.getInt(objs[n++])); 
				ps.setString(++i, DataUtil.getString(objs[n++])); 
				ps.setString(++i, DataUtil.getStringK(objs[n++])); 
				ps.setString(++i, DataUtil.getStringK(objs[n++])); 
				ps.setString(++i, DataUtil.getStringK(objs[n++]));  
				return ps;    
				}  
			 },keyHolder);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		 return keyHolder.getKey().intValue();  
	}    
	public void updateNotice(Notice notice) throws Exception{
		String sql = "update notice set title=? , content=? , start_time=? , end_time=? ," +
				" rank=? , status=? , hold1=? , hold2=? , remark=? where uuid=?";
		Object[] objs = {notice.getTitle(),notice.getContent(),notice.getStart_time(),notice.getEnd_time(),
				notice.getRank(),notice.getStatus(),notice.getHold1(),notice.getHold2(),notice.getRemark(),notice.getUuid()};
		this.getJdbcTemplate().update(sql,objs);		
	}
	public void upRatify(int status , String  uuid) throws Exception{
		String sql = "update notice set status="+status+" where uuid="+uuid;
		this.getJdbcTemplate().update(sql);
	}
	public void ratifyNotice(Notice notice) throws Exception{
		String sql = "update notice set status=? where uuid=?";
		Object[] objs = {notice.getStatus(),notice.getUuid()};
		this.getJdbcTemplate().update(sql,objs);
	}
	public void deleteNotice(String[] uuid) throws Exception{
		String[] sql = new String[uuid.length];
		 sql[0] = "delete from notice where uuid="+uuid[0];
		this.getJdbcTemplate().batchUpdate(sql);
	}	
	public boolean ifLeader(String userid)throws Exception{
		boolean ifleader = false;			
		String sql= "select execute_rank from execute_user where uuid = "+userid;
		final Notice notice = new Notice();
		this.getJdbcTemplate().query(sql, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException{
				notice.setHold1(String.valueOf(rs.getInt("execute_rank"))); 
			}
		}); 
		if("1".equals(notice.getHold1())&&notice.getHold1()!=null){
			ifleader=true;
		}
		return ifleader;
}
}
