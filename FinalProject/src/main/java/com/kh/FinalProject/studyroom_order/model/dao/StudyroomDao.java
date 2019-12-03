package com.kh.FinalProject.studyroom_order.model.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.FinalProject.studyroom_order.model.vo.Studyroom;
import com.kh.FinalProject.studyroom_order.model.vo.StudyroomOrder;

@Repository("srDao")
public class StudyroomDao {

	public int getRoomCount(SqlSession sqlSession) {
		return sqlSession.selectOne("studyroomMapper.getRoomCount");
	}

	public ArrayList<Studyroom> selectRoomList(SqlSession sqlSession) {
		return (ArrayList)sqlSession.selectList("studyroomMapper.selectRoomList");
	}

	public int reservationStudyroom(SqlSession sqlSession, StudyroomOrder sr) {
		return sqlSession.insert("studyroomMapper.reservationStudyroom",sr);
	}

	public ArrayList<StudyroomOrder> selectOrderList(SqlSession sqlSession, String id) {
		return (ArrayList)sqlSession.selectList("studyroomMapper.selectOrderList",id);
	}

	public ArrayList<String> selectName(SqlSession sqlSession, Map<String, Object> map) {
		return (ArrayList)sqlSession.selectList("studyroomMapper.selectName", map);
	}

	public ArrayList<Studyroom> getSrInfo(SqlSession sqlSession, String so_floor) {
		return (ArrayList)sqlSession.selectList("studyroomMapper.getSrInfo",so_floor);
	}

	public ArrayList<String> checkId(SqlSession sqlSession, Map<String, Object> map) {
		return (ArrayList)sqlSession.selectList("studyroomMapper.checkId", map);
	}

	public ArrayList<StudyroomOrder> reservationInfo(SqlSession sqlSession, String dateInfo) {
		return (ArrayList)sqlSession.selectList("studyroomMapper.reservationInfo", dateInfo);
	}

	public ArrayList<StudyroomOrder> checkTime(SqlSession sqlSession, StudyroomOrder sro) {
		return (ArrayList)sqlSession.selectList("studyroomMapper.checkTime",sro);
	}

//	public ArrayList<Studyroom> spoidSrInfo(SqlSession sqlSession, Map<String, Object> map) {
//		return (ArrayList)sqlSession.selectList("studyroomMapper.spoidSrInfo",map);
//	}
	public ArrayList<Studyroom> spoidSrInfo(SqlSession sqlSession, Studyroom sr) {
		return (ArrayList)sqlSession.selectList("studyroomMapper.spoidSrInfo",sr);
	}

	public ArrayList<StudyroomOrder> orderList(SqlSession sqlSession, StudyroomOrder sr) {
		return (ArrayList)sqlSession.selectList("studyroomMapper.orderList",sr);
	}

	public int deleteOrder(SqlSession sqlSession, String so_no) {
		return sqlSession.update("studyroomMapper.deleteOrder",so_no);
	}

	public int cancelOrder(SqlSession sqlSession, String bo_number) {
		System.out.println("dao : " + bo_number);
		return sqlSession.update("studyroomMapper.cancelOrder",bo_number);
	}

	

}
