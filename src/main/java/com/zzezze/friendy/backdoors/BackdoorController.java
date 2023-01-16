package com.zzezze.friendy.backdoors;


import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.repositories.CommentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/backdoor")
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;

    public BackdoorController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder, CommentRepository commentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.commentRepository = commentRepository;
    }

    //    https://images.unsplash.com/random
    @GetMapping("/setup-users")
    public String setupUsers() {
        jdbcTemplate.execute("DELETE FROM users");

        for (int i = 1001; i < 1020; i++) {
            jdbcTemplate.update("" +
                            "INSERT INTO users(" +
                            " id, username, password, nickname, profile_image, introduction" +
                            ")" +
                            " VALUES(" + i + "1, ?, ?, ?, ?, ?)",
                    "test" + i,
                    passwordEncoder.encode("test" + i),
                    "testUser" + i,
                    "https://picsum.photos/id/" + (i - 900) + "/200/300",
                    i + ""
            );
        }

        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
                        " id, username, password, nickname, profile_image, introduction" +
                        ")" +
                        " VALUES(1, ?, ?, ?, ?, ?)",
                "test",
                passwordEncoder.encode("test"),
                "zzezze",
                "https://friendyimages.s3.ap-northeast-2.amazonaws.com/premium_photo-1669040674572-836c64a3ac70.avif",
                "미니홈피 소개입니다!"
        );

        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
                        " id, username, password, nickname, profile_image, introduction" +
                        ")" +
                        " VALUES(2, ?, ?, ?, ?, ?)",
                "test2",
                passwordEncoder.encode("test2"),
                "suktae",
                "https://friendyimages.s3.ap-northeast-2.amazonaws.com/photo-1671299739805-632b8cbf4977.avif",
                "여긴 석태님 미니홈피"
        );

        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
                        " id, username, password, nickname, profile_image, introduction" +
                        ")" +
                        " VALUES(3, ?, ?, ?, ?, ?)",
                "test3",
                passwordEncoder.encode("test3"),
                "jenna",
                "https://friendyimages.s3.ap-northeast-2.amazonaws.com/%E1%84%8B%E1%85%B3%E1%86%AB%E1%84%92%E1%85%B4%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%91%E1%85%B5%E1%86%AF%E1%84%89%E1%85%A1%E1%84%8C%E1%85%B5%E1%86%AB.avif",
                "여긴 은희님 미니홈피"
        );

        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
                        " id, username, password, nickname, profile_image, introduction" +
                        ")" +
                        " VALUES(4, ?, ?, ?, ?, ?)",
                "test4",
                passwordEncoder.encode("test4"),
                "jiwoo",
                "https://picsum.photos/id/50/200/300",
                "여긴 지우님 미니홈피"
        );

        return "OK";
    }

    @GetMapping("/setup-miniHomepages")
    public String setupMiniHomepages() {
        jdbcTemplate.execute("DELETE FROM photo");
        jdbcTemplate.execute("DELETE FROM guest_book");

        LocalDateTime now = LocalDateTime.now();

        // 사진첩 세팅
        // zzezze
        jdbcTemplate.update("" +
                "INSERT INTO photo(" +
                " id, username, image, explanation" +
                ")" +
                " VALUES(1, 'test', 'https://friendyimages.s3.ap-northeast-2.amazonaws.com/photo1.avif', '이건 사진 설명1'),\n"
                + " (2, 'test', 'https://friendyimages.s3.ap-northeast-2.amazonaws.com/photo2.avif', '이건 사진 설명2')\n"
        );

        commentRepository.save(Comment.fake());

        return "OK";
    }

    @GetMapping("/setup-relationship")
    public String setupRelationship() {
        jdbcTemplate.execute("DELETE FROM relationship");
        // 친구세팅
        // zzezze
        jdbcTemplate.update("" +
                "INSERT INTO relationship(" +
                " id, sender, receiver" +
                ")" +
                " VALUES(1, 'test', 'test2'),\n"
                + " (2, 'test3', 'test')\n"
        );

        return "OK";
    }

    @GetMapping("/setup-invitation")
    public String invitations() {
        jdbcTemplate.execute("DELETE FROM invitation");

        LocalDateTime now = LocalDateTime.now();
        // 친구세팅
        // zzezze
        jdbcTemplate.update("" +
                "INSERT INTO invitation(" +
                " id, sender, receiver, created_at" +
                ")" +
                " VALUES(1, 'test', 'test4', '2023-01-12T16:29:50.854851'),\n"
                + " (2, 'test1001', 'test', '2023-01-12T16:29:50.854851')\n"
        );

        return "OK";
    }
}
