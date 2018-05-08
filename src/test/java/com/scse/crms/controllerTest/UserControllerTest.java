package com.scse.crms.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.springframework.web.context.WebApplicationContext;

import com.scse.crms.lTestUtil.BaseTest;
import com.scse.crms.po.User;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
public class UserControllerTest extends BaseTest{
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private MockHttpSession session;
	@Before
	public void setup(){
		 this.mockMvc = webAppContextSetup(this.wac).build();
		 session = new MockHttpSession();
	}
	 @Test
	    public void testloginSuccess() throws Exception {
	        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/login.do");
	        mockHttpServletRequestBuilder.param("id", "1");
	        mockHttpServletRequestBuilder.param("password", "123456");
	        MvcResult mResult = mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk())
	                .andDo(print()).andReturn();
	        User user = new User();
	         	user.setId("1");
	         	user.setRole("student");
	         	user.setPassword("");
	         ObjectMapper objectMapper = new ObjectMapper();
	        String jsonuserString = objectMapper.writeValueAsString(user);
	        assertThat(mResult.getResponse().getContentAsString()).contains(jsonuserString);
	    }
	 @Test
	    public void testloginFail() throws Exception {
	        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/login.do");
	        mockHttpServletRequestBuilder.param("id", "1");
	        mockHttpServletRequestBuilder.param("password", "1234567");
	        MvcResult mResult =mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk())
	                .andDo(print())
	                .andReturn();
	        assertEquals(mResult.getResponse().getContentAsString(), "fail");
	    }
	 @Test
	    public void testloginsession() throws Exception {
	        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/login.do");
	        mockHttpServletRequestBuilder.param("id", "1");
	        mockHttpServletRequestBuilder.param("password", "123456");
	        MvcResult mResult =mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk())
	                .andDo(print())
	                .andReturn();
	        session= (MockHttpSession) mResult.getRequest().getSession();
	        User user = new User();
	        	 user = (User) session.getAttribute("user");
	 	    assertEquals(user.getPassword(), "");
	        assertEquals(user.getRole(), "student");
	    }
	 @Test
	    public void testloginsessionT() throws Exception {
	        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/login.do");
	        mockHttpServletRequestBuilder.param("id", "2");
	        mockHttpServletRequestBuilder.param("password", "123456");
	        MvcResult mResult =mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk())
	                .andDo(print())
	                .andReturn();
	        session= (MockHttpSession) mResult.getRequest().getSession();
	        User user = new User();
	        	 user = (User) session.getAttribute("user");
	 	    assertEquals(user.getPassword(), "");
	        assertEquals(user.getRole(), "teacher");
	    }
	 @Test
	    public void testloginsessionNull() throws Exception {
	        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/login.do");
	        mockHttpServletRequestBuilder.param("id", "2");
	        mockHttpServletRequestBuilder.param("password", "1234567");
	        MvcResult mResult =mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk())
	                .andDo(print())
	                .andReturn();
	        session= (MockHttpSession) mResult.getRequest().getSession();
	        User user = new User();
	        user = (User) session.getAttribute("user");
	        assertNull(user);
	    }

}
