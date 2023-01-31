package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserExploreDto;
import com.zzezze.friendy.dtos.UsersExploreDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GetUsersService {
    private final UserRepository userRepository;
    private final GetRelationshipService getRelationshipService;

    public GetUsersService(UserRepository userRepository, GetRelationshipService getRelationshipService) {
        this.userRepository = userRepository;
        this.getRelationshipService = getRelationshipService;
    }

    public UsersExploreDto list(Username username) {
        User me = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        List<User> users = userRepository.findAll();

        List<User> myFriends = findMyFriends(me);

        myFriends.forEach(users::remove);
        users.remove(me);

        Map<User, List<User>> usersFriends = findUsersFriends(users);

        Map<User, List<User>> usersFriendsTogether = filterFriendsTogether(myFriends, usersFriends);

        List<UserExploreDto> userExploreDtos = users.stream()
                .map(user -> {
                    List<User> userFriendsTogether = usersFriendsTogether.get(user);

                    return user.toExploreDto(userFriendsTogether);
                })
                .toList();

        return new UsersExploreDto(userExploreDtos);
    }

    public List<User> findMyFriends(User me) {
        return getRelationshipService.list(me.getNickname())
                .getUsers()
                .stream()
                .map(userDto -> userRepository.findById(userDto.getId())
                        .orElseThrow(UserNotFound::new))
                .toList();
    }

    private Map<User, List<User>> findUsersFriends(List<User> users) {
        Map<User, List<User>> userFriends = new HashMap<>();

        users.forEach(user -> userFriends.put(user, getRelationshipService.list(user.getNickname())
                .getUsers()
                .stream()
                .map(userDto -> userRepository.findById(userDto.getId())
                        .orElseThrow(UserNotFound::new))
                .toList()));

        return userFriends;
    }

    private Map<User, List<User>> filterFriendsTogether(List<User> myFriends, Map<User, List<User>> usersFriends) {
        Map<User, List<User>> usersFriendsTogether = new HashMap<>();

        usersFriends
                .keySet()
                .forEach(user -> {
                    List<User> userFriends = usersFriends.get(user);

                    List<User> userFriendsTogether
                            = myFriends.stream().filter(myFriend -> userFriends.contains(myFriend)
                    ).toList();

                    usersFriendsTogether.put(user, userFriendsTogether);
                });

        return usersFriendsTogether;
    }
}
