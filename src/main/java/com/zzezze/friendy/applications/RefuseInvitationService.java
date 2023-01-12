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
    private final DeleteReceivedInvitationService deleteReceivedInvitationService;

    public RefuseInvitationService(DeleteReceivedInvitationService deleteReceivedInvitationService) {
        this.deleteReceivedInvitationService = deleteReceivedInvitationService;
    }

    public String refuse(Username username, Long id) {
        deleteReceivedInvitationService.delete(username, id);

        return "Refuse invitation success";
    }
}
