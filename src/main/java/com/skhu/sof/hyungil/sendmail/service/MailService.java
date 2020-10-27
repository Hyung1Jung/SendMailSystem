package com.skhu.sof.hyungil.sendmail.service;

import com.skhu.sof.hyungil.sendmail.dto.MailDto;
import com.skhu.sof.hyungil.sendmail.util.MailHandler;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "Junghyungile@gmail.com";

    public void mailSend(MailDto mailDto) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(mailDto.getAddress());
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            mailHandler.setSubject(mailDto.getTitle());
            String htmlContent = "<p>" + mailDto.getMessage() +"<p> <img src='cid:sample-img'>";
            mailHandler.setText(htmlContent, true);
            mailHandler.setAttach("SendFileTest.txt", "static/SendFileTest.txt");
            mailHandler.setInline("image1", "static/image1.jpg");

            mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}