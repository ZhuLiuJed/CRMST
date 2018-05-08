package com.scse.crms.serviceTest;


import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;




import com.scse.crms.lTestUtil.BaseTest;
import com.scse.crms.lTestUtil.ZListAssert;
import com.scse.crms.po.Teacher;
import com.scse.crms.service.TeacherService;
@TransactionConfiguration(defaultRollback = true)  
@Transactional  
public class TeacherServiceTest extends BaseTest {
	@Autowired
	private TeacherService tService;
	private Teacher teacher;
	@Before
	public void setup(){
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("hh:mm:ss");
		String time=df.format(date);
		teacher = new Teacher();
		teacher.setId(time);
		teacher.setAge(26);
		teacher.setName("daw");
	}
	@Test()
	@Rollback
	public void test() throws Exception{
	tService.insertTeacher(teacher);
	List<Teacher>teachers = tService.findTeacherBy(teacher);
 	assertThat(teachers).hasSize(1);
// 	重写的方法
  	ZListAssert.assertThat(teachers).hasitem(teacher);
	}
	
}
