package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.LoginResultDto;
import com.zzezze.friendy.exceptions.LoginFailed;
import com.zzezze.friendy.models.Password;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.repositories.UserRepository;
import com.zzezze.friendy.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public LoginService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResultDto login(Username username, Password password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(LoginFailed::new);

        user.authenticate(password);

        return new LoginResultDto(
                jwtUtil.encode(username.getValue()),
                user.getNickname().getValue()
        );
    }
}
