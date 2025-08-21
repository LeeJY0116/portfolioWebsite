// src/main/java/com/example/portfolio/contact/ContactController.java
package com.example.portfolio.contact;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    // ✅ 메일 빈이 없어도 null로 주입되게 처리
    @Autowired(required = false)
    private JavaMailSender mailSender;

    @PostMapping
    public ResponseEntity<?> submit(@Valid @RequestBody ContactForm form) {
        // 봇 차단용 허니팟: 값이 오면 그냥 성공처럼 응답하고 버림
        if (StringUtils.hasText(form.getWebsite())) {
            return ResponseEntity.ok(Map.of("status", "ok"));
        }

        if (mailSender != null) {
            try {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo("you@yourdomain.com");
                msg.setSubject("[Portfolio] " + form.getName() + " 문의");
                msg.setText("From: " + form.getEmail() + "\n\n" + form.getMessage());
                mailSender.send(msg);
            } catch (Exception e) {
                e.printStackTrace(); // 메일 실패는 로그만 남김
            }
        } else {
            // 메일 설정 없을 때는 서버 로그로만 확인
            System.out.println("[CONTACT] " + form.getName() + " <" + form.getEmail() + ">\n" + form.getMessage());
        }

        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}
