package com.cydeo.step_definitions;


import com.cydeo.utilities.EmailUtils;
import com.cydeo.utilities.PdfUtils;
import io.cucumber.java.After;

import javax.mail.MessagingException;
import java.util.Date;

public class EmailHooks {

    @After
    public void afterScenario() throws MessagingException, Exception {
        String recipient = "kamalbelkati@gmail.com";
        String subject = "Cucumber Report - " + new Date().toString();
        String body = "Please find attached the Cucumber report for the latest test run.";

        String htmlFilePath = "target/cucumber-reports.html";
//        String pdfFilePath = "target/cucumber/cucumber-reports";
//        PdfUtils.convertHtmlToPdf(htmlFilePath, pdfFilePath);

        EmailUtils.sendEmailWithAttachment(recipient, subject, body, htmlFilePath);
    }
}
