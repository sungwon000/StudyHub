package com.kh.FinalProject.notice.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.kh.FinalProject.booksales.model.vo.BookReg;
import com.kh.FinalProject.notice.model.exception.NoticeException;
import com.kh.FinalProject.notice.model.service.NoticeService;
import com.kh.FinalProject.notice.model.vo.Notice;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService nService;

	// 공지사항 리스트
	@RequestMapping("nlist.no")
	public ModelAndView noticeList(ModelAndView mv) {

		ArrayList<Notice> list = nService.selectList();

		if (list != null) {

			mv.addObject("list", list);
			mv.setViewName("noticeList");
		} else {
			throw new NoticeException("공지사항 조회 실패");
		}

		return mv;

	}

	// 공지사항 보기
	@RequestMapping("ndetail.no")
	public ModelAndView noticeDetail(@RequestParam("nId") int nId, ModelAndView mv) {

		Notice notice = nService.selectNotice(nId);

		if (notice != null) {
			mv.addObject("n", notice).setViewName("noticeListDetail");
		} else {
			throw new NoticeException("공지사항 상세 보기 실패");
		}

		return mv;
	}

	// 공지사항 등록 페이지
	@RequestMapping("ninsertView.no")
	public String noticeIncert() {
		return "noticeInsert";
	}

	// 공지사항 등록
	@RequestMapping("ninsert.no")
	public String noticeInert(@ModelAttribute Notice n, @RequestParam("uploadFile") MultipartFile uploadFile,
			HttpServletRequest request) {

		if (uploadFile != null && !uploadFile.isEmpty()) {

			String renameFileName = saveFile(uploadFile, request);

			if (renameFileName != null) {
				n.setOriginalFileName(uploadFile.getOriginalFilename());
				n.setRenameFileName(renameFileName);
			}

		}

		int result = nService.inserNotice(n);

		if (result > 0) {
			return "redirect:nlist.no";
		} else {
			throw new NoticeException("게시글 등록을 실패하였습니다.");
		}

	}

	// 파일저장
	public String saveFile(MultipartFile file, HttpServletRequest request) {
		// 파일이 저장될 경로 설정 : 작은 resources 불러오기
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\nuploadFiles"; // \를 하나만 하면 인지를 못한다

		File folder = new File(savePath);

		if (!folder.exists()) {
			folder.mkdirs();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String originFileName = file.getOriginalFilename();
		String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis())) + "."
				+ originFileName.substring(originFileName.lastIndexOf(".") + 1);

		String renamePath = folder + "\\" + renameFileName;
		try {
			file.transferTo(new File(renamePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return renameFileName;
	}

	// 공지사항 업데이트
	@RequestMapping("nupView.no")
	public ModelAndView noticeUpdateView(@RequestParam("nId") int nId, ModelAndView mv) {

		Notice notice = nService.selectNotice(nId);

		mv.addObject("notice", notice).setViewName("noticeUpdate");

		return mv;
	}

	@RequestMapping("nupdate.no")
	public ModelAndView noticeUpdate(@ModelAttribute Notice n, @RequestParam("reloadFile") MultipartFile reloadFile,
			HttpServletRequest request, ModelAndView mv) {

		if (reloadFile != null && !reloadFile.isEmpty()) {
			deleteFile(n.getRenameFileName(), request);
		}

		String renameFileName = saveFile(reloadFile, request);

		if (renameFileName != null) {
			n.setOriginalFileName(reloadFile.getOriginalFilename());
			n.setRenameFileName(renameFileName);
		}

		int result = nService.updateNotice(n);
		if (result > 0) {
			// page --> ndetail
			mv.setViewName("redirect:ndetail.no?nId=" + n.getnId());
		} else {
			throw new NoticeException("게시글 수정에 실패하였습니다.");
		}

		return mv;
	}

	public void deleteFile(String fileName, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\nuploadFiles";

		File f = new File(savePath + "\\" + fileName);

		if (f.exists()) {
			f.delete();
		}
	}

	// 공지사항 삭제
	@RequestMapping("ndelete.no")
	public String noticeDelete(@RequestParam("nId") int nId) {

		int result = nService.noticeDelete(nId);

		if (result > 0) {
			return "redirect:nlist.no";
		} else {
			throw new NoticeException("게시글 삭제에 실패하였습니다.");
		}

	}

	// 공지사항 top list
	@RequestMapping("topList.no")
	public void noticeTopList(ModelAndView mv, HttpServletResponse response) throws JsonIOException, IOException {

		ArrayList<Notice> nlist = nService.selectTopList();
		
		for(Notice n : nlist) {
			n.setnTitle(URLEncoder.encode(n.getnTitle(), "UTF-8"));
		}

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		gson.toJson(nlist, response.getWriter());
	}
}
