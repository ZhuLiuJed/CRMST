package com.scse.crms.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value="/student.do", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String queryStudents(Student s) throws JsonGenerationException, JsonMappingException, IOException {
//		List<Student> studentList = studentService.findStudentBy(s);
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("studentList", studentList);
//		
//		mv.setViewName("/WEB-INF/jsp/student.jsp");
		
		return new ObjectMapper().writeValueAsString(studentService.findStudentBy(s));
	}
	
}
