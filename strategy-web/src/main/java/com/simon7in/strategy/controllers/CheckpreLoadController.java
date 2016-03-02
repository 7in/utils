package com.simon7in.strategy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckpreLoadController {

	@RequestMapping("/checkpreload.htm")
	public String check() {
		return "/checkpreload";
	}

}
