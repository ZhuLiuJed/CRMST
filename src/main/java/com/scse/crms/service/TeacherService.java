package com.scse.crms.service;

import java.util.List;

import com.scse.crms.po.Teacher;

public interface TeacherService {
	
	//ѧ����ѯ
	public List<Teacher> findTeacherBy(Teacher teacher);
	//ѧ��ע��
	public void insertTeacher(Teacher teacher);
}
