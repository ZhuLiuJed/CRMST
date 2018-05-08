package com.scse.crms.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.scse.crms.lTestUtil.BaseTest;
@WebAppConfiguration
public class ClassroomControllerTest extends BaseTest {
	@Autowired
	private WebApplicationContext wac;
    private MockHttpSession session;  
	private MockMvc mockMvc;
	public ClassroomControllerTest() {
		// TODO Auto-generated constructor stub
	}
	@Before
	public void setup(){
		 this.mockMvc = webAppContextSetup(this.wac).build();
		 this.session = new MockHttpSession();
	}
	@Test
	public void testselectSchedule()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/class.do"))  
                .param("classid","1")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("rank\":\"3,5,3\"");
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
