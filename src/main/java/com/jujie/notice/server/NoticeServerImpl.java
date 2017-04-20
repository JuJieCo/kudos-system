package com.jujie.notice.server;

import java.util.List;

import com.jujie.global.util.Page;
import com.jujie.notice.Notice;
import com.jujie.notice.dao.NoticeDaoImpl;

public class NoticeServerImpl {	
	private NoticeDaoImpl noticeDao;

	public NoticeDaoImpl getNoticeDao() {
		return noticeDao;
	}
	public void setNoticeDao(NoticeDaoImpl noticeDao) {
		this.noticeDao = noticeDao;
	}
	public List<Notice> publishNotices(Object[] objs, String title, String userid , Page page) throws Exception{
		return noticeDao.publishNotices(objs, title, userid , page);
	}
	public Notice showNotice(String uuid) throws Exception{
		return noticeDao.showNotice(uuid);
	}
	public Notice showAdd(String userid) throws Exception{
		return noticeDao.showAdd(userid);
	}
	public Notice showUpdate(String uuid) throws Exception{
		return noticeDao.showNotice(uuid);
	}
	public void updateNotice(Notice notice) throws Exception{
		noticeDao.updateNotice(notice);
	}
	public void deleteNotice(String[] uuid) throws Exception{
		noticeDao.deleteNotice(uuid);
	}	
	public void addNotice(Notice notice) throws Exception {
		noticeDao.addNotice(notice);	
	}
	public List<Notice> ratifyNotices(Object[] objs, String title , String userid , Page page) throws Exception{
		return noticeDao.ratifyNotices(objs , title , userid , page);
	}
	public void ratifyNotice(Notice notice) throws Exception{
		noticeDao.ratifyNotice(notice);
	}
	public void upRatify(int status , String  uuid) throws Exception{
		noticeDao.upRatify(status, uuid);
	}
	public List<Notice> watchNotices(Object[] objs, String title , String userid, Page page) throws Exception{
		return noticeDao.watchNotices(objs, title, userid , page);
	}
}
