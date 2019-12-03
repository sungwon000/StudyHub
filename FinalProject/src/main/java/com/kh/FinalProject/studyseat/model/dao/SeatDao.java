package com.kh.FinalProject.studyseat.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.FinalProject.studyseat.model.vo.Seat;
import com.kh.FinalProject.studyseat.model.vo.SeatHistory;
import com.kh.FinalProject.user.model.vo.User;

@Repository("sDao")
public class SeatDao {

	public ArrayList<Integer> countSeat(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("seatMapper.countSeat");
	}



	public ArrayList<Seat> selectSeatList(SqlSessionTemplate sqlSession, String floor) {
		
		return (ArrayList)sqlSession.selectList("seatMapper.selectFloorSeat" , floor);
	}


	public int updateResv(SqlSessionTemplate sqlSession, Seat seat) {

		return sqlSession.update("seatMapper.updateResv" , seat);
	}



	public int checkDup(SqlSessionTemplate sqlSession, String id) {
		
		return sqlSession.selectOne("seatMapper.checkDup" , id);
	}



	public ArrayList<SeatHistory> selectHistoryList(SqlSessionTemplate sqlSession, String id) {

		return (ArrayList)sqlSession.selectList("seatMapper.selectHistoryList" , id);
	}



	public int insertHistory(SqlSessionTemplate sqlSession, Seat seat) {
		
		return sqlSession.insert("seatMapper.insertHistory" , seat);
	}



	public int cancelResv(SqlSessionTemplate sqlSession, Seat seat) {
		
		return sqlSession.update("seatMapper.cancelResv" , seat);
	}



	public int resetSeat(SqlSessionTemplate sqlSession, int cancelId) {
		
		return sqlSession.update("seatMapper.resetSeat" , cancelId);
	}



	public SeatHistory selectsId(SqlSessionTemplate sqlSession, String id) {
		
		return sqlSession.selectOne("seatMapper.selectsId" , id);
	}



	public int checkCode(SqlSessionTemplate sqlSession, Seat seat) {
		
		return sqlSession.update("seatMapper.checkCode" , seat);
	}



	public int updateHistory(SqlSessionTemplate sqlSession, Seat seat) {
		
		return sqlSession.update("seatMapper.updateHistory" , seat);
	}



	public int checkUsing(SqlSessionTemplate sqlSession, String id) {
		
		return sqlSession.selectOne("seatMapper.checkUsing" , id);
	}



	public int updateSeat(SqlSessionTemplate sqlSession, Seat seat) {
	
		return sqlSession.update("seatMapper.updateSeat" , seat);
	}



	public int updateOutHistory(SqlSessionTemplate sqlSession, Seat seat) {
		
		return sqlSession.update("seatMapper.updateOutHistory" , seat);
	}



	public int updateUser(SqlSessionTemplate sqlSession, User user) {
		
		return sqlSession.update("seatMapper.updateUser" , user);
	}




}
