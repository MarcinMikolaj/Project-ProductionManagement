package project.email;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
	
	boolean send(Email email, MultipartFile multipartFile) throws MessagingException, IllegalStateException, IOException;
	public void saveToAccountMailBox(Email email);
}
