package com.scse.crms.service;

import java.util.List;

import com.scse.crms.po.Student;

public interface StudentService {
	
	//ѧ����ѯ
	public List<Student> findStudentBy(Student student);
	//ѧ��ע��
	public void insertStudent(Student student);
}
