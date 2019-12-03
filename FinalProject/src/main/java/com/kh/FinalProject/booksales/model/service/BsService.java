package com.kh.FinalProject.booksales.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.FinalProject.booksales.model.vo.BookReg;
import com.kh.FinalProject.booksales.model.vo.BookSales;


public interface BsService {

	ArrayList<BookReg> selectList();

	BookReg selectBs(int brBnumber);

	int inserBs(BookReg br);

	int updateBs(BookReg br);

	int bsDelete(int brBnumber);

	int updateComplete(int brBnumber);

	int insertBookSale(HashMap<String, Object> map);

	ArrayList<BookReg> selectTopList();

	ArrayList<BookSales> selectList(String userId);

	



}
