package com.scse.crms.serviceTest;


import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;




import com.scse.crms.lTestUtil.BaseTest;
import com.scse.crms.lTestUtil.ZListAssert;
import com.scse.crms.po.Student;
import com.scse.crms.service.StudentService;

@SuppressWarnings("deprecation")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
public class StudentserviceTest extends BaseTest {
	@Autowired
	private StudentService studentService;   
	private Student student;
	@Before
	public void setup(){
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("hh:mm:ss");
		String time=df.format(date);
		student = new Student();
		student.setId(time);
		student.setAge(20);
		student.setName("aa");
	}
	
	
	 @Test()
	 	public void testFindStudentBy() throws Exception{
		    studentService.insertStudent(student);
		 	List<Student> students = studentService.findStudentBy(student);
//		 	assertTrue(students.size()>0,"гаЪ§Он");
//		 	System.out.println("++++++++++"+students.size()+"++++++++");
		 	assertTrue(students.size()>0);
		 	ZListAssert.assertThat(students).hasitem(student);
	 }
}
