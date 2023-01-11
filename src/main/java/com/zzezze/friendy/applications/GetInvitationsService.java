package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.InvitationsDto;
import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetInvitationsService {
    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;

    public GetInvitationsService(InvitationRepository invitationRepository, UserRepository userRepository) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
    }

    public InvitationsDto list(Username username) {
        List<UserDto> userReceivedDtos = getUserReceivedDtos(username);

        List<UserDto> userSentDtos = getUserSentDtos(username);

        return new InvitationsDto(userReceivedDtos, userSentDtos);
    }

    public List<UserDto> getUserReceivedDtos(Username username) {
        List<Invitation> invitationsReceived = invitationRepository.findAllByReceiver(username);

        List<UserDto> userReceivedDtos = invitationsReceived.stream()
                .map(invitation -> userRepository.findByUsername(invitation.getSender())
                        .orElseThrow(UserNotFound::new).toDto())
                .toList();
        return userReceivedDtos;
    }

    public List<UserDto> getUserSentDtos(Username username) {
        List<Invitation> invitationsSent = invitationRepository.findAllBySender(username);

        List<UserDto> userSentDtos = invitationsSent.stream()
                .map(invitation -> userRepository.findByUsername(invitation.getReceiver())
                        .orElseThrow(UserNotFound::new).toDto())
                .toList();
        return userSentDtos;
    }
}
