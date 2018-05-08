package com.scse.crms.controllerTest;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import org.junit.runners.Parameterized;  
import org.junit.runners.Parameterized.Parameters; 

import com.scse.crms.lTestUtil.BaseTest;


/**
 *项目名称CRMS
 *类名称ClassesControllerTest
 *类描述：
 *编辑人：邓文杰
 *编辑时间：2018年3月11日上午10:10:38
 *修改备注：
 *
 */
@WebAppConfiguration
public class ClassesControllerTest extends BaseTest  {
	@Autowired
	private WebApplicationContext wac;
    private MockHttpSession session;  
	private MockMvc mockMvc;
//	private String term;
//	private String year;
//	private String link;
//	@Parameters  
//	public static Collection prepareData(){  
//	        Object [][] bject = {{"aa","cc","/student/schedule.do"},{"aa","2017","/teacher/schedule.do"},{"1","aa","/courseList.do"}};  
//	        return Arrays.asList(bject);  
//	    }  
//	public ClassesControllerTest(String term,String year,String link){  
//	        this.term = term;  
//	        this.year = year;  
//	        this.link = link;  
//	    }  
	@Before
	public void setup(){
		 this.mockMvc = webAppContextSetup(this.wac).build();
		 this.session = new MockHttpSession();
	}
	
//	 @DataProvider(name = "data1")
//	 public Object[][] createdata() {
//	 return new Object[][] {{"aa", "cc"},
//			 				{"aa", "2017"},
//			 				{"1", "aa"}
//	 						};
//	 	}
////	 @DataProvider(name = "link")
////	 public Object[][] createlinkdata() {
////	 return new Object[][] {{"/student/schedule.do"},
////			 				{"/teacher/schedule.do"},
////			 				{"/courseList.do"}
////	 						};
////	 	}
	@Test
	public void testselectSchedule()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/schedule.do"))  
                .param("sid","1")
                .param("term", "2")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("classid\":\"1\"");
	}
	@Test
	public void testselectSchedulegetsession()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/schedule.do"))  
                .param("term", "2")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("classid\":\"1\"");
	}
	@Test()
	public void testselectSchedulenull()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/schedule.do"))  
                .param("term", "1")
                .param("year", "cc")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
	}
	@Test()
	public void testselectSchedulenull1()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/schedule.do"))  
                .param("term", "aa")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
	}
	@Test()
	public void testselectSchedulenull2()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/schedule.do"))  
                .param("term", "aa")
                .param("year", "cc")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
	}
	@Test
	public void testselectSchedulebutteaher()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/schedule.do"))  
                .param("term", "2xx")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
	}
	
	@Test
	public void testselectSchedulenotauth()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/schedule.do"))  
                .param("term", "2")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("1","1234567"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
//		assertThat(jsonString).contains("[]");
		assertEquals(jsonString, "Insufficient authority");
	}
	@Test
	public void testselectScheduleTeacher()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/schedule.do"))  
                .param("sid","1")
                .param("term", "2")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("classid\":\"1\"");
	}
	@Test
	public void testselectScheduleTeachergetsession()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/schedule.do"))  
                .param("term", "2")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("Insufficient authority");
	}
	
	@Test()
	public void testselectScheduleTeachernull()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/schedule.do"))  
                .param("term", "1")
                .param("year", "cc")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
	}
	@Test()
	public void testselectScheduleteachernull1()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/schedule.do"))  
                .param("term", "aa")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
	}
	@Test()
	public void testselectScheduleteachernull2()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/schedule.do"))  
                .param("term", "aa")
                .param("year", "cc")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
	}
	@Test
	public void testselectScheduleTeacherbutstudent()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/schedule.do"))  
                .param("term", "2xx")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("4","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
	}
	
	@Test
	public void testselectScheduleTeachernotauth()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/schedule.do"))  
                .param("term", "2")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("5","1234567"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertEquals(jsonString, "Insufficient authority");
	}
	@Test
	public void testcourseList()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/courseList.do"))  
                .param("term", "2")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("cname");
		assertThat(jsonString).contains("clist");
	}
	@Test
	public void testcourseListInsuffcientAuth()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/courseList.do"))  
                .param("term", "2")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("2","1234567"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("Insufficient authority");
	}
	@Test()
	public void testcourseListnull()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/courseList.do"))  
                .param("term", "aa")
                .param("year", "2017")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
	}
	@Test()
	public void testcourseListnull2()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/courseList.do"))  
                .param("term", "aa")
                .param("year", "cc")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
	}@Test()
	public void testcourseListnull3()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/courseList.do"))  
                .param("term", "1")
                .param("year", "cc")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
	}
	@Test()
	public void testselectSeatTable()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/seat.do"))  
                .param("classid", "1")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("sid\":\"1\"");
	}
	@Test()
	public void testselectSeatTableisnull()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/seat.do"))  
                .param("classid", "1xxxxx")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("[]");
	}
	@Test()
	@Rollback
	public void testupdateSeatTable()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/updateSeat.do"))  
                .param("seat", "A8")
                .param("sid", "1")
                .param("classid", "1")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("设置座位成功");
	}
	@Test()
	@Rollback
	public void testupdateSeatTable2()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/updateSeat.do"))  
                .param("seat", "A4")
                .param("sid", "3")
                .param("classid", "1")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("修改座位成功");
	}
	@Test()
	@Rollback
	public void testupdateSeatTable3()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/updateSeat.do"))  
                .param("seat", "A4")
                .param("sid", "4")
                .param("classid", "1")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("修改座位成功");
	}
	@Test()
	@Rollback
	public void testupdateSeatTablestudent()throws Exception{
					this.mockMvc
					.perform((post("/teacher/removeSeat.do"))  
			        .param("classid", "1")
			        .param("seat", "A40")
			        .session((MockHttpSession)getLoginSession("2","123456"))
			        .contentType(MediaType.APPLICATION_JSON)
			        ).andExpect(status().isOk())
			        .andDo(print())
			        .andReturn();
		MvcResult result = this.mockMvc
				.perform((post("/student/updateSeat.do"))  
                .param("seat", "A40")
                .param("classid", "1")
                .session((MockHttpSession)getLoginSession("4","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		result = this.mockMvc
				.perform((post("/student/updateSeat.do"))  
                .param("seat", "A40")
                .param("classid", "1")
                .session((MockHttpSession)getLoginSession("3","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("该座位已被占据");
	}
	@Test()
	@Rollback
	public void testupdateSeatTablestudent5()throws Exception{
					this.mockMvc
					.perform((post("/teacher/removeSeat.do"))  
			        .param("classid", "1")
			        .param("seat", "A40")
			        .session((MockHttpSession)getLoginSession("2","123456"))
			        .contentType(MediaType.APPLICATION_JSON)
			        ).andExpect(status().isOk())
			        .andDo(print())
			        .andReturn();
		MvcResult result = this.mockMvc
				.perform((post("/student/updateSeat.do"))  
                .param("seat", "A40")
                .param("classid", "1")
                .session((MockHttpSession)getLoginSession("4","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("设置座位成功");
		}
	@Test()
	@Rollback
	public void testupdateSeatTablestudent2()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/updateSeat.do"))  
                .param("seat", "A1")
                .param("sid", "1")
                .param("classid", "1xx")
                .session((MockHttpSession)getLoginSession("3","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("修改座位失败");
	}
	@Test()
	public void testGetcourseList() throws Exception{
			MvcResult result = this.mockMvc
								.perform((post("/student/courseList.do")) 
								.param("year", "2017")
								.param("term", "2")
								.session((MockHttpSession)getLoginSession("1","123456"))
								.contentType(MediaType.APPLICATION_JSON)
								).andExpect(status().isOk())
								.andDo(print())
								.andReturn();
	}
	@Test()
	public void testGetcourseList2() throws Exception{
			MvcResult result = this.mockMvc
								.perform((post("/student/courseList.do")) 
								.param("year", "2018")
								.param("term", "2")
								.session((MockHttpSession)getLoginSession("1","123456"))
								.contentType(MediaType.APPLICATION_JSON)
								).andExpect(status().isOk())
								.andDo(print())
								.andReturn();
	}
	@Test()
	public void testselectSeatTablestudentsession()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/seat.do"))  
                .param("classid", "1xxxxx")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("[]");
	}
	@Test()
	public void testremoveSeatBySid()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/removeSeat.do"))  
                .param("classid", "1")
                .session((MockHttpSession)getLoginSession("7","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		//不能回滚处理~~~
				String jsonString = result.getResponse().getContentAsString();
				assertThat(jsonString).contains("移除座位成功");
				 this.mockMvc
					.perform((post("/student/updateSeat.do"))  
		         .param("classid", "1")
		         .param("seat", "A5")
		         .session((MockHttpSession)getLoginSession("7","123456"))
		         .contentType(MediaType.APPLICATION_JSON)
		         ).andExpect(status().isOk());
	}
	@Test()
	public void testremoveAllSeat()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/removeAllSeat.do"))  
                .param("classid", "9")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("清空座位成功");
		//不能回滚处理~~~
		 this.mockMvc
			.perform((post("/student/updateSeat.do"))  
         .param("classid", "9")
         .param("seat", "A2")
         .session((MockHttpSession)getLoginSession("8","123456"))
         .contentType(MediaType.APPLICATION_JSON)
         ).andExpect(status().isOk());
	}
	@Test()
	public void testremoveAllSeatnoclassid()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/removeAllSeat.do"))  
                .param("classid", "9xxxx")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("清空座位失败");
	}
	
	@Test()
	@Rollback
	public void testremoveSeatnoclassid2()throws Exception{
		//不能回滚处理~~~
		 this.mockMvc
			.perform((post("/student/updateSeat.do"))  
       .param("classid", "9")
       .param("seat", "A1")
       .session((MockHttpSession)getLoginSession("9","123456"))
       .contentType(MediaType.APPLICATION_JSON)
       ).andExpect(status().isOk());
		MvcResult result = this.mockMvc
				.perform((post("/teacher/removeSeat.do"))  
                .param("classid", "9")
                .param("seat", "A1")
                .session((MockHttpSession)getLoginSession("2","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("移除座位成功");
		//不能回滚处理~~~
		 this.mockMvc
			.perform((post("/student/updateSeat.do"))  
        .param("classid", "9")
        .param("seat", "A1")
        .session((MockHttpSession)getLoginSession("9","123456"))
        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
	}
	@Test()
	@Rollback
	public void testremoveSeatBySid2()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/removeSeat.do"))  
                .param("classid", "1XXXX")
                .contentType(MediaType.APPLICATION_JSON)
                .session((MockHttpSession)getLoginSession("7","123456"))
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		//不能回滚处理~~~
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("移除座位失败");
	}
	@Test()
	@Rollback
	public void testremoveSeatBySidnoid()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/removeSeat.do"))  
                .param("classid", "1")
                .param("seat", "A5")
                .session((MockHttpSession)getLoginSession("1","123456"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
	}
	
//	不加入session
	@Test()
	public void testNoSession3()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/student/schedule.do"))  
                .param("term", "2")
                .param("year", "2017")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("Insufficient authority");
	}
	@Test()
	public void testNoSession2()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/teacher/schedule.do"))  
                .param("term", "2")
                .param("year", "2017")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("Insufficient authority");
	}
	@Test()
	public void testNoSession()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/courseList.do"))  
                .param("term", "2")
                .param("year", "2017")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();
		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("Insufficient authority");
	}
	@Test()
	public void testselectSeatTableNoSession()throws Exception{
		MvcResult result = this.mockMvc
				.perform((post("/seat.do"))  
                .param("classid", "1")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

		String jsonString = result.getResponse().getContentAsString();
		assertThat(jsonString).contains("Insufficient authority");
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
