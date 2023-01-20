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

@Service
@Transactional
public class GetUserService {
    private final UserRepository userRepository;
    private final DiscriminateRelationshipService discriminateRelationshipService;

    public GetUserService(UserRepository userRepository, DiscriminateRelationshipService discriminateRelationshipService) {
        this.userRepository = userRepository;
        this.discriminateRelationshipService = discriminateRelationshipService;
    }

    public UserRelationShipDto detail(Username username, Nickname nickname) {
        User visitor = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        User owner = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFound::new);

        String relation = discriminateRelationshipService.discriminate(visitor, owner);

        return new UserRelationShipDto(
                visitor.getNickname().getValue(),
                relation
        );
    }
}
