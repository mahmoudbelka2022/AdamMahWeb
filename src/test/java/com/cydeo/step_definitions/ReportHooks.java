package com.cydeo.step_definitions;

import io.cucumber.java.AfterAll;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static com.cydeo.utilities.EmailReportUti.sendEmailReport;

public class ReportHooks {


    @AfterAll
    public static void sendReportEmail() throws Exception {
        sendEmailReport("kamalbelkati@gmail.com", "target/cucumber-html-reports");
    }



}
