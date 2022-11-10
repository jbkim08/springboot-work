package com.sec.busanit.service;

import java.util.HashMap;
import java.util.List;

import com.sec.busanit.model.BoardVO;
import com.sec.busanit.model.CurriVO;
import com.sec.busanit.model.FileVO;

public interface BoardService {

	public int NoticeCount();
	public List<BoardVO> getNotice(HashMap<String, Object> hm);
	public int ClassNoticeCount(HashMap<String, Object> hm);
	public List<BoardVO> getClassNotice(HashMap<String, Object> hm);
	public int OneClassNoticeCount(HashMap<String, Object> hm);
	public List<BoardVO> getOneClassNotice(HashMap<String, Object> hm);
	
	public BoardVO read(int bno);
	public void insert(BoardVO board);
	public boolean update(BoardVO board);
	
	public List<CurriVO> getCurri();
	
//	글 삭제
	public void delete(int bno);
	
//	파일 등록
	public int nextBno();
	public void fileShareInsert(FileVO file);
	public void toutFileInsert(FileVO file);
	public List<FileVO> fileShare(HashMap<String, Object> hm);
	public int fileShareCount(HashMap<String, Object> hm);
	public List<FileVO> getFileList(HashMap<String, Object> hm);
	public int getFileCount(HashMap<String, Object> hm);
	public int fileCount(int bno);
	public FileVO getFile(int fno);
	public void fileDelete(int fno);
	
//search
	public List<BoardVO> findAll(HashMap<String, Object> hm);
	public int searchCount(HashMap<String, Object> hm);
	public List<FileVO> findFileShare(HashMap<String, Object> hm);
	public int findFileShareCount(HashMap<String, Object> hm);
	
}