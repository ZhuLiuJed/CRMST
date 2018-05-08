package com.scse.crms.serviceTest;

import static org.junit.Assert.*;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.scse.crms.lTestUtil.BaseTest;
import com.scse.crms.po.Classes;
import com.scse.crms.po.Classroom;
import com.scse.crms.service.ClassroomService;

public class ClassroomServiceTest extends BaseTest {

	@Autowired
	private ClassroomService classroomService;
	
	private Classroom classroom;
	@Before
	public void setup(){
		classroom = new Classroom();
		classroom.setRank("3,5,3");
		classroom.setRoom_number("A101");
		classroom.setRow(10);
	}
	
	@Test
	public void testSelectClassroomRank(){

		Classes classes = new Classes();
		classes.setId("1");
		Classroom classroom1 = classroomService.selectClassroom(classes.getId());
		assertEquals(classroom1.getRank(), classroom.getRank());
	}
	
	
	@Test
	public void testSelectClassroomRow(){

		Classes classes = new Classes();
		classes.setId("1");
		Classroom classroom1 = classroomService.selectClassroom(classes.getId());
		assertEquals(classroom1.getRow(),classroom.getRow());
	}
	@Test
	public void testSelectClassroomisnull(){
		Classroom classroom1 = classroomService.selectClassroom("");
		assertNull(classroom1);
	}
}
