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
public class RefuseInvitationService {
    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;

    public RefuseInvitationService(InvitationRepository invitationRepository, UserRepository userRepository) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
    }

    public String refuse(Username username, Long id) {
        User receiver = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        User sender = userRepository.findById(id)
                .orElseThrow(UserNotFound::new);

        Invitation invitation = invitationRepository
                .findBySenderAndReceiver(sender.getUsername(), receiver.getUsername())
                .orElseThrow(InvitationNotFound::new);

        if(!receiver.getUsername().equals(invitation.getReceiver())){
            throw new CancelInvitationFailed();
        }

        invitationRepository.deleteBySender(sender.getUsername());

        return "Refuse invitation success";
    }
}
