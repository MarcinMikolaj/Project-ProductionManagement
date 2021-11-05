package project.email;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

import java.io.File;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {
	
	private final JavaMailSender javaMailSender;
	private EmailRepository emailRepository;
	
	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Autowired
	public void setEmailRepsoitory(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}

	@Override
	public boolean send(Email email, MultipartFile multipartFile) throws MessagingException, IllegalStateException, IOException {
		
		try {	
			if(email.getAdres() != null && email.getAdres().length() > 0) {
				
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
				mimeMessageHelper.setTo(email.getAdres());
				mimeMessageHelper.setSubject(email.getTitle());
				mimeMessageHelper.setText(email.getText());
				
				File convFile = new File(System.getProperty("java.io.tmpdir")+ multipartFile.getOriginalFilename());
				
				if(multipartFile.isEmpty() == false) {
					multipartFile.transferTo(convFile);
					mimeMessageHelper.addAttachment(convFile.getName(),convFile);
				}
						
				javaMailSender.send(mimeMessage);
				convFile.delete(); // Remove filer after use
				return true;
			}
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void saveToAccountMailBox(Email email) {
		emailRepository.save(email);
	}

}
