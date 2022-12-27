package com.zzezze.friendy.backdoors;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backdoor")
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;

    public BackdoorController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/setup-users")
    public String setupUsers() {
        jdbcTemplate.execute("DELETE FROM users");

        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
                        " id, username, password, nickname" +
                        ")" +
                        " VALUES(1, ?, ?, ?)",
                "test", passwordEncoder.encode("test"), "zzezze"
        );

        return "OK";
    }

}
