package com.scse.crms.serviceTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.scse.crms.lTestUtil.BaseTest;
import com.scse.crms.po.Classes;
import com.scse.crms.service.ClassesService;
import com.scse.crms.vo.ClassesVo;
import com.scse.crms.vo.SeatTable;
@TransactionConfiguration(defaultRollback = true)  
@Transactional 
public class ClassesServiceTest extends BaseTest {
	@Autowired
	private ClassesService classesService;
	private Classes classes;
	private ClassesVo classesVo;
	private SeatTable seatTable;
	@Before
	public void setup(){
		classes = new Classes();
		classesVo = new ClassesVo();
		classesVo.setClassid("1");
		classesVo.setClassroom("B201");
		classesVo.setCname("课程");
		classesVo.setShooltime("1");
		classesVo.setSid("1");
		classesVo.setTerm(2);
		classesVo.setTid("2");
		classesVo.setTname("师一");
		classesVo.setWeek("1,3,5,7,9,11,13,15");
		classesVo.setYear(2017);
		seatTable = new SeatTable("1", "一", "1", "1", "B201", "A1", "1");
		
	}
	@Test
	public void testSelectScheduleForStudent(){
		ClassesVo classesVo2 = new ClassesVo();
		classesVo2.setSid("1");
		classesVo2.setTerm(2);
		classesVo2.setYear(2017);
		List<ClassesVo> cList = classesService.selectScheduleForStudent(classesVo2);
	  	assertTrue(cList.size()>0);
	}
	@Test
	public void testSelectScheduleForStudentisnull(){
		ClassesVo classesVo2 = new ClassesVo();
		classesVo2.setSid("1xxxx");
		classesVo2.setTerm(2);
		classesVo2.setYear(2017);
		List<ClassesVo> cList = classesService.selectScheduleForStudent(classesVo2);
	  	assertThat(cList).hasSize(0);
	}
	@Test
	public void testSelectScheduleForStudentisnull2(){
		ClassesVo classesVo2 = new ClassesVo();
		classesVo2.setSid("1");
		List<ClassesVo> cList = classesService.selectScheduleForStudent(classesVo2);
	  	assertThat(cList).hasSize(0);
	}
	@Test
	public void testSelectScheduleForTeacher(){
		ClassesVo classesVo2 = new ClassesVo();
		classesVo2.setTid("2");
		classesVo2.setTerm(2);
		classesVo2.setYear(2017);
		List<ClassesVo> cList = classesService.selectScheduleForTeacher(classesVo2);
	  	assertTrue(cList.size()>0);
	}
	@Test
	public void testSelectScheduleForTeacherisnull(){
		ClassesVo classesVo2 = new ClassesVo();
		classesVo2.setTid("1xxxx");
		classesVo2.setTerm(2);
		classesVo2.setYear(2017);
		List<ClassesVo> cList = classesService.selectScheduleForTeacher(classesVo2);
		assertThat(cList).hasSize(0);
	}
	@Test
	public void testUpdateSeat(){
		SeatTable seatTable = new SeatTable();
		seatTable.setSid("1");
		seatTable.setClassid("1");
		seatTable.setSeat("A7");
		int a = classesService.updateSeat(seatTable);
		assertTrue(a==1);
	}
	@Test
	public void testUpdateSeatisno(){
		SeatTable seatTable = new SeatTable();
		seatTable.setSid("1xxx");
		seatTable.setClassid("1");
		seatTable.setSeat("A7");
//		List<ClassesVo> cList = classesService.selectScheduleForTeacher(classesVo2);
		int a = classesService.updateSeat(seatTable);
		assertTrue(a==0);
	}
	@Test
	public void testselectSeat(){
		List<SeatTable> sList = classesService.selectSeat("1xxxxxx");
		assertThat(sList).hasSize(0);
	}
	@Test
	public void testselectSeat2(){
		List<SeatTable> sList = classesService.selectSeat("1");
		assertTrue(sList.size()>0);
	}
	@Test
	public void testremoveAllSeat(){
		classes.setId("1");
		int i = classesService.removeAllSeat(classes.getId());
		assertTrue(i>0);
	}
	
}
