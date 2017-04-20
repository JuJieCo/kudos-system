package com.jujie.notice.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.global.util.DateUtil;
import com.jujie.global.util.Page;
import com.jujie.notice.Notice;
import com.jujie.notice.server.NoticeServerImpl;
import com.jujie.user.User;

public class NoticeAction extends BaseActionSupper {
	private static final long serialVersionUID = 1L;
	//private static Log log = LogFactory.getLog(NoticeAction.class);
	public List<Notice> noticeList;
	private Notice notice;
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
    private Page page;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getQuery() {
		if (notice.getStart_time() == null || "".equals(notice.getStart_time())) {
			notice.setStart_time(DateUtil.DateUtilFormat("1900-01-01", "-"));
		}
		if (notice.getEnd_time() == null || "".equals(notice.getEnd_time())) {
			notice.setEnd_time(DateUtil.DateUtilFormat("3000-12-31", "-"));
		}
		if (notice.getStart_time() != null || !"".equals(notice.getStart_time())) {
			request.setAttribute("stime", DateUtil.DateUtilFormat(notice.getStart_time(), "-"));
		}
		if ("1900-01-01".equals(DateUtil.DateUtilFormat(notice.getStart_time(), "-"))) {
			request.setAttribute("stime", "");
		}
		if (notice.getEnd_time() != null || !"".equals(notice.getEnd_time())) {
			request.setAttribute("etime", DateUtil.DateUtilFormat(notice.getEnd_time(), "-"));
		}
		if ("3000-12-31".equals(DateUtil.DateUtilFormat( notice.getEnd_time(), "-"))) {
			request.setAttribute("etime", "");
		}
		if (notice.getTitle() != null && !"".equals(notice.getTitle())) {
			request.setAttribute("titl", notice.getTitle());
		}
		String tag = request.getParameter("tag");
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this.getService("noticeServer");
		if("p".equals(tag)&&tag!=null){
			try {
				if(page==null){
					page = new Page(1);
				}
				if(notice==null){
					this.publishNotices();
				}else{
					User user = (User)request.getSession().getAttribute("sessionUser");
					String userid = String.valueOf(user.getUuid());
					Object[] objs = {notice.getStart_time(), notice.getEnd_time()};
					noticeList = noticeServerImpl.publishNotices(objs , notice.getTitle() ,userid ,page);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setNoticeType(1);
			request.setAttribute("tag", "p");
		}
		if("t".equals(tag)&&tag!=null){
			try {
				if(page==null){
					page = new Page(1);
				}
				if(notice==null){
					this.ratifyNotices();
				}else{
					User user = (User)request.getSession().getAttribute("sessionUser");
					String userid = String.valueOf(user.getUuid());
					Object[] objs = {notice.getStart_time(), notice.getEnd_time()};
					noticeList = noticeServerImpl.ratifyNotices(objs, notice.getTitle() ,userid , page);
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setNoticeType(2);
			request.setAttribute("tag", "t");
		}
		if("w".equals(tag)&&tag!=null){
			try {
				if(page==null){
					page = new Page(1);
				}
				if(notice==null){
					this.watchNotices();
				}else{
					User user = (User)request.getSession().getAttribute("sessionUser");
					String userid = String.valueOf(user.getUuid());
					Object[] objs = {notice.getStart_time(), notice.getEnd_time()};
					noticeList = noticeServerImpl.watchNotices(objs, notice.getTitle() ,userid , page);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setNoticeType(3);
			request.setAttribute("tag", "w");
		}
		return "noticelist";
	}
	public String publishNotices(){
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this.getService("noticeServer");
		try {
			if(page==null){
				page = new Page(1);
			}
			User user = (User)request.getSession().getAttribute("sessionUser");
			String userid = String.valueOf(user.getUuid());
			noticeList = noticeServerImpl.publishNotices(new Object[] {null, null}, null ,userid ,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setNoticeType(1);
		request.setAttribute("tag", "p");
		return "noticelist";
	}
	public String ratifyNotices(){
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this.getService("noticeServer");
		try {
			if(page==null){
				page = new Page(1);
			}
			User user = (User)request.getSession().getAttribute("sessionUser");
			String userid = String.valueOf(user.getUuid());
			noticeList = noticeServerImpl.ratifyNotices(new Object[] {null, null}, null ,userid , page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setNoticeType(2);
		request.setAttribute("tag", "t");
		return "noticelist";
	}
	public String watchNotices(){
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this.getService("noticeServer");
		try {
			if(page==null){
				page = new Page(1);
			}
			User user = (User)request.getSession().getAttribute("sessionUser");
			String userid = String.valueOf(user.getUuid());
			noticeList = noticeServerImpl.watchNotices(new Object[] {null, null}, null ,userid , page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setNoticeType(3);
		request.setAttribute("tag", "w");
		return "noticelist";
	}	
	public String showNotice() {
		String  uuid = request.getParameter("uuid");
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this .getService("noticeServer");
		try {
			notice = noticeServerImpl.showNotice(uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String tag =request.getParameter("tag");
		if("p".equals(tag)&&tag!=null){
			this.setNoticeType(1);
		}
		if("t".equals(tag)&&tag!=null){
			this.setNoticeType(2);
		}
		if("w".equals(tag)&&tag!=null){
			this.setNoticeType(3);
		}	
		return "shownotice";
	}
	public String showEditNotice(){
		String method = request.getParameter("method");
		String uuid = request.getParameter("uuid");
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this .getService("noticeServer");
		if("isAdd".equals(method)&&method!=null){
			try {
				User user = (User)request.getSession().getAttribute("sessionUser");
				String userid = String.valueOf(user.getUuid());
				notice =noticeServerImpl.showAdd(userid);
				notice.setRank(1);
				request.setAttribute("systime", this.getSimDate());
				request.setAttribute("editsub", 0);
				this.setNoticeType(1);
				return "addofedit";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if("isUpdate".equals(method)&&method!=null){
			try {
				request.setAttribute("systime","");
				request.setAttribute("editsub", 1);
				notice =noticeServerImpl.showUpdate(uuid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.setNoticeType(1);
		return "updateofedit";
	}
	public String editNotice(){
		String method = request.getParameter("method");
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this .getService("noticeServer");
		if("add".equals(method)&&method!=null){
			try {
				notice.setPublic_time(new Date());
				notice.setStatus(0);
				noticeServerImpl.addNotice(notice);
				//this.setNoticeType(1);
				return this.publishNotices();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if("update".equals(method)&&method!=null){
			try {
				noticeServerImpl.updateNotice(notice);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//this.setNoticeType(1);
		return this.publishNotices();
	}
	public String deleteNotice() {
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this.getService("noticeServer");
		String[] uuid = request.getParameterValues("uuid");
		String tag = request.getParameter("tag");
		try {
			noticeServerImpl.deleteNotice(uuid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//this.setNoticeType(1);
			if("t".equals(tag)){
				return this.ratifyNotices();
			}else{
				return this.publishNotices();
			}
			
		
	}
	public String upRatify(){
		String uuid = request.getParameter("uuid");
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this .getService("noticeServer");
		try {
			noticeServerImpl.upRatify(1, uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//this.setNoticeType(1);
		return publishNotices();
	}
	public String showRatifyNotice(){
		String uuid = request.getParameter("uuid");
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this .getService("noticeServer");
		try {
			notice = noticeServerImpl.showNotice(uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setNoticeType(2);
		return "showratify";
	}
	public String ratifyNotice(){;
		NoticeServerImpl noticeServerImpl = (NoticeServerImpl) this .getService("noticeServer");
		try {
			noticeServerImpl.ratifyNotice(notice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//this.setNoticeType(1);
		return this.ratifyNotices();
	}
	public void setNoticeType(int index){
		request.setAttribute("noticeType", index);
	}	
	public String getSimDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String temp =sdf.format(new Date());
		return temp;
	}
}
