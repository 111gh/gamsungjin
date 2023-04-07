package com.gamsungjin.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping("/test1")
	public Map<String, Object> helloWorld() {
		Map<String, Object> result = new HashMap<>();
		result.put("say", "hi");
		result.put("123", 1);
		
		return result;
	}
}
