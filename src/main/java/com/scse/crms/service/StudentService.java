package com.scse.crms.service;

import com.scse.crms.po.Student;
import com.scse.crms.po.StudentVo;

public interface StudentService {
	
	//ѧ����ѯ
	public Student findStudentById(StudentVo studentVo);
	//ѧ��ע��
	public void insertStudent(StudentVo studentVo);
}
