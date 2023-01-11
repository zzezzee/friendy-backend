package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserRelationShipDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Relationship;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.RelationshipRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetUserService {
    private final UserRepository userRepository;
    private final RelationshipRepository relationshipRepository;

    public GetUserService(UserRepository userRepository, RelationshipRepository relationshipRepository) {
        this.userRepository = userRepository;
        this.relationshipRepository = relationshipRepository;
    }

    public UserRelationShipDto detail(Username username, Nickname nickname) {
        User visitor = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        User owner = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFound::new);

        String relation = discriminate(visitor, owner);

        return new UserRelationShipDto(
                visitor.getNickname().getValue(),
                relation
        );
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

        return "stranger";
    }
}
