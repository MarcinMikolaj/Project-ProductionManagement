package project.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import project.Test;

@Controller
public class SignInController {
	
	@GetMapping(path = "/signIn")
	public String signIn_GET() {
		return "SignIn";
	}

}
