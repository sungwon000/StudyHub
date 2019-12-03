package com.kh.FinalProject.booksales.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.FinalProject.booksales.model.dao.BsDAO;
import com.kh.FinalProject.booksales.model.vo.BookReg;
import com.kh.FinalProject.booksales.model.vo.BookSales;

@Service("bsService")
public class BsServiceImpl implements BsService{

	@Autowired
	private BsDAO bsDAO;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 중고서적 리스트
	public ArrayList<BookReg> selectList(){
		return bsDAO.selectList(sqlSession);
	}
	
	// 중고서적 디테일 보기
	public BookReg selectBs(int brBnumber) {
		BookReg br = null;
		
		int result = bsDAO.addReadCount(sqlSession, brBnumber);
		
		if(result > 0) {
			br = bsDAO.selectBs(sqlSession, brBnumber);
		}
		
		return br;
	}

	// 중고서적 등록
	public int inserBs(BookReg br) {
		return bsDAO.insertBs(sqlSession, br);
	}

	// 중고서적 수정
	public int updateBs(BookReg br) {
		return bsDAO.updateBs(sqlSession, br);
	}

	// 중고서적 삭제
	public int bsDelete(int brBnumber) {
		return bsDAO.bsDelete(sqlSession, brBnumber);
	}

	// 주문완료
	public int updateComplete(int brBnumber) {
		return bsDAO.updateComplete(sqlSession, brBnumber);
	}

	// 주문완료 테이블 insert
	public int insertBookSale(HashMap<String, Object> map) {
		return bsDAO.insertBookSale(sqlSession, map);
	}

	// 중고서적 TOP 리스트
	public ArrayList<BookReg> selectTopList() {
		return bsDAO.selectTopList(sqlSession);
	}

	// 중고서적 거래 내역 리스트
	public ArrayList<BookSales> selectList(String userId) {
		return bsDAO.selectList(sqlSession, userId);
	}
	
	



	
}
