package socialnetwork.service;

import socialnetwork.domain.Friendship;
import socialnetwork.exceptions.RepositoryException;
import socialnetwork.exceptions.ValidationException;
import socialnetwork.repository.database.FriendshipDbRepository;
import socialnetwork.repository.database.UserDbRepository;
import socialnetwork.repository.memory.FriendshipRepository;
import socialnetwork.repository.memory.UserRepository;
import socialnetwork.utils.DoubleKeyLong;
import socialnetwork.validators.UserValidator;

import java.util.Map;

public class Service {
    private final UserDbRepository userRepository;
    private final FriendshipDbRepository friendshipRepository;

    private final UserValidator userValidator;

    public Service(UserDbRepository userRepository, FriendshipDbRepository friendshipRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
        this.userValidator = userValidator;
    }


    public void addUser(String username, String firstName, String lastName) throws ValidationException, RepositoryException {
        this.userValidator.validateForAdd(username,firstName, lastName);
        this.userRepository.addUser(username, firstName, lastName);
    }

    public void removeUser(Long id) throws RepositoryException {
        this.userRepository.removeUser(id);
    }

    public void addFriend(String senderUsername, String receiverUsername) {
        Map.Entry<Long, Long> pairId = this.userRepository.getFriendsId(senderUsername, receiverUsername);
        this.friendshipRepository.addFriendship(pairId.getKey(), pairId.getValue());
        this.friendshipRepository.addFriendship(pairId.getValue(), pairId.getKey());
    }

    public void removeFriend(String senderUsername,String receiverUsername) throws RepositoryException {
        Map.Entry<Long, Long> pairId = this.userRepository.getFriendsId(senderUsername, receiverUsername); this.friendshipRepository.removeFriendship(pairId.getKey(), pairId.getValue());
        this.friendshipRepository.removeFriendship(pairId.getValue(), pairId.getKey());

    }

    public void logUser(String username) throws RepositoryException {
        this.userRepository.logUser(username);
    }
}
