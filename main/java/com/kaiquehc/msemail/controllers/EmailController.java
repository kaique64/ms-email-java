package com.kaiquehc.msemail.controllers;

import com.kaiquehc.msemail.dtos.EmailDTO;
import com.kaiquehc.msemail.models.EmailModel;
import com.kaiquehc.msemail.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
        EmailModel emailModel = new EmailModel();
        // Transformando DTO (Data transfer object) em Model
        BeanUtils.copyProperties(emailDTO, emailModel);

        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}
