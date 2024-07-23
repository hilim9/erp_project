package kr.happyjob.study.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.happyjob.study.system.model.NoticeModel;

public interface NoticeService {

	List<NoticeModel> noticeList(Map<String, Object> params) throws Exception;
	
	int noticeListCnt(Map<String, Object> params) throws Exception;
	
	int deleteNotice(Map<String, Object> params) throws Exception;
	
	int saveNotice(Map<String, Object> params) throws Exception;
	
	int noticeSaveFile(Map<String, Object> params, HttpServletRequest req) throws Exception;
	
	int updateNotice(Map<String, Object> params) throws Exception;
	
	int noticeUpdateFile(Map<String, Object> params, HttpServletRequest req) throws Exception;
	
	NoticeModel noticeDetail(Map<String, Object> params) throws Exception;
}
