package com.gamsungjin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gamsungjin.user.bo.UserBO;
import com.gamsungjin.user.model.User;

// 단위 테스트 (Unit test)
// JUnit
@SpringBootTest
class GamsungjinApplicationTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserBO userBO;

	//@Test
	void contextLoads() {
		logger.debug("### Hello world!! ##");
		
		User user = userBO.getUserByLoginId("qwer");
		logger.debug("### user: " + user);
	}
	
	@Test
	void 더하기테스트() {
		logger.debug("### 더하기 테스트 시작");
		int a = 10;
		int b = 2;
		
		assertEquals(12, sum(10, 2));
	}

	int sum(int x, int y) {
		return x + y;
	}
}
