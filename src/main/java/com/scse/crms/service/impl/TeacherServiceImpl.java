package com.scse.crms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scse.crms.mapper.TeacherMapper;
import com.scse.crms.po.Teacher;
import com.scse.crms.service.TeacherService;

/**
 * 
 * <p>Description:ѧ������ </p>
 * @date 2017��10��14�� ����5:38:58
 * @author HometownWu
 *
 */
@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherMapper teacherMapper;
	
	public List<Teacher> findTeacherBy(Teacher teacher) {
		// 
		return teacherMapper.findTeacherBy(teacher);
	}

	public void insertTeacher(Teacher teacher) {
		// 
		teacherMapper.insertTeacher(teacher);
	}

}
