package com.scse.crms.serviceTest;


import static org.junit.Assert.*;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.scse.crms.lTestUtil.BaseTest;
import com.scse.crms.po.User;
import com.scse.crms.service.UserService;

public class UserServiceTest extends BaseTest {
	@Autowired
	private UserService userService;
	private User user;
	@Before
	public void setup(){
		user = new User();
		user.setId("1");
		user.setPassword("123456");
	}
	@Test
	public void testLoginofId(){
		User user1 =userService.login(user);
		assertEquals(user1.getId(), "1");
	}
	@Test
	public void testLoginofRole(){
		User user1 =userService.login(user);
		assertEquals(user1.getRole(), "student");
	}
	@Test
	public void testLoginofPasswordMD5(){
		User user1 =userService.login(user);
		assertEquals(user1.getPassword(),"e10adc3949ba59abbe56e057f20f883e");
	}
}
