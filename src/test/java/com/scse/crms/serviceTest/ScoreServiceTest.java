package com.scse.crms.serviceTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.scse.crms.lTestUtil.BaseTest;
import com.scse.crms.po.Classes;
import com.scse.crms.service.ScoreService;
import com.scse.crms.vo.ParaForScore;
import com.scse.crms.vo.ScoreVo;

public class ScoreServiceTest extends BaseTest {
	@Autowired
	private ScoreService classroomService;
	private ScoreVo scoreVo;
	private ParaForScore paraForScore;
	private Classes classes;
	@Before
	public void setup(){
		paraForScore = new ParaForScore();
		classes = new Classes();
//		paraForScore.setTid("2");
//		paraForScore.setCid("2");
//		paraForScore.setClassid("1");
//		paraForScore.setSid("1");
	}
	@Test
	public void testSelectScoreWithClassidtoSid(){
		paraForScore.setSid("1440121103");
		paraForScore.setClassid("crms001");
		List<ScoreVo> sList =  classroomService.selectScoreWithClassid(paraForScore);
		if (sList.size()>0) {
			for (ScoreVo scoreVo : sList) {
				System.out.println(scoreVo.getSid());
				if (scoreVo.getSid().equals(paraForScore.getSid())) {
					assertTrue(true);
				}
			}
		}
		fail("sListno");
	}
	@Test
	public void testSelectScoreWithClassidtotid(){
		paraForScore.setTid("2");
		List<ScoreVo> sList =  classroomService.selectScoreWithClassid(paraForScore);
		assertTrue(sList.size()>0);
		
	}
	@Test
	public void testSelectScoreWithClassidtoClassid(){
		paraForScore.setClassid("1");
		List<ScoreVo> sList =  classroomService.selectScoreWithClassid(paraForScore);
		assertTrue(sList.size()>0);
	}
	
	@Test
	public void testSelectScoreWithClassidtocid(){
		paraForScore.setCid("2");
		List<ScoreVo> sList =  classroomService.selectScoreWithClassid(paraForScore);
		assertTrue(sList.size()>0);
	}
	@Test
	public void testSelectScoreWithClassidisnull(){
		List<ScoreVo> sList =  classroomService.selectScoreWithClassid(paraForScore);
		assertTrue(sList.size()>0);
	}


}
