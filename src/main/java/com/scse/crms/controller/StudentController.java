package com.scse.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scse.crms.po.Student;
import com.scse.crms.service.StudentService;

/**
 * 
 * <p>Description:Student��Controller </p>
 * @date 2017��10��14�� ����10:26:00
 * @author HometownWu
 *
 */

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	//ѧ����ѯ
	@RequestMapping("/my.action")
	public ModelAndView queryStudents() {
		System.out.println("my.action");
		Student student = studentService.findStudentById(null);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", student);
		
		mv.setViewName("/WEB-INF/jsp/student.jsp");
		
		return mv;
	}
	
	//
	
}
