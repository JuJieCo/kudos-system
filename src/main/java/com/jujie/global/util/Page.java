package com.jujie.global.util;

import java.util.List;

public class Page {
	
	public static final int DATABASE_TYPE_MYSQL = 0;
	
	public static final int DATABASE_TYPE_ORACLE = 1;
	
	public static final int DATABASE_TYPE_MSSQL = 2;
	
	/**
	 * ��ǰҳ
	 */
	private int currentPage = 1;
	@SuppressWarnings("unused")
	/**
	 * ��ҳ��
	 */
	private int maxPage = 0;
	/**
	 * ÿҳ��¼��
	 */
	private int pageSize = 10;
	/**
	 * �ܼ�¼��
	 */
	private int maxSize = 0;
	
	/**
	 * ���
	 */
	private List list;
	
	/**
	 * ��ҳ��ǩ
	 */
	private String pageHtml;

	/**
	 * ����Pages����
	 * 
	 * @param currentPage
	 *            ��ǰҳ
	 */
	public Page(int currentPage) {
		this.currentPage = currentPage == 0 ? 1 : currentPage;
	}

	/**
	 * ����Pages����
	 * 
	 * @param currentPage
	 * @param pageSize
	 */
	public Page(int currentPage, int pageSize) {
		this.currentPage = currentPage == 0 ? 1 : currentPage;
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage == 0 ? 1 : currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage == 0 ? 1 : currentPage;
	}

	public int getMaxPage() {
		return (maxSize + pageSize - 1) / pageSize;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * mysql��ҳ��װ���
	 * 
	 * @param sql
	 * @return
	 */
	public String mysqlPageSQL(String sql) {
		int start = (currentPage - 1) * pageSize;
		//int end = currentPage * pageSize;
		String str = "select t1.* from ("
				+ sql
				+ ") t1 LIMIT " + start + "," + pageSize;
		return str;
	}
	
	/**
	 * oracle��ҳ��װ���
	 * 
	 * @param sql
	 * @return
	 */
	public String oraclePageSQL(String sql) {
		int start=(currentPage-1)*pageSize;
		int end=currentPage*pageSize;
		String str = "select * from (select t1.*,rownum r from (select t.* from ("+sql+") t where rownum<="+end+" ) t1) t2 where t2.r>"+start;
		return str;
	}
	
	/**
	 * sqlserver��ҳ��װ���
	 * @param sql
	 * @return
	 */
	public String mssqlPageSQL(String sql){
		try{
			StringBuffer sb = new StringBuffer("");
			if(sql!=null){
				sql = sql.toLowerCase();
				sb.append("select top "+(currentPage*pageSize));
				sb.append(sql.substring(6));
			}
			String[] fileds = sql.substring(sql.lastIndexOf("order by")).split(" ");
			String ob1 = "";
			String ob2 = "";
			if(fileds[2].indexOf(".")!=-1){
				fileds[2] = fileds[2].substring(fileds[2].indexOf(".")+1);
			}
			if(fileds.length>3){
				if("desc".equals(fileds[3])){
					ob1 = fileds[2]+" asc ";
					ob2 = fileds[2]+" desc ";
				}else{
					ob1 = fileds[2]+" desc ";
					ob2 = fileds[2]+" asc ";
				}
			}else{
				ob1 = fileds[2]+" desc ";
				ob2 = fileds[2]+" asc ";
			}
			int t_pageSize = pageSize;
			if(currentPage*pageSize-this.getMaxSize()>0){
				t_pageSize = this.getMaxSize() - (currentPage-1)*pageSize;
			}
			String str = "select * from (select top "+t_pageSize+" * from ("+sb.toString()+") as a order by "+ob1+" )as b order by "+ob2;
			return str;
		}catch(Exception e){
			e.printStackTrace();
		}
		return sql;
	}

	public static void main(String[] args) {	
		Page page = new Page(3);
		String sql = "select task.uuid,task.task_code,task.title,task.task_content,task.task_level,task.start_time,task.finish_time,"
			+"task.issued_time,task.status,task.hold1,DATEDIFF(day,getdate(),task.finish_time) remindDay,task.remark,"
			+"saffer.uuid saffer_uuid,saffer.execute_dept saffer_dept,saffer.execute_userName saffer_name,"
			+"leader.uuid leader_uuid,leader.execute_dept leader_dept,leader.execute_userName leader_name "
			+"from works_task task "
			+"inner join execute_user saffer on task.execute_user_uuid=saffer.uuid "
			+"inner join execute_user leader on task.leader_uuid=leader.uuid "
			+" order by task.start_time";
		
		System.out.println(page.mssqlPageSQL(sql));
	}
	
	/**
	 * ��ȡ�ܼ�¼���װ���
	 * 
	 * @param sql
	 * @return
	 */
	public String maxSizeSQL(String sql) {
		return "select count(*) from (" + sql + ") t";
	}
	
	public String maxSizeSQL2(String sql) {
		StringBuffer sb = new StringBuffer("");
		if(sql!=null){
			sql = sql.toLowerCase();
			sb.append("select top 100 Percent ");
			sb.append(sql.substring(6));
		}
		return "select count(*) from (" + sb.toString() + ") t";
	}

	public Page() {
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
		setPageHtml(createPageTag());
	}

	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}
	
	/**
	 * ��ҳ��Ϣ
	 */
	public String createPageTag(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<div id=\"pageTag\" align=\"right\" style=\"font-size:12px\">");
		if(getMaxPage() == 1 || getMaxPage() == 0){
			stringBuffer.append("��ҳ&nbsp;|&nbsp;��һҳ&nbsp;|&nbsp;��һҳ&nbsp;|&nbsp;βҳ");
		}else if(currentPage > 1 && currentPage < getMaxPage() && maxSize != 0){
			stringBuffer.append("<a href=\"javascript:void(0);\" onclick=\"selectPage('1');\">��ҳ</a>&nbsp;|&nbsp;");
			stringBuffer.append("<a href=\"javascript:void(0);\" onclick=\"selectPage('"+(currentPage-1)+"');\">��һҳ</a>&nbsp;|&nbsp;");
			stringBuffer.append("<a href=\"javascript:void(0);\" onclick=\"selectPage('"+(currentPage+1)+"');\">��һҳ</a>&nbsp;|&nbsp;");
			stringBuffer.append("<a href=\"javascript:void(0);\" onclick=\"selectPage('"+getMaxPage()+"');\">βҳ</a>");
		}else if(currentPage <= 1 && getMaxPage() != 1 && maxSize != 0){
			stringBuffer.append("��ҳ&nbsp;|&nbsp;��һҳ&nbsp;|&nbsp;");
			stringBuffer.append("<a href=\"javascript:void(0);\" onclick=\"selectPage('"+(currentPage+1)+"');\">��һҳ</a>&nbsp;|&nbsp;");
			stringBuffer.append("<a href=\"javascript:void(0);\" onclick=\"selectPage('"+getMaxPage()+"');\">βҳ</a>");
		}else if(currentPage >= getMaxPage() && getMaxPage() != 1 && maxSize != 0){
			stringBuffer.append("<a href=\"javascript:void(0);\" onclick=\"selectPage('1');\">��ҳ</a>&nbsp;|&nbsp;");
			stringBuffer.append("<a href=\"javascript:void(0);\" onclick=\"selectPage('"+(currentPage-1)+"');\">��һҳ</a>&nbsp;|&nbsp;");
			stringBuffer.append("��һҳ&nbsp;|&nbsp;βҳ");	
		}
		stringBuffer.append(" &nbsp;&nbsp;��");
		if(getMaxPage() == 0){
			stringBuffer.append("1");
		}else{
			stringBuffer.append(getMaxPage());
		}
		stringBuffer.append("ҳ&nbsp;��ǰ��");
		stringBuffer.append(currentPage);
		stringBuffer.append("ҳ&nbsp;");
		stringBuffer.append("</div>");
		return stringBuffer.toString();
	}

}
