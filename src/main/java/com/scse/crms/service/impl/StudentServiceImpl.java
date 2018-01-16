package com.scse.crms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.scse.crms.mapper.StudentMapper;
import com.scse.crms.po.Student;
import com.scse.crms.po.StudentVo;
import com.scse.crms.service.StudentService;

/**
 * 
 * <p>Description:ѧ������ </p>
 * @date 2017��10��14�� ����5:38:58
 * @author HometownWu
 *
 */
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	public Student findStudentById(Student student) {
		// 
		return studentMapper.findStudentById(student);
	}
	public List<Student> findStudentBy(Student student) {
		// 
		return studentMapper.findStudentBy(student);
	}

	public void insertStudent(Student student) {
		// 
		studentMapper.insertStudent(student);
	}

}
