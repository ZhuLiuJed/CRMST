package com.scse.crms.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import testvo.noticetestvo;

import com.alibaba.fastjson.JSONObject;
import com.scse.crms.lTestUtil.BaseTest;
import com.scse.crms.po.Notice;
@WebAppConfiguration
public class NoticeControllerTest extends BaseTest {

	@Autowired
	private WebApplicationContext wac;
    private MockHttpSession session;  
	private MockMvc mockMvc;
	private Notice notice;
	private noticetestvo noticetestvo;
	public NoticeControllerTest() {
		// TODO Auto-generated constructor stub
	}
	@Before
	public void setup(){
		 this.mockMvc = webAppContextSetup(this.wac).build();
		 this.session = new MockHttpSession();
		 notice = new Notice();
		 noticetestvo = new noticetestvo();
	}
	@Test
	public void testSelectNoticeForClasses()throws Exception{
		notice.setContent("content");
		notice.setDate("2018-03-01");
		notice.setEffective("2018-03-31");
		notice.setId("1");
		notice.setTid("2");
		notice.setTarget("1");
		MvcResult result = this.mockMvc
				.perform((post("/student/notice.do"))
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
	}
	@Test
	public void testSelectNoticeForTeacher()throws Exception{
		noticetestvo.setContent("aaaaaaa");
		noticetestvo.setDate("2018-03-01");
		noticetestvo.setEffective("2018-03-31");
		noticetestvo.setId("1");
		noticetestvo.setTid("2");
		noticetestvo.setTarget("1");
		noticetestvo.setTname("Ê¦Ò»");
		MvcResult result = this.mockMvc
				.perform((post("/teacher/notice.do"))
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonnoticeString = objectMapper.writeValueAsString(noticetestvo);
		assertThat(jsonString).contains(jsonnoticeString);
	}
	@Test
	public void testSelectNoticeForTeacher1()throws Exception{
		notice.setContent("content");
		notice.setDate("2018-03-01");
		notice.setEffective("2018-03-31");
		notice.setId("1");
		notice.setTid("2");
		notice.setTarget("1");
		MvcResult result = this.mockMvc
				.perform((post("/teacher/notice.do"))
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		assertEquals(result.getResponse().getContentAsString(), "Insufficient authority");
	}
	@Test
	public void testAddNotice()throws Exception{
		notice.setContent("aaaaaaaa");
		notice.setTid("2");
		notice.setTarget("1");
		notice.setTitle("¹þ¹þ¹þ");
		MvcResult result = this.mockMvc
				.perform((post("/teacher/addNotice.do"))
				.param("tid", notice.getTid())
				.param("title", notice.getTitle())
				.param("content", notice.getContent())
				.param("target", notice.getTarget())
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		 assertEquals(result.getResponse().getContentAsString(), "1");
	}
	@Test
	public void testDeleteNoticeById()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/deleteNoticeById.do"))
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		 assertEquals(result.getResponse().getContentAsString(), "0");
	}
	@Test
	@Rollback
	public void testDeleteNoticeById2()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/deleteNoticeById.do"))
				.param("id","1")
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
