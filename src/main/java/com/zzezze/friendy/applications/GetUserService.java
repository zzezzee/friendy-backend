package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.dtos.UserRelationShipDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.RelationShip;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetUserService {
    private final UserRepository userRepository;
    private final RelationShip relationShip;

    public GetUserService(UserRepository userRepository, RelationShip relationShip) {
        this.userRepository = userRepository;
        this.relationShip = relationShip;
    }

    public UserRelationShipDto detail(Username username, Nickname nickname) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        String relation = relationShip.discriminate(user.getNickname(), nickname);

        return new UserRelationShipDto(
                user.getNickname().getValue(),
                relation
        );
    }
}
