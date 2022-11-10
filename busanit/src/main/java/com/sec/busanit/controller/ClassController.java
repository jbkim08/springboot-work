package com.sec.busanit.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sec.busanit.model.BoardVO;
import com.sec.busanit.model.FileVO;
import com.sec.busanit.model.PageVO;
import com.sec.busanit.model.ReplyVO;
import com.sec.busanit.service.BoardService;
import com.sec.busanit.service.ReplyService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/class/*")
public class ClassController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("main_class")
	public String curri(@RequestParam("course") String course, Model model) {
		
		model.addAttribute("course", course);
		
		return "/class/main_class";
	}
	
	@GetMapping("list")
	public String curriDetail(@RequestParam("course") String course, @RequestParam("role") String role, Model model, String pageNum) {
		
		int currentPage = pageNum==null?1:Integer.parseInt(pageNum);
		int pageSize=3;
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("pageStart", (currentPage-1)*pageSize);
		hm.put("pageSize",  pageSize);
		hm.put("role",  role);
		hm.put("course",  course);
		
		
		int count;
		PageVO page;
		
		
		 if(role.equals("fshare")){
				List<FileVO >list = boardService.fileShare(hm);
				count = boardService.fileShareCount(hm);
				
				page = new PageVO(count, currentPage, pageSize);
				
				model.addAttribute("list", list);
				model.addAttribute("fileCount", boardService.getFileCount(hm));
		 } else {
			List<BoardVO> list = boardService.getClassNotice(hm);
			count = boardService.ClassNoticeCount(hm);
			
			page = new PageVO(count, currentPage, pageSize);
			
			for(BoardVO vo:list) {
				
				int replyCount = replyService.replyCount(vo.getBno());
				vo.setReplycount(replyCount);
				
				int fileCount = boardService.fileCount(vo.getBno());
				vo.setFileCount(fileCount);
			}
			model.addAttribute("list", list);
			
		}
		
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("course", course);
		model.addAttribute("role", role);
		
		return "/class/list_"+role;
	}
	
	//검색 기능 유지
	@GetMapping("list2")
	public String curriDetailS(@RequestParam("course") String course, @RequestParam("role") String role, Model model, String pageNum,
			@RequestParam(name="word", defaultValue="") String word) {
		int currentPage = pageNum==null?1:Integer.parseInt(pageNum);
		int pageSize=3;
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("word", word); //검색 기능
		hm.put("pageStart", (currentPage-1)*pageSize);
		hm.put("pageSize",  pageSize);
		hm.put("role",  role);
		hm.put("course",  course);
		
		int count;
		PageVO page;
		
		
		 if(role.equals("fshare")){
				List<FileVO >list = boardService.findFileShare(hm);
				count = boardService.findFileShareCount(hm);
				
				page = new PageVO(count, currentPage, pageSize);
				
				model.addAttribute("list", list);
		 } else {
			List<BoardVO> list = boardService.findAll(hm);
			count = boardService.searchCount(hm);
			page = new PageVO(count, currentPage, pageSize);
			
			for(BoardVO vo:list) {
				
				int replyCount = replyService.replyCount(vo.getBno());
				vo.setReplycount(replyCount);
			}
			model.addAttribute("list", list);
			
		}
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("course", course);
		model.addAttribute("role", role);
		model.addAttribute("word", word); //
		log.info("count............."+count);
		log.info("word............."+word);
		
		return "/class/list_"+role;
	}
	
//	글 쓰기 접근
	@GetMapping("register")
	public String register(@RequestParam("course") String course, @RequestParam("role") String role, Model model) {
		model.addAttribute("course", course);
		model.addAttribute("role", role);
		
		return "/class/register_"+role;
	}
	
//	그냥 글 쓰기
	@PostMapping("insert")
	public String classInsert(BoardVO board, MultipartFile[] uploads, HttpSession session) {
		
		if(uploads!=null) {
		
		String uploadFolder = session.getServletContext().getRealPath("/resources/upload");
		System.out.println("uploadFolder=================="+uploadFolder);
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadFolder + File.separator + today;
		System.out.println(saveFolder);
		File folder = new File(saveFolder);
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		for(MultipartFile multipartFile:uploads) {
			String originFile = multipartFile.getOriginalFilename();
			System.out.println(multipartFile.getOriginalFilename());
			if(!originFile.isEmpty()) {
				FileVO fileVO = new FileVO();
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid.toString()+"_"+originFile;
				String fileType = multipartFile.getContentType();
				log.info("filetype: "+fileType);
				fileType = fileType.substring(0, fileType.indexOf("/"));
				fileVO.setFiletype(fileType);
				fileVO.setOriginfile(originFile);
				fileVO.setSavefile(saveFileName);
				fileVO.setSavefolder(saveFolder);
				fileVO.setWriter(board.getWriter());
				fileVO.setRole(board.getRole());
				fileVO.setBno(boardService.nextBno());
				System.out.println("bno========================"+fileVO.getBno());
				
				try {
					File savefile = new File(saveFolder, saveFileName);
					multipartFile.transferTo(savefile);
					fileList.add(fileVO);
					boardService.toutFileInsert(fileVO);
				} catch(IllegalStateException | IOException e) {
					e.getStackTrace();
				}
			}
		}
		board.setFileList(fileList);
		}
		boardService.insert(board);
		
		return "redirect:/class/list?course="+board.getCourse()+"&&role="+board.getRole();
	}
	
//	글 삭제
	@GetMapping("delete")
	public String classDelete(@RequestParam("course") String course, @RequestParam("role") String role, int bno) {
		boardService.delete(bno);
		
		return "redirect:/class/list?course="+course+"&&role="+role;
	}
	
//	파일 추가 답글쓰기
	@PostMapping("replyFile")
	public @ResponseBody HashMap<String, Object> fileUpload(HttpSession session, MultipartHttpServletRequest request) {
		
		Object result = new Object();
		HashMap<String, Object> returnResult = new HashMap<String, Object>();
		
//		FileVO file = new FileVO();
		List<FileVO> list = new ArrayList<FileVO>();
		
		String uploadFolder = session.getServletContext().getRealPath("/resources/upload");
		System.out.println("uploadFolder======================"+uploadFolder);
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadFolder + File.separator + today;
		System.out.println(saveFolder);
		File folder = new File(saveFolder);
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		String[] writer = request.getParameterValues("writer");
		String[] content = request.getParameterValues("content");
		String[] role = request.getParameterValues("role");
		String[] bno = request.getParameterValues("bno");
		
		Iterator<String> itr = request.getFileNames();
		
		List<MultipartFile> fileList = request.getFiles((String)itr.next());
		
		
		for (MultipartFile multipartFile : fileList) {
			int i=0;
			System.out.println("==============="+writer[0]);
			System.out.println("==============="+content[0]);
			System.out.println("==============="+role[0]);
			System.out.println("==============="+bno[0]);
			
			String originFile = multipartFile.getOriginalFilename();
			
			if (!originFile.isEmpty()) {
				FileVO f = new FileVO();
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid.toString() + "_" + originFile;
				String fileType = multipartFile.getContentType();
				
				fileType = fileType.substring(0, fileType.indexOf("/"));
				f.setFiletype(fileType);
				f.setOriginfile(originFile);
				f.setSavefolder(saveFolder);
				f.setSavefile(saveFileName);
				f.setWriter(writer[i]);
				f.setContent(content[i]);
				f.setRole(role[i]);
				f.setBno(Integer.parseInt(bno[i]));
				
				System.out.println(f.toString());
				
				File savefile = new File(saveFolder, saveFileName);
				
				i++;
				try {
					multipartFile.transferTo(savefile);
					list.add(f);
					boardService.toutFileInsert(f);
					
					result="success";
				} catch (IllegalStateException e) {
					e.printStackTrace();
					result="error";
				} catch (IOException e) {
					e.printStackTrace();
					result="error";
				}
			}
		}
	
		System.out.println(result);
		
		returnResult.put("result", result);
		
		return returnResult;
	}
	
//	파일 리스트 불러오기
	@GetMapping("getFileList/{bno}/{role}")
	@ResponseBody
	public ResponseEntity<List<FileVO>> getToutList(@PathVariable("bno") int bno, @PathVariable("role") String role) {

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("bno", bno);
		hm.put("role", role);
		
		List<FileVO> list = boardService.getFileList(hm);
		for(FileVO vo:list)
		System.out.println(vo.toString());
		
		return new ResponseEntity<List<FileVO>>(boardService.getFileList(hm), HttpStatus.OK);
	}
	
//	파일 추가 글쓰기
	@PostMapping("register")
	public String register(FileVO file, MultipartFile[] uploads, HttpSession session,@RequestParam("course") String course, @RequestParam("role") String role, String pageNum, Model model) {
		
		String uploadFolder = session.getServletContext().getRealPath("/resources/upload");
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadFolder + File.separator + today;
		File folder = new File(saveFolder);
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		for (MultipartFile multipartFile : uploads) {
			String originFile = multipartFile.getOriginalFilename();
			
			if (!originFile.isEmpty()) {
				FileVO f = new FileVO();
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid.toString() + "_" + originFile;
				String fileType = multipartFile.getContentType();
				
				fileType = fileType.substring(0, fileType.indexOf("/"));
				f.setFiletype(fileType);
				f.setOriginfile(originFile);
				f.setSavefolder(saveFolder);
				f.setSavefile(saveFileName);
				f.setWriter(file.getWriter());
				f.setContent(file.getContent());
				f.setRole(file.getRole());
				
				System.out.println(f.toString());
				
				File savefile = new File(saveFolder, saveFileName);
				
				try {
					multipartFile.transferTo(savefile);
					fileList.add(f);
					boardService.fileShareInsert(f);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		int currentPage = pageNum==null?1:Integer.parseInt(pageNum);
		int pageSize=3;
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("pageStart", (currentPage-1)*pageSize);
		hm.put("pageSize",  pageSize);
		hm.put("role",  role);
		hm.put("course",  course);
		
		int count = boardService.fileShareCount(hm);
		
		PageVO page = new PageVO(count, currentPage, pageSize);
		
		model.addAttribute("list", fileList);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("course", course);
		model.addAttribute("role", role);
		
		return "redirect:/class/list?course="+course+"&&role="+role;
	}
	
	@GetMapping("detail")
	public String classDetail(@RequestParam("course") String course, @RequestParam("role") String role, @RequestParam("bno") int bno, Model model) {
		
		BoardVO board = boardService.read(bno);
		String content = board.getContent();
		content = content.replace("\r\n", "<br/>");
		
		board.setContent(content);
		
		List<ReplyVO> reply = replyService.getReplyList(bno);
		int replycount = replyService.replyCount(bno);
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("bno", bno);
		hm.put("course", course);
		hm.put("role", role);
		
//		과제제출 파일 보기
		List<FileVO> list = boardService.getFileList(hm);
		for(FileVO vo:list)
		System.out.println(vo.toString());
		
		model.addAttribute("fileCount", boardService.getFileCount(hm));
		model.addAttribute("replycount", replycount);
		model.addAttribute("reply", reply);
		model.addAttribute("board", board);
		model.addAttribute("list", list);
		model.addAttribute("course", course);
		model.addAttribute("role", role);
		model.addAttribute("bno", bno);
		
		return "/class/detail_"+role;
	}
	
	@GetMapping("update")
	public String classUpdate(@RequestParam("course") String course, @RequestParam("role") String role, @RequestParam("bno") int bno, Model model) {

		BoardVO board = boardService.read(bno);
		
		model.addAttribute("board", board);
		model.addAttribute("course", course);
		model.addAttribute("role", role);
		
		return "/class/update_"+role;
	}
	
	@PostMapping("update")
	public String classUpdate(BoardVO board, MultipartFile[] uploads, HttpSession session) {
		
		System.out.println(board.getFileList());
		
		BoardVO b = boardService.read(board.getBno());
		
		if(board.getWriter()==null)
			board.setWriter(b.getWriter());
		if(board.getRole()==null)
			board.setRole(b.getRole());
		if(board.getCourse()==null)
			board.setCourse(b.getCourse());
			
		boardService.update(board);
		
		return "redirect:/class/list?course="+board.getCourse()+"&&role="+board.getRole();
	}
	
	@GetMapping("download/{fno}")
	public String download(@PathVariable("fno") int fno, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		
		FileVO fvo = boardService.getFile(fno);
		
		String fileName = null;
		try {
			String path = fvo.getSavefolder();
			
			File file = new File (path, fvo.getSavefile());
			
			BufferedInputStream in = new BufferedInputStream (new FileInputStream(file));
			
			String header = request.getHeader("User-Agent");
			
			if ((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
				fileName = URLEncoder.encode(fvo.getOriginfile(), "UTF-8");
			} else {
				fileName = new String(fvo.getOriginfile().getBytes("UTF-8"), "iso-8859-1");
			}
			
			response.setContentType("application/octet-stream");
			
			response.setHeader("Content-Disposition", "attachment;filename=\""+ fileName + "\"");
			
			FileCopyUtils.copy(in, response.getOutputStream());
			
			in.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return "";
	}
	
	@GetMapping("fileDelete")
	public String fileDelete(@RequestParam int fno, @RequestParam String course, @RequestParam String role, Model model) {
		
		boardService.fileDelete(fno);
		
		return "redirect:/class/list?course="+course+"&&role="+role;
	}
}