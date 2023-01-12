package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.AcceptInvitationFailed;
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
public class DeleteReceivedInvitationService {
    private InvitationRepository invitationRepository;
    private UserRepository userRepository;

    public DeleteReceivedInvitationService(InvitationRepository invitationRepository, UserRepository userRepository) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
    }

    public Invitation delete(Username username, Long id) {
        User receiver = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        User sender = userRepository.findById(id)
                .orElseThrow(UserNotFound::new);

        Invitation invitation =
                invitationRepository.findBySenderAndReceiver(sender.getUsername(), receiver.getUsername())
                        .orElseThrow();

        if(!receiver.getUsername().equals(invitation.getReceiver())){
            throw new AcceptInvitationFailed();
        }

        invitationRepository.deleteById(invitation.getId());

        return invitation;
    }
}
