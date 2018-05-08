package com.scse.crms.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
import com.scse.crms.po.Notice;
@WebAppConfiguration
public class TeacherControllerTest extends BaseTest {
	@Autowired
	private WebApplicationContext wac;
    private MockHttpSession session;  
	private MockMvc mockMvc;
	public TeacherControllerTest() {
		// TODO Auto-generated constructor stub
	}
	@Before
	public void setup(){
		 this.mockMvc = webAppContextSetup(this.wac).build();
		 this.session = new MockHttpSession();
	}
	@Test
	public void testqueryTeacher() throws Exception{
		MvcResult result = this.mockMvc
				.perform((get("/teacher.do"))
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
