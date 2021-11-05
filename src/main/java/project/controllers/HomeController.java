package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import project.Test;

@Controller
public class HomeController {
	
	private Test test;
	
	@Autowired
	public HomeController(Test test) {
		this.test = test;
	}
	
	@GetMapping(path = "/")
	public String home() {		
		return "home";
	}
	
	@GetMapping(path = "/test")
	public String test() {
		test.dataBaseTest();
		return "home";
	}
	
	

}
