package com.scse.crms.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scse.crms.po.Teacher;
import com.scse.crms.po.User;
import com.scse.crms.service.TeacherService;

/**
 * 
 * <p>Description:Student的Controller </p>
 * @date 2017年10月14日 下午10:26:00
 * @author HometownWu
 *
 */

@Controller
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;

	//教师查询
	@RequestMapping(value="/teacher.do", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String queryTeacher(HttpSession session, Teacher t) throws JsonGenerationException, JsonMappingException, IOException {
//		List<Student> studentList = studentService.findStudentBy(s);
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("studentList", studentList);
//		
//		mv.setViewName("/WEB-INF/jsp/student.jsp");
		t.setId(((User)session.getAttribute("user")).getId());
		
		return new ObjectMapper().writeValueAsString(teacherService.findTeacherBy(t));
	}
	
}
