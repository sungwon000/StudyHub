package com.kh.FinalProject.studyroom_order.model.serivce;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.FinalProject.studyroom_order.model.vo.Studyroom;
import com.kh.FinalProject.studyroom_order.model.vo.StudyroomOrder;
import com.kh.FinalProject.user.model.vo.User;

public interface StudyroomService {

	int getRoomCount();

	ArrayList<Studyroom> selectRoomList();

	int reservationStudyroom(StudyroomOrder sr);

	ArrayList<StudyroomOrder> selectOrderList(String id);

	ArrayList<String> selectName(Map<String, Object> map);

	ArrayList<Studyroom> getSrInfo(String so_floor);

	ArrayList<String> checkId(Map<String, Object> map);

	ArrayList<StudyroomOrder> reservationInfo(String dateInfo);

	ArrayList<StudyroomOrder> checkTime(StudyroomOrder sro);

//	ArrayList<Studyroom> spoidSrInfo(Map<String, Object> map);
	ArrayList<Studyroom> spoidSrInfo(Studyroom sr);

	ArrayList<StudyroomOrder> orderList(StudyroomOrder sr);

	int deleteOrder(String so_no);

	int cancelOrder(String bo_number);


	

}
