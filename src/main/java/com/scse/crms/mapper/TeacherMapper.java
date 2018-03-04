package com.scse.crms.mapper;

import java.util.List;

import com.scse.crms.po.Teacher;

public interface TeacherMapper {

	//ѧ����ѯ
	public List<Teacher> findTeacherBy(Teacher teacher);
	//ѧ��ע��
	public void insertTeacher(Teacher teacher);
	//ɾ��
	public int deleteTeacherById(String id);
	//�޸�
	public int updateTeacher(Teacher teacher);
	
}
