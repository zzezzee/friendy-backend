package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.dtos.UsersDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Relationship;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.RelationshipRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetRelationshipService {
    private final UserRepository userRepository;
    private final RelationshipRepository relationshipRepository;

    public GetRelationshipService(UserRepository userRepository, RelationshipRepository relationshipRepository) {
        this.userRepository = userRepository;
        this.relationshipRepository = relationshipRepository;
    }

    public UsersDto list(Nickname nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFound::new);

        List<Relationship> relationshipList1
                = relationshipRepository.findAllBySender(user.getUsername());

        List<Relationship> relationshipList2
                = relationshipRepository.findAllByReceiver(user.getUsername());

        List<Username> usernameList = new ArrayList<>();

        relationshipList1
                .forEach(relationship -> usernameList.add(relationship.getReceiver()));
        relationshipList2
                .forEach(relationship -> usernameList.add(relationship.getSender()));

        List<UserDto> userDtos = usernameList.stream()
                .map(username -> userRepository.findByUsername(username)
                        .orElseThrow(UserNotFound::new).toDto())
                .toList();

        return new UsersDto(userDtos);
    }
}
