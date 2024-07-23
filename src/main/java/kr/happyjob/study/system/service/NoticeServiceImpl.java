package kr.happyjob.study.system.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.happyjob.study.common.comnUtils.FileUtilCho;
import kr.happyjob.study.system.dao.NoticeDao;
import kr.happyjob.study.system.model.NoticeModel;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Value("${fileUpload.rootPath}")
	private String rootPath;
	
	@Value("${fileUpload.virtualRootPath}")
	private String virtualRootPath;
	
	@Value("${fileUpload.noticePath}")
	private String noticePath;

	@Override
	public List<NoticeModel> noticeList(Map<String, Object> params) throws Exception {
		return noticeDao.noticeList(params);
	}

	@Override
	public int noticeListCnt(Map<String, Object> params) throws Exception {
		return noticeDao.noticeListCnt(params);
	}

	@Override
	public NoticeModel noticeDetail(Map<String, Object> params) throws Exception {
		return noticeDao.noticeDetail(params);
	}

	@Override
	public int saveNotice(Map<String, Object> params) throws Exception {
		return noticeDao.saveNotice(params);
	}
	
	@Override
	public int noticeSaveFile(Map<String, Object> params, HttpServletRequest req) throws Exception {
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest)req;
		
		String itemFilePath = noticePath + File.separator;
		FileUtilCho fileUpload = new FileUtilCho(mhsr, rootPath, virtualRootPath, itemFilePath);
		
		Map<String, Object> fileInfo = fileUpload.uploadFiles();
		
		if (fileInfo.get("file_nm") == null) {
			params.put("fileYn", "N");
			params.put("fileInfo", null);
		} else {
			params.put("fileYn", "Y");
			params.put("fileInfo", fileInfo);
		}
		
		return noticeDao.noticeSaveFile(params);
	}

	@Override
	public int updateNotice(Map<String, Object> params) throws Exception {
		return noticeDao.updateNotice(params);
	}
	
	@Override
	public int noticeUpdateFile(Map<String, Object> params, HttpServletRequest req) throws Exception {
		NoticeModel originFile = noticeDao.noticeDetail(params);
		
		if (originFile.getFile_name() != null) {
			File oldFile = new File(originFile.getPhsycal_path());
			oldFile.delete();
		}
		
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest)req;
		
		String itemFilePath = noticePath + File.separator;
		FileUtilCho fileUpload = new FileUtilCho(mhsr, rootPath, virtualRootPath, itemFilePath);
		
		Map<String, Object> fileInfo = fileUpload.uploadFiles();
		
		if (fileInfo.get("file_nm") == null) {
			params.put("fileYn", "N");
			params.put("fileInfo", null);
		} else {
			params.put("fileYn", "Y");
			params.put("fileInfo", fileInfo);
		}
		
		return noticeDao.noticeUpdateFile(params);
	}

	@Override
	public int deleteNotice(Map<String, Object> params) throws Exception {
		NoticeModel originFile = noticeDao.noticeDetail(params);
		
		if (originFile.getFile_name() != null) {
			File oldFile = new File(originFile.getPhsycal_path());
			oldFile.delete();
		}
		
		return noticeDao.deleteNotice(params);
	}
}
