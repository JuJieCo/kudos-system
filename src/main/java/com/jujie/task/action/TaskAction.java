package com.jujie.task.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.global.util.DataUtil;
import com.jujie.global.util.DateJsonValueProcessor;
import com.jujie.global.util.DateUtil;
import com.jujie.global.util.DownFileUtil;
import com.jujie.global.util.ExportExcel;
import com.jujie.global.util.FileUtil;
import com.jujie.global.util.Page;
import com.jujie.task.WorkProgress;
import com.jujie.task.WorksAccessory;
import com.jujie.task.WorksTask;
import com.jujie.task.server.TaskServerImpl;
import com.jujie.task.util.CreatTaskCode;
import com.jujie.user.User;
import com.jujie.user.action.UserAction;
import com.jujie.user.server.UserServerImpl;

public class TaskAction extends BaseActionSupper {

	private static Log log = LogFactory.getLog(UserAction.class);
	private WorksTask worksTask;
	private WorkProgress workProgress;
	public List<WorksTask> worksTaskList;
	public List<WorkProgress> WorkProgressList;
    private Page page;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	private File[] accessory;// 实际上传文件
    private String[] accessoryContentType; // 文件的内容类型
    private String[] accessoryFileName;
	
	public String[] getAccessoryFileName() {
		return accessoryFileName;
	}

	public void setAccessoryFileName(String[] accessoryFileName) {
		this.accessoryFileName = accessoryFileName;
	}

	public String getAjaxSaffer(){
		UserServerImpl userServer = (UserServerImpl)this.getService("userServer");
		try{
			List<User> userList = userServer.queryAllUser(new Object[]{DataUtil.getStringK(request.getParameter("deptName"))},null);
			JSONArray jsonArray = JSONArray.fromObject(userList);
			log.info("");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonArray.toString());
		}catch(final Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String getAjaxProgess(){
	    TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			WorkProgressList = taskServer.queryTaskProgressByTask(new Object[]{null,null,DataUtil.getInt(request.getParameter("taskUuid")),null});
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(null));
			JSONArray jsonArray = JSONArray.fromObject(WorkProgressList,jsonConfig);
			log.info("");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonArray.toString());
		}catch(final Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteAjaxAccessory(){
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			taskServer.deleteTaskAccessory(DataUtil.getInt(request.getParameter("aid")));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String reportFindResultTaskList(){
		User user = (User)request.getSession().getAttribute("sessionUser");
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
	    try{
	    	Object[] objs = {null,worksTask.getStartTime(),worksTask.getFinishTime(),worksTask.getStatus(),request.getParameter("executeDept"),request.getParameter("safferID"),user.getUuid()};
	    	worksTaskList = taskServer.queryTaskBySearch(objs,page); 	
			String head = "任务查询统计报表";
			List<String> titles = new ArrayList<String>();
			titles.add("任务编号");
			titles.add("任务标题");
			titles.add("任务内容");
			titles.add("任务执行人");
			titles.add("任务下发人");
			titles.add("开始时间");
			titles.add("结束时间");
			List<List<Object>> values = new ArrayList<List<Object>>();
			for (WorksTask task  : worksTaskList) {
				List<Object> vl = new ArrayList<Object>();
				vl.add(task.getTaskCode());
				vl.add(task.getTitle());
				vl.add(task.getTaskContent());
				if(task.getSafferList()!=null&&task.getSafferList().size()>0){
					String names = "";
					for(User saffer : task.getSafferList()){
						names += saffer.getExecuteUserName()+" ";
					}
					vl.add(names);
				}else{
					vl.add("");
				}
				vl.add(task.getLeader().getExecuteUserName());
				vl.add(task.getStartTime());
				vl.add(task.getFinishTime());
				values.add(vl);
			}
			try{
				response.setContentType("text/plain");
				response.setHeader("Location", "统计报表");
				response.setHeader("Content-Disposition", "attachment; filename=reportTask.xls");
				ExportExcel.exportExcel(head, titles, values, response.getOutputStream());
			}catch(Exception e){
				e.printStackTrace();
				response.getWriter().write("<script>alert('导出日志数据失败！')</script>");
			}
			
			log.info("");
		}catch(final Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String getDraftTaskList(){
		User user = (User)request.getSession().getAttribute("sessionUser");
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		UserServerImpl userServer= (UserServerImpl)this.getService("userServer");
		List<User> userList =null;
		try{
			if(page==null){
				page = new Page(1);
			}
			if(worksTask==null){
				Object[] objs = {null,null,null,0,user.getUuid()};
				worksTaskList = taskServer.queryTaskAll(objs,page);
				for (WorksTask task : worksTaskList) {
					 userList =new ArrayList<User>();
					 userList= userServer.querySafferByuuid(task.getUuid());
					 task.setSafferList(userList);
				}
			}else{
				Object[] objs = {null,worksTask.getStartTime(),worksTask.getFinishTime(),0,user.getUuid()};
				worksTaskList = taskServer.queryTaskAll(objs,page);
				for (WorksTask task : worksTaskList) {
					userList =new ArrayList<User>();
					 userList= userServer.querySafferByuuid(task.getUuid());
					 task.setSafferList(userList);
				}
			}
			log.info("");
		}catch(final Exception e){
			e.printStackTrace();
		}
		return "tasklist";
	}  
	
	public String getOngoingTaskList(){
		User user = (User)request.getSession().getAttribute("sessionUser");
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		UserServerImpl userServer= (UserServerImpl)this.getService("userServer");
		List<User> userList =null;
		try{
			if(page==null){
				page = new Page(1);
			}
			if(worksTask==null){
				Object[] objs = {null,null,null,-100,user.getUuid()};
			 	worksTaskList = taskServer.queryTaskAll(objs,page);
				for (WorksTask task : worksTaskList) {
					userList =new ArrayList<User>();
					 userList= userServer.querySafferByuuid(task.getUuid());
					 task.setSafferList(userList);
				}
			}else{
				Object[] objs = {null,worksTask.getStartTime(),worksTask.getFinishTime(),-100,user.getUuid()};
			 	worksTaskList = taskServer.queryTaskAll(objs,page);
				for (WorksTask task : worksTaskList) {
					userList =new ArrayList<User>();
					 userList= userServer.querySafferByuuid(task.getUuid());
					 task.setSafferList(userList);
				}
			}
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "tasklist2";
	}
	
	public String getFindResultTaskList(){
		User user = (User)request.getSession().getAttribute("sessionUser");
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		UserServerImpl userServer = (UserServerImpl)this.getService("userServer");
		  try{
			  if(page==null){
					page = new Page(1);
				}
			if(worksTask==null){
				Object[] objs = {null,null,null,null,null,null,user.getUuid()};
				worksTaskList = taskServer.queryTaskBySearch(objs,page);
				 
			}else{
				String deptName = request.getParameter("executeDept");
				String safferID = request.getParameter("safferID");
				this.getCxt().put("executeDept", deptName);
				this.getCxt().put("safferID", safferID);
				
				Object[] objs = {null,worksTask.getStartTime(),worksTask.getFinishTime(),worksTask.getStatus(),deptName,safferID,user.getUuid()};
				worksTaskList = taskServer.queryTaskBySearch(objs,page);
			}
			try {
				this.getCxt().put("deptList", userServer.queryAllDept());
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		} 
		return "tasklist3";
	}
	
	public String getMyTaskList(){
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		User user = (User)request.getSession().getAttribute("sessionUser");
		try{
			if(page==null){
				page = new Page(1);
			}
			if(worksTask==null){
				worksTaskList = taskServer.queryWorkRemind(new Object[]{null,null,null,null,user.getUuid()},page);
			}else{
				Object[] objs = {null,worksTask.getStartTime(),worksTask.getFinishTime(),null,user.getUuid()};
				worksTaskList = taskServer.queryWorkRemind(objs,page);
			}
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "mytasklist";
	}
	
	public String getTaskProgessList(){
		User user = (User)request.getSession().getAttribute("sessionUser");
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			if(page==null){
				page = new Page(1);
			}
			WorkProgressList = taskServer.queryTaskProgressAll(new Object[]{
					DateUtil.DateUtilFormat(request.getParameter("stdate"), "-"),
					DateUtil.DateUtilFormat(request.getParameter("etdate"), "-"),
					null,user.getUuid()},page);
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getCxt().put("stdate", DataUtil.getStringK(request.getParameter("stdate")));
		this.getCxt().put("etdate", DataUtil.getStringK(request.getParameter("etdate")));
		return "taskProgresslist2";
	}
	
	public String getTaskProgessListByTask(){
		User user = (User)request.getSession().getAttribute("sessionUser");
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			WorkProgressList = taskServer.queryTaskProgressByTask(new Object[]{
					DateUtil.DateUtilFormat(request.getParameter("stdate"), "-"),
					DateUtil.DateUtilFormat(request.getParameter("etdate"), "-"),
					worksTask.getUuid(),user.getUuid()});
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		this.getCxt().put("stdate", DataUtil.getStringK(request.getParameter("stdate")));
		this.getCxt().put("etdate", DataUtil.getStringK(request.getParameter("etdate")));
		return "taskProgresslist";
	}
	
	public String saveTask(){
		
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		User user = (User)request.getSession().getAttribute("sessionUser");
		try{
			worksTask.setIssuedTime(new Date());
			worksTask.setStatus(0);
			int taskuuid = taskServer.saveTask(worksTask);
			String[] saffers=request.getParameterValues("saffer");
			
			 for (String saffer : saffers) {
				 	
				WorkProgress taskProgress =new WorkProgress();
				taskProgress.getInputUser().setUuid(DataUtil.getInt(saffer));
				worksTask.setUuid(taskuuid);
				taskProgress.setTask(worksTask);
				taskProgress.setProgress("0");
				taskProgress.setInputTime(new Date());
				taskProgress.setRemark("任务未执行");
				taskServer.saveTaskSaffer(taskuuid, DataUtil.getInt(saffer));
				taskServer.saveTaskProgress(taskProgress);
			}
			taskServer.saveTaskLeader(taskuuid,user.getUuid());
			worksTask = null;
			log.info("");
			
			List<WorksAccessory> WorksAccessoryList = getUploadWorksAccessory();
			for (WorksAccessory worksAccessory : WorksAccessoryList) {
				worksAccessory.setTaskUuid(taskuuid);
				taskServer.saveTaskAccessory(worksAccessory);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return getDraftTaskList();
	}
	
	public String saveTaskProgess(){
		User user = (User)request.getSession().getAttribute("sessionUser");
		workProgress.getInputUser().setUuid(user.getUuid());
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			taskServer.saveTaskProgress(workProgress);
			worksTask = workProgress.getTask();
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return forwardTo(DataUtil.getStringK(request.getParameter("forward")));
	}
	
	public String showTask(){
		User user = (User)request.getSession().getAttribute("sessionUser");
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		UserServerImpl userServer = (UserServerImpl)this.getService("userServer");
		try{
			if(worksTask==null){
				worksTask = new WorksTask();
				worksTask.setStatus(0);
				worksTask.setIssuedTime(new Date());
				worksTask.setTaskCode(CreatTaskCode.creatCode(user.getExecuteRank()));
				worksTask.setLeader(userServer.queryOneUser(user.getUuid()));
				
			}else{
				worksTask = taskServer.queryTask(worksTask.getUuid());
				List<User> safferList = taskServer.queryExeueByTaskuuid(worksTask.getUuid());
				worksTask.setSafferList(safferList);
				worksTask.setWorksAccessoryList(taskServer.queryTaskAccessory(worksTask.getUuid()));
				User leader=userServer.queryOneLeader(worksTask.getUuid());
				worksTask.setLeader(leader);
			}
			log.info("");
		}catch(Exception e){
			//e.printStackTrace();
		}
		try {
			this.getCxt().put("deptList", userServer.queryAllDept());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getCxt().put("type", DataUtil.getStringK(request.getParameter("type")));
		this.getCxt().put("forward", DataUtil.getStringK(request.getParameter("forward")));
		return "taskaddmod";
	}
	
	public String showTaskProgress(){
		
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			workProgress = taskServer.queryTaskProgress(workProgress.getUuid());
			log.info("");
		}catch(Exception e){
			//e.printStackTrace();
		}
		this.getCxt().put("type", DataUtil.getStringK(request.getParameter("type")));
		this.getCxt().put("forward", DataUtil.getStringK(request.getParameter("forward")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String inputtime = sdf.format(new Date());
		request.setAttribute("inputtime", inputtime);
		return "taskProgressaddmod";
	}
	
	public String editTask(){
		
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		User user = (User)request.getSession().getAttribute("sessionUser");
		try{
			WorksTask temp = taskServer.queryTask(worksTask.getUuid());
			worksTask.setIssuedTime(temp.getIssuedTime());
			taskServer.updateTask(worksTask);
			String[] saffers=request.getParameterValues("saffer");
			taskServer.deleteTaskSaffer(worksTask.getUuid());
			for (String saffer : saffers) {
				taskServer.saveTaskSaffer(worksTask.getUuid(),DataUtil.getInt(saffer));
			}
			
			List<WorksAccessory> WorksAccessoryList = getUploadWorksAccessory();
			for (WorksAccessory worksAccessory : WorksAccessoryList) {
				worksAccessory.setTaskUuid(worksTask.getUuid());
				taskServer.saveTaskAccessory(worksAccessory);
			}
			
			worksTask = null;
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return forwardTo(DataUtil.getStringK(request.getParameter("forward")));
	}
	
	public String editTaskProgess(){
		
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			taskServer.updateTaskProgress(workProgress);
			worksTask = workProgress.getTask();
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return forwardTo(DataUtil.getStringK(request.getParameter("forward")));
	}
	
	public String deleteTask(){
		
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			String[] ids = request.getParameterValues("checkbox_uuid");
			if(ids!=null){
				for (String id : ids) {
					taskServer.deleteTaskAccessoryByTask(DataUtil.getInt(id));
					taskServer.deleteTaskSaffer(DataUtil.getInt(id));
					taskServer.deleteTaskLeader(DataUtil.getInt(id));
					taskServer.deleteTaskProgress(DataUtil.getInt(id));
				}
				taskServer.deleteTask(ids);
			}else{
				taskServer.deleteTaskAccessoryByTask(worksTask.getUuid());
				taskServer.deleteTaskSaffer(worksTask.getUuid());
				taskServer.deleteTaskLeader(worksTask.getUuid());
				taskServer.deleteTaskProgress(worksTask.getUuid());
				taskServer.deleteTask(worksTask.getUuid());
			}
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return forwardTo(DataUtil.getStringK(request.getParameter("forward")));
	}
	
	public String deleteTaskProgess(){
		
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			String[] ids = request.getParameterValues("checkbox_uuid");
			if(ids!=null){
				workProgress = taskServer.queryTaskProgress(DataUtil.getInt(ids[0]));
				taskServer.deleteTaskProgress(ids);
			}else{
				workProgress = taskServer.queryTaskProgress(workProgress.getUuid());
				ids=new String[1];
				ids[0]=workProgress.getUuid().toString(); 
				taskServer.deleteTaskProgress(ids);
			}
			worksTask = workProgress.getTask();
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return forwardTo(DataUtil.getStringK(request.getParameter("forward")));
	}
	
	public String updateTaskStatu(){
		
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			if(!"".equals(worksTask.getStatus())&&"-1".equals(String.valueOf(worksTask.getStatus()))){
				taskServer.deleteTaskProgress(worksTask.getUuid());
			}
			taskServer.updateTaskStatus(worksTask.getUuid(), worksTask.getStatus());
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return forwardTo(DataUtil.getStringK(request.getParameter("forward")));
	}

	public String updateTaskProgessStatu(){
		
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return forwardTo(DataUtil.getStringK(request.getParameter("forward")));
	}
	
	
	
	public String getTaskInfo(){
		
		//TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public String getTaskProgessInfo(){
		
		//TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		try{
			
			log.info("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	
	public String forwardTo(String forward){
		if("draft".equals(forward)){
			return getDraftTaskList();
		}
		if("ongo".equals(forward)){
			return getOngoingTaskList();
		}
		if("find".equals(forward)){
			return getFindResultTaskList();
		}
		if("myprog".equals(forward)){
			return getTaskProgessListByTask();
		}
		return null;
	}
	
	
	public List<WorksAccessory> getUploadWorksAccessory(){
        try {
        	List<WorksAccessory> worksAccessoryList = new ArrayList<WorksAccessory>();
            String targetDirectory =servletContext.getRealPath("/upload/file");
            for(int i=0;i<accessory.length;i++){
            	String fileName = FileUtil.resetFileName(accessoryFileName[i]);
                File target = new File(targetDirectory,fileName); 
                FileUtils.copyFile(accessory[i], target);  
            	WorksAccessory worksAccessory = new WorksAccessory();
            	worksAccessory.setFilename(accessoryFileName[i]);
            	worksAccessory.setAccessorySize(""+accessory[i].length());
            	worksAccessory.setAccessoryType(accessoryContentType[i]);
            	worksAccessory.setRoute("/upload/file/"+fileName);
            	worksAccessoryList.add(worksAccessory);
            }
            return worksAccessoryList;
        } catch (Exception e) {
        	log.debug("上传失败");
        	e.printStackTrace();
        }
        return null;
	} 
	
	public String downAccessory(){
		TaskServerImpl taskServer = (TaskServerImpl)this.getService("taskServer");
		int aid = DataUtil.getInt(request.getParameter("aid"));
		try{
			WorksAccessory worksAccessory = taskServer.queryOneTaskAccessory(aid);
			String sysPath = servletContext.getRealPath("/");
			System.out.println(sysPath);
			DownFileUtil.getInstance().downFile(sysPath+""+worksAccessory.getRoute(), worksAccessory.getFilename(), response);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public WorksTask getWorksTask() {
		return worksTask;
	}

	public void setWorksTask(WorksTask worksTask) {
		this.worksTask = worksTask;
	}

	public WorkProgress getWorkProgress() {
		return workProgress;
	}

	public void setWorkProgress(WorkProgress workProgress) {
		this.workProgress = workProgress;
	}

	public List<WorksTask> getWorksTaskList() {
		return worksTaskList;
	}

	public void setWorksTaskList(List<WorksTask> worksTaskList) {
		this.worksTaskList = worksTaskList;
	}

	public List<WorkProgress> getWorkProgressList() {
		return WorkProgressList;
	}

	public void setWorkProgressList(List<WorkProgress> workProgressList) {
		WorkProgressList = workProgressList;
	}

	public File[] getAccessory() {
		return accessory;
	}

	public void setAccessory(File[] accessory) {
		this.accessory = accessory;
	}

	public String[] getAccessoryContentType() {
		return accessoryContentType;
	}

	public void setAccessoryContentType(String[] accessoryContentType) {
		this.accessoryContentType = accessoryContentType;
	}

	
}
