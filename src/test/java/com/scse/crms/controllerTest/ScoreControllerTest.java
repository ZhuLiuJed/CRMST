package com.scse.crms.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;





import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import testvo.StudentScorevo;

import com.scse.crms.lTestUtil.BaseTest;
import com.scse.crms.po.Notice;
import com.scse.crms.vo.ParaForScore;
@WebAppConfiguration
public class ScoreControllerTest extends BaseTest {
	@Autowired
	private WebApplicationContext wac;
    private MockHttpSession session;  
	private MockMvc mockMvc;
	private ParaForScore paraForScore;
	private StudentScorevo scorevo;
	private ObjectMapper objectMapper;
	public ScoreControllerTest() {
		// TODO Auto-generated constructor stub
	}
	@Before
	public void setup(){
		 this.mockMvc = webAppContextSetup(this.wac).build();
		 this.session = new MockHttpSession();
		 paraForScore = new ParaForScore();
		 scorevo = new StudentScorevo();
		 paraForScore.setCid("1");
		 paraForScore.setClassid("1");
		 paraForScore.setSid("1");
		 paraForScore.setUsual_performance("10,10,10");
		 scorevo.setSid("1");
		 scorevo.setCname("¿Î³Ì");
		 scorevo.setSname("Ò»");
		 scorevo.setHomework(10.0f);
		 scorevo.setAbsenceScore(10.0f);
		 scorevo.setPerformance(10.0f);
		 scorevo.setUsual_performance("10,10,10");
		 scorevo.setProportion("40");
		 objectMapper = new ObjectMapper();
	}
	@Test
	public void testSelectScore() throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/score.do"))
						.param("sid", "")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonnoticeString = objectMapper.writeValueAsString(scorevo);
		assertThat(result.getResponse().getContentAsString()).contains(jsonnoticeString);
	}
	@Test
	public void testSelectScoretoteacher() throws Exception{
		scorevo.setSname("Èý");
		MvcResult result = this.mockMvc
				.perform((post("/teacher/score.do"))
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonnoticeString = objectMapper.writeValueAsString(scorevo);
		assertThat(result.getResponse().getContentAsString()).contains(jsonnoticeString);
	}
	@Test
	public void testSelectScorenoauth() throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/score.do"))
						.param("sid", "")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonnoticeString = objectMapper.writeValueAsString(scorevo);
		assertThat(result.getResponse().getContentAsString()).contains("Insufficient authority");
	}
	@Test
	public void testSelectScoretoteachernoauth() throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/score.do"))
						.param("sid", "")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonnoticeString = objectMapper.writeValueAsString(scorevo);
		assertThat(result.getResponse().getContentAsString()).contains("Insufficient authority");
	}
	
	@Test
	public void testUpdateScoreBySidAndClassid() throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/updateScore.do"))
						.param("usual_performance", paraForScore.getUsual_performance())
						.param("sid", paraForScore.getSid())
						.param("classid", paraForScore.getClassid())
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
	}
	private	HttpSession getLoginSession(String a,String b) throws Exception{  
		 MvcResult result = this.mockMvc  
               .perform((post("/login.do")
              		 .param("id", a)
              		 .param("password", b)
              		 ))  
               .andExpect(status().isOk())  
               .andReturn();  
		 return result.getRequest().getSession(); 
	    }  
}
