package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.CancelInvitationFailed;
import com.zzezze.friendy.exceptions.InvitationNotFound;
import com.zzezze.friendy.exceptions.InvitationNotificationNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.notifications.InvitationNotification;
import com.zzezze.friendy.models.value_objects.Type;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.UserRepository;
import com.zzezze.friendy.repositories.notifications.InvitationNotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CancelInvitationService {
    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final InvitationNotificationRepository invitationNotificationRepository;

    public CancelInvitationService(InvitationRepository invitationRepository, UserRepository userRepository, InvitationNotificationRepository invitationNotificationRepository) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
        this.invitationNotificationRepository = invitationNotificationRepository;
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

        if(invitationNotificationRepository.existsBySenderAndReceiverAndType(sender.getUsername(), receiver.getUsername(), new Type("Invitation"))){
            InvitationNotification invitationNotification =
                    invitationNotificationRepository.findBySenderAndReceiverAndType(sender.getUsername(), receiver.getUsername(), new Type("Invitation"))
                            .orElseThrow(InvitationNotificationNotFound::new);

            invitationNotificationRepository.delete(invitationNotification);
        }

        invitationRepository.deleteById(invitation.getId());

        return "Cancel invitation success";
    }
}
