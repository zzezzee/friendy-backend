package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.Relationship;
import com.zzezze.friendy.repositories.RelationshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateRelationshipServiceTest {
    RelationshipRepository relationshipRepository;
    CreateRelationshipService createRelationshipService;

    @BeforeEach
    void setup() {
        relationshipRepository = mock(RelationshipRepository.class);
        createRelationshipService = new CreateRelationshipService(relationshipRepository);
    }

    @Test
    void create() {
        createRelationshipService.create(Invitation.fake());

        verify(relationshipRepository).save(any());
    }
}
