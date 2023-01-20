package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.Relationship;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.RelationshipRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DiscriminateRelationshipService {
    private final RelationshipRepository relationshipRepository;
    private InvitationRepository invitationRepository;

    public DiscriminateRelationshipService(RelationshipRepository relationshipRepository, InvitationRepository invitationRepository) {
        this.relationshipRepository = relationshipRepository;
        this.invitationRepository = invitationRepository;
    }

    public String discriminate(User visitor, User owner) {
        Username visitorUsername = visitor.getUsername();
        Username ownerUsername = owner.getUsername();

        if (visitorUsername.equals(ownerUsername)) {
            return "me";
        }

        List<Long> visitorRelationshipId
                = relationshipRepository.findAllBySenderOrReceiver(visitorUsername, visitorUsername)
                .stream()
                .map(Relationship::getId)
                .toList();

        List<Long> ownerRelationshipId
                = relationshipRepository.findAllBySenderOrReceiver(ownerUsername, ownerUsername)
                .stream()
                .map(Relationship::getId)
                .toList();

        int size = visitorRelationshipId
                .stream()
                .filter(ownerRelationshipId::contains)
                .toList()
                .size();

        if(size == 1){
            return "friend";
        }

        List<Invitation> invitations1 = invitationRepository.findAllBySender(ownerUsername);
        List<Invitation> invitations2 = invitationRepository.findAllByReceiver(ownerUsername);

        int size1 = invitations1.stream()
                .filter(invitation -> invitation.getReceiver().equals(visitorUsername))
                .toList()
                .size();

        int size2 = invitations2.stream()
                .filter(invitation -> invitation.getSender().equals(visitorUsername))
                .toList()
                .size();

        if(size1+size2 == 1){
            return "applier";
        }

        return "stranger";
    }
}
