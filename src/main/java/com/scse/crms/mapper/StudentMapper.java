package com.scse.crms.mapper;

import java.util.List;

import com.scse.crms.po.Student;
import com.scse.crms.po.StudentVo;

public interface StudentMapper {
	//ѧ����ѯ
	public Student findStudentById(Student student);
	public List<Student> findStudentBy(Student student);
	//ѧ��ע��
	public void insertStudent(Student student);
	//ɾ��
	public int deleteStudentById(int id);
	//�޸�
	public int updateStudent(Student student);
	
}
