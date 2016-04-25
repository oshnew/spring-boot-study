package com.biz.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

	/**
	 * Hello 컨트롤러
	 * 
	 * @param name 파라미터값 존재시 string을 더해서 리턴
	 * @return
	 */
	@RequestMapping("/hello")
	public String hello(@RequestParam(value = "name", required = false) String name) {

		if (name != null) {
			return "Hello... " + name;
		}

		return "Hello...";
	}

}
