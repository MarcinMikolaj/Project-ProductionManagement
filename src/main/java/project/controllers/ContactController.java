package project.controllers;

import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ModelAttribute;

import project.email.EmailService;
import project.email.Email;

@Controller
public class ContactController {
	
	private final EmailService emailService;
	
	@Autowired
	public ContactController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@GetMapping(path = "/contact")
	public String contact_GET(Model model) {
		model.addAttribute("email", new Email());
		return "contactForm";
	}
	
	@RequestMapping(path = "/contact", method = RequestMethod.POST)
	public String contact_POST(@ModelAttribute Email email,
			@RequestParam MultipartFile multipartFile) throws MessagingException, FileNotFoundException, IOException {
		
		emailService.saveToAccountMailBox(email);
		emailService.send(email, multipartFile);	
		return "contactSuccessful";
	}
	
	//************* contact successful ***************
	
	@GetMapping(path = "/contactSuccessful")
	public String contactSuccessful_GET() {
		return "contactSuccessful";
	}

}
