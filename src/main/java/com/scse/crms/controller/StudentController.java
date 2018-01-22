package com.scse.crms.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.scse.crms.po.Student;
import com.scse.crms.service.ClassesService;
import com.scse.crms.service.StudentService;
import com.scse.crms.service.impl.ClassesServiceImpl;

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
	@RequestMapping("/my.do")
	public ModelAndView queryStudents(Student s) {
		System.out.println("my.action");
		List<Student> studentList = studentService.findStudentBy(s);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("studentList", studentList);
		
		mv.setViewName("/WEB-INF/jsp/student.jsp");
		
		return mv;
	}
	
	@RequestMapping("schedule.do")
	@ResponseBody
	public String selectSchedule() throws JsonGenerationException, JsonMappingException, IOException {
		
		ClassesService service = new ClassesServiceImpl();
		
		return new ObjectMapper().writeValueAsString(service.selectSchedule().toString());
	}
	
}
