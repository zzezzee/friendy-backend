package com.zzezze.friendy.applications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzezze.friendy.applications.notifications.SendInvitationNotificationService;
import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.exceptions.InvitationAlreadyExist;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateInvitationService {
    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private SendInvitationNotificationService notificationService;

    public CreateInvitationService(InvitationRepository invitationRepository, UserRepository userRepository, SendInvitationNotificationService notificationService) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public UserDto create(Username sender, Nickname nickname) throws JsonProcessingException {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFound::new);

        Username receiver = user.getUsername();

        invitationRepository.findBySenderAndReceiver(sender, receiver)
                .ifPresent(event -> {
                    throw new InvitationAlreadyExist();
                });

        notificationService.sendNotification(sender, receiver);

        Invitation invitation = Invitation.of(sender, receiver);

        invitationRepository.save(invitation);

        return user.toDto();
    }
}
