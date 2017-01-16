package com.hrhx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/map")
public class ChinaMapController {
	
	@RequestMapping(value="china")
	public String china(){
		return "/map/china";
	}

}
