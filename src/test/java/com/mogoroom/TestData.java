package com.mogoroom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mogoroom.hadoop.RootApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RootApplication.class)
@WebAppConfiguration
public class TestData {
	 
	@Test
	public  void testToken() {
		System.err.println("test");
	}
	 
}
