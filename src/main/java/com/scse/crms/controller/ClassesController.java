package com.scse.crms.controller;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scse.crms.po.User;
import com.scse.crms.service.ClassesService;
import com.scse.crms.vo.SeatTable;

@Controller
public class ClassesController {
	
	@Autowired
	ClassesService classesService;
	
	@RequestMapping("/student/schedule.do")
	@ResponseBody
	public String selectScheduleForStudent(HttpSession session) {
		try {
			return new ObjectMapper().writeValueAsString(classesService.selectScheduleForStudent(((User)session.getAttribute("user")).getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "error";
	}
	@RequestMapping("/teacher/schedule.do")
	@ResponseBody
	public String selectScheduleForTeacher(HttpSession session) {
		try {
			return new ObjectMapper().writeValueAsString(classesService.selectScheduleForTeacher(((User)session.getAttribute("user")).getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "error";
	}

	@RequestMapping("seat.do")
	@ResponseBody
	public String selectSeatTable() {
		return classesService.selectSeat().toString();
	}

	@RequestMapping("/{role}/updateSeat.do")
	@ResponseBody
	public String updateSeatTable(@PathVariable("role") String role, SeatTable seatTable, HttpSession session) {
		if(!role.equals("teacher")) {
			if(classesService.selectCountWithSeat(seatTable)>0)
				return "����λ�ѱ�ռ��";
			seatTable.setSid(((User)session.getAttribute("user")).getId());
		}
		else if(classesService.selectCountWithSeat(seatTable)>0) {
			if(classesService.removeSeat(seatTable)<1)
				return "������λʧ��";
		}
		if(classesService.updateSeat(seatTable)>0)
			return "������λ�ɹ�";
		return "������λʧ��";
	}


	@RequestMapping("/student/removeSeat.do")
	@ResponseBody
	public String removeSeatBySid(SeatTable seatTable, HttpSession session) {
		seatTable.setSid(((User)session.getAttribute("user")).getId());
		if(classesService.removeSeatBySid(seatTable)>0)
			return "�Ƴ���λ�ɹ�";
		return "�Ƴ���λʧ��";
	}
	@RequestMapping("/teacher/removeSeat.do")
	@ResponseBody
	public String removeSeat(SeatTable seatTable, HttpSession session) {
		
		if(classesService.removeSeat(seatTable)>0)
			return "�Ƴ���λ�ɹ�";
		return "�Ƴ���λʧ��";
	}
	
	//���������λ
	@RequestMapping("/teacher/removeAllSeat.do")
	@ResponseBody
	public String removeAllSeat() {
		if(classesService.removeAllSeat()>0)
			return "�����λ�ɹ�";
		return "�����λʧ��";
	}

	@RequestMapping("autoRankSeat.do")
	@ResponseBody
	public String autoRankSeat() {
		return "";
	}
}
