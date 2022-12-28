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

        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
                        " id, username, password, nickname" +
                        ")" +
                        " VALUES(2, ?, ?, ?)",
                "test2", passwordEncoder.encode("test2"), "suktae"
        );

        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
                        " id, username, password, nickname" +
                        ")" +
                        " VALUES(3, ?, ?, ?)",
                "test3", passwordEncoder.encode("test3"), "sunghwan"
        );

        return "OK";
    }

    @GetMapping("/setup-miniHomepages")
    public String setupMiniHomepages() {
        jdbcTemplate.execute("DELETE FROM mini_homepage");

        jdbcTemplate.update("" +
                        "INSERT INTO mini_homepage(" +
                        " id, username, nickname, image, introduction" +
                        ")" +
                        " VALUES(1, ?, ?, ?, ?)",
                "test",
                "zzezze",
                "https://friendyimages.s3.ap-northeast-2.amazonaws.com/premium_photo-1669040674572-836c64a3ac70.avif",
                "미니홈피 소개입니다!"
        );

        jdbcTemplate.update("" +
                        "INSERT INTO mini_homepage(" +
                        " id, username, nickname, image, introduction" +
                        ")" +
                        " VALUES(2, ?, ?, ?, ?)",
                "test2",
                "suktae",
                "https://friendyimages.s3.ap-northeast-2.amazonaws.com/photo-1671299739805-632b8cbf4977.avif",
                "여긴 석태님 미니홈피"
        );

        jdbcTemplate.update("" +
                        "INSERT INTO mini_homepage(" +
                        " id, username, nickname, image, introduction" +
                        ")" +
                        " VALUES(3, ?, ?, ?, ?)",
                "test3",
                "sunghwan",
                "https://friendyimages.s3.ap-northeast-2.amazonaws.com/%E1%84%92%E1%85%A5%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5.avif",
                "여긴 성환님 미니홈피"
        );

        return "OK";
    }
}
