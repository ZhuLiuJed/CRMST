package com.scse.crms.mapper;

import com.scse.crms.po.Student;
import com.scse.crms.po.StudentVo;

public interface StudentMapper {
	//ѧ����ѯ
	public Student findStudentById(StudentVo studentVo);
	//ѧ��ע��
	public void insertStudent(StudentVo studentVo);
	
}
