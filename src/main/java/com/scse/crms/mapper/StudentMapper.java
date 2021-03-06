package com.scse.crms.mapper;

import java.util.List;

import com.scse.crms.po.Student;

public interface StudentMapper {
	//学生查询
	public List<Student> findStudentBy(Student student);
	//学生注册
	public void insertStudent(Student student);
	//删除
	public int deleteStudentById(int id);
	//修改
	public int updateStudent(Student student);
	
}
