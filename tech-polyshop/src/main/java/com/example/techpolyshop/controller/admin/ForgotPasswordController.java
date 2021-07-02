package com.example.techpolyshop.controller.admin;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.example.techpolyshop.Impl.CustomerServiceImpl;
import com.example.techpolyshop.config.Utility;
import com.example.techpolyshop.domain.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javassist.NotFoundException;
import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("site/account")
public class ForgotPasswordController {
    @Autowired
    CustomerServiceImpl customerService;

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("forgot_password")
    public String showForgotPasswordForm() {
        return "/site/account/forgotPassword";
    }

    @PostMapping("forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model)
            throws ClassNotFoundException, NotFoundException {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            customerService.updateResetToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/site/account/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }

        return "/site/account/forgotPassword";
    }

    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("thuongbthpd03828@fpt.edu.vn", "Cake Bakery");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link 
                + "\">Change my password</a> </p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        Customer customer = customerService.getByResetToken(token);
        model.addAttribute("token", token);

        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "/site/account/resetPassword";
    }

    @PostMapping("reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Customer customer = customerService.getByResetToken(token);
        model.addAttribute("title", "Reset your password");

        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            customerService.updatePassword(customer, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "message";
    }
}
