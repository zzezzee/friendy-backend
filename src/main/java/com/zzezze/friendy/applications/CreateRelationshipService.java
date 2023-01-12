package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.Relationship;
import com.zzezze.friendy.repositories.RelationshipRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateRelationshipService {
    private final RelationshipRepository relationshipRepository;

    public CreateRelationshipService(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    public void create(Invitation invitation) {
        Relationship relationship = invitation.accept();

        relationshipRepository.save(relationship);
    }
}
