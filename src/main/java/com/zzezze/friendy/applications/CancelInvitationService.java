package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.CancelInvitationFailed;
import com.zzezze.friendy.exceptions.InvitationNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CancelInvitationService {
    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;

    public CancelInvitationService(InvitationRepository invitationRepository, UserRepository userRepository) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
    }

    public String cancel(Username username, Long id) {
        User sender = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        User receiver = userRepository.findById(id)
                .orElseThrow(UserNotFound::new);

        Invitation invitation = invitationRepository
                .findBySenderAndReceiver(sender.getUsername(), receiver.getUsername())
                .orElseThrow(InvitationNotFound::new);

        if(!sender.getUsername().equals(invitation.getSender())){
            throw new CancelInvitationFailed();
        }

        invitationRepository.deleteByReceiver(receiver.getUsername());

        return "Cancel invitation success";
    }
}
