package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.exceptions.InvitationAlreadyExist;
import com.zzezze.friendy.exceptions.InvitationNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CreateInvitationService {
    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;

    public CreateInvitationService(InvitationRepository invitationRepository, UserRepository userRepository) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
    }

    public UserDto create(Username sender, Nickname nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFound::new);

        Username receiver = user.getUsername();

        invitationRepository.findBySenderAndReceiver(sender, receiver)
                .ifPresent(event -> {
                    throw new InvitationAlreadyExist();
                });

        Invitation invitation = Invitation.of(sender, receiver);

        invitationRepository.save(invitation);

        return user.toDto();
    }
}
