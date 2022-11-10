package com.sec.busanit.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.busanit.mapper.BoardMapper;
import com.sec.busanit.model.BoardVO;
import com.sec.busanit.model.CurriVO;
import com.sec.busanit.model.FileVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public int NoticeCount() {
		
		return boardMapper.NoticeCount();
	}
	
	@Override
	public List<BoardVO> getNotice(HashMap<String, Object> hm) {
	
		return boardMapper.getNotice(hm);
	}

	@Override
	public int ClassNoticeCount(HashMap<String, Object> hm) {
		
		return boardMapper.ClassNoticeCount(hm);
	}
	
	@Override
	public List<BoardVO> getClassNotice(HashMap<String, Object> hm) {
		
		return boardMapper.getClassNotice(hm);
	}

	@Override
	public int OneClassNoticeCount(HashMap<String, Object> hm) {
		
		return boardMapper.OneClassNoticeCount(hm);
	}
	
	@Override
	public List<BoardVO> getOneClassNotice(HashMap<String, Object> hm) {
		
		return boardMapper.getOneClassNotice(hm);
	}

	@Override
	public List<CurriVO> getCurri() {
		
		return boardMapper.getCurri();
	}

	@Override
	public void insert(BoardVO board) {
//		파일 업로드 전
		boardMapper.insert(board);
		
//		파일 업로드 적용
//		boardMapper.insertSelectKey(board);
		
//		람다식 사용
//		board.getFileList().forEach(f ->{
//			f.setBno(board.getBno());
//			boardMapper.fileRegister(f);
//		});
		
//		람다식 사용 X
//		for (FileVO f : board.getFileList()) {
//			f.setBno(board.getBno());
//			boardMapper.fileRegister(f);
//		}
	}

	@Override
	public BoardVO read(int bno) {
		boardMapper.hitCount(bno);
		return boardMapper.read(bno);
	}

	@Override
	public boolean update(BoardVO board) {
		
		return boardMapper.update(board);
	}

	@Override
	public void delete(int bno) {
		boardMapper.delete(bno);
	}

	@Override
	public List<FileVO> fileShare(HashMap<String, Object> hm) {

		return boardMapper.fileShare(hm);
	}

	@Override
	public int fileShareCount(HashMap<String, Object> hm) {

		return boardMapper.fileShareCount(hm);
	}

	@Override
	public void fileShareInsert(FileVO file) {

		boardMapper.fileShareInsert(file);
	}

	@Override
	public List<BoardVO> findAll(HashMap<String, Object> hm) {
		return boardMapper.findAll(hm);
	}

	@Override
	public int searchCount(HashMap<String, Object> hm) {
		return boardMapper.searchCount(hm);
	}

	@Override
	public List<FileVO> findFileShare(HashMap<String, Object> hm) {

		return boardMapper.findFileShare(hm);
	}

	@Override
	public int findFileShareCount(HashMap<String, Object> hm) {

		return boardMapper.findFileShareCount(hm);
	}

	@Override
	public List<FileVO> getFileList(HashMap<String, Object> hm) {
		return boardMapper.getFileList(hm);
	}

	@Override
	public void toutFileInsert(FileVO file) {
		boardMapper.toutFileInsert(file);
	}

	@Override
	public int getFileCount(HashMap<String, Object> hm) {
		return boardMapper.getFileCount(hm);
	}

	@Override
	public int fileCount(int bno) {

		return boardMapper.fileCount(bno);
	}

	@Override
	public FileVO getFile(int fno) {

		return boardMapper.getFile(fno);
	}

	@Override
	public int nextBno() {

		return boardMapper.nextBno();
	}

	@Override
	public void fileDelete(int fno) {
		boardMapper.fileDelete(fno);
	}

}
