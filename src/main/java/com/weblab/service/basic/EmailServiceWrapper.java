package com.weblab.service.basic;

import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

/**
 * Created by amowel on 13.03.17.
 */
@Service
public class EmailServiceWrapper {

    private EmailService emailService;

    @Autowired
    public EmailServiceWrapper(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async()
    public void sendEmail(String receiverEmail) throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("grom3198@gmail.com", "HOMELESS"))
                .to(Lists.newArrayList(new InternetAddress(receiverEmail, "Dear user")))
                .subject("Confirm registration")
                .body("ajsdikansdkasnd")
                .build();

        emailService.send(email);
    }

}
