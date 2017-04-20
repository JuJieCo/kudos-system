package com.jujie.task.server;

 
import java.util.List;

import com.jujie.global.util.Page;
import com.jujie.task.WorkProgress;
import com.jujie.task.WorksAccessory;
import com.jujie.task.WorksTask;
import com.jujie.task.dao.WorksAccessoryDaoImpl;
import com.jujie.task.dao.WorksProgressDaoImpl;
import com.jujie.task.dao.WorksTaskDaoImpl;
import com.jujie.user.User;

public class TaskServerImpl {
	
	private WorksTaskDaoImpl taskDao;
	private WorksProgressDaoImpl taskProgressDao;
	private WorksAccessoryDaoImpl taskAccessoryDao;
	
	 
	
	public void setTaskAccessoryDao(WorksAccessoryDaoImpl taskAccessoryDao) {
		this.taskAccessoryDao = taskAccessoryDao;
	}
	public void setTaskDao(WorksTaskDaoImpl taskDao) {
		this.taskDao = taskDao;
	}
	public void setTaskProgressDao(WorksProgressDaoImpl taskProgressDao) {
		this.taskProgressDao = taskProgressDao;
	}
	//工作安排
	public WorksTask queryTask(int uuid) throws Exception{
		return taskDao.queryTask(uuid);
	}
	public List<User> queryExeueByTaskuuid(int uuid) throws Exception{
		return taskDao.queryExeueByTaskuuid(uuid);
	}
	
	public List<WorksTask> queryTaskAll(Object[] objs,Page page) throws Exception{
		return taskDao.queryTaskAll(objs,page);
	}
	public List<WorksTask> queryTaskBySearch(Object[] objs,Page page) throws Exception{
		return taskDao.queryTaskBySearch(objs,page);
	}
	public List<WorksTask> queryWorkRemind(Object[] objs,Page page) throws Exception{
		return taskDao.queryWorkRemind(objs,page);
	}
	
	public int saveTask(WorksTask task) throws Exception{
		return taskDao.saveTask(task);
	}
	public void saveTaskSaffer(int taskUuid,int safferUuid) throws Exception{
		 taskDao.saveTaskSaffer(taskUuid,safferUuid);
	}
	public void saveTaskLeader(int taskUuid,int leaderUuid) throws Exception{
		 taskDao.saveTaskLeader(taskUuid,leaderUuid);
	}
	public void updateTask(WorksTask task) throws Exception{
		taskDao.updateTask(task);
	}
	public void updateTaskStatus(int uuid,int status) throws Exception{
		taskDao.updateTaskStatus(uuid, status);
	}
	
	public void deleteTask(int uuid) throws Exception{
		taskDao.deleteTask(uuid);
	}
	public void deleteTask(String[] uuids) throws Exception{
		taskDao.deleteTask(uuids);
	}
	public void deleteTaskSaffer(int uuid) throws Exception{
		taskDao.deleteTaskSaffer(uuid);
	}
	public void deleteTaskLeader(int uuid) throws Exception{
		taskDao.deleteTaskLeader(uuid);
	}
    //工作进度
	public WorkProgress queryTaskProgress(int uuid) throws Exception{
	     return taskProgressDao.queryTaskProgress(uuid);
	}
	public List<WorkProgress> queryTaskProgressAll(Object[] objs,Page page) throws Exception{
		 return taskProgressDao.queryTaskProgressAll(objs,page);
	}
	public List<WorkProgress> queryTaskProgressByTask(Object[] objs) throws Exception{
		   return taskProgressDao.queryTaskProgressByTask(objs);
	}
	public int saveTaskProgress(WorkProgress taskProgress) throws Exception{
		int id = taskProgressDao.saveTaskProgress(taskProgress);
		boolean sattus =false;
		if("100".equals(taskProgress.getProgress())){
		    List<WorkProgress> workProgressList=taskProgressDao.queryStatusByUuid(taskProgress.getTask().getUuid());
			for (WorkProgress workProgress : workProgressList) {
				if(!"100".equals(workProgress.getProgress())||"".equals(workProgress.getProgress())){
					sattus=true;
					break;
				}
			}
			if(sattus){				
			}else{
			    taskDao.updateTaskStatus(taskProgress.getTask().getUuid(),8);
			}
		 }
		return id;
	}
	public void updateTaskProgress(WorkProgress taskProgress) throws Exception{
		taskProgressDao.updateTaskProgress(taskProgress);
		boolean sattus =false;
		if("100".equals(taskProgress.getProgress())){
		    List<WorkProgress> workProgressList=taskProgressDao.queryStatusByUuid(taskProgress.getTask().getUuid());
			for (WorkProgress workProgress : workProgressList) {
				if(!"100".equals(workProgress.getProgress())||"".equals(workProgress.getProgress())){
					sattus=true;
					break;
				}
			}
			if(sattus){
				
			 }else{
			  taskDao.updateTaskStatus(taskProgress.getTask().getUuid(),8);
			    }
		 }else{
			 int status = taskDao.queryTask(taskProgress.getTask().getUuid()).getStatus();
			 if(status==8){
				 taskDao.updateTaskStatus(taskProgress.getTask().getUuid(),-8);
			 }else{
				 taskDao.updateTaskStatus(taskProgress.getTask().getUuid(),status);
			 } 
		 }
		
	}
	public void deleteTaskProgress(int work_id) throws Exception{
		taskProgressDao.deleteTaskProgress(work_id);
	}
	public void deleteTaskProgress(String[] work_ids) throws Exception{
		taskProgressDao.deleteTaskProgress(work_ids);
	}
	
	public List<WorksAccessory> queryTaskAccessory(int task_uuid) throws Exception{
		return taskAccessoryDao.queryTaskAccessory(task_uuid);
	}

	public int saveTaskAccessory(WorksAccessory worksAccessory) throws Exception{
		 return taskAccessoryDao.saveTaskAccessory(worksAccessory);
	}
	public void updateTaskAccessory(WorksAccessory worksAccessory) throws Exception{
		taskAccessoryDao.updateTaskAccessory(worksAccessory);
	}
	public void deleteTaskAccessory(int uuid) throws Exception{
		taskAccessoryDao.deleteTaskAccessory(uuid);
	}
	
	public void deleteTaskAccessoryByTask(int taskUuid) throws Exception{
		taskAccessoryDao.deleteTaskAccessoryByTask(taskUuid);
	}
	
	public WorksAccessory queryOneTaskAccessory(int uuid) throws Exception{
		return taskAccessoryDao.queryOneTaskAccessory(uuid);
	}
}
