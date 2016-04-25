package com.biz.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {

	/**
	 * 인덱스 호출
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "welcome...";
	}

}
