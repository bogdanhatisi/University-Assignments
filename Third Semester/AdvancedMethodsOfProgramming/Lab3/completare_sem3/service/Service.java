package socialnetwork.service;

import socialnetwork.domain.Friendship;
import socialnetwork.exceptions.RepositoryException;
import socialnetwork.exceptions.ValidationException;
import socialnetwork.repository.memory.FriendshipRepository;
import socialnetwork.repository.memory.UserRepository;
import socialnetwork.utils.DoubleKeyLong;
import socialnetwork.validators.UserValidator;

import java.util.Map;

public class Service {
    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    private final UserValidator userValidator;

    public Service(UserRepository userRepository, FriendshipRepository friendshipRepository, UserValidator userValidator) throws RepositoryException {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
        this.userValidator = userValidator;
        this.joinUsersFriends();
    }

    private void joinUsersFriends() throws RepositoryException {
        for (Map.Entry<DoubleKeyLong, Friendship> entry : this.friendshipRepository.getAll()) {
            this.userRepository.addFriendbyId(entry.getValue().getId().getFirstKey(), entry.getValue().getId().getSecondKey());
        }
    }

    public void addUser(String username, String firstName, String lastName) throws ValidationException, RepositoryException {
        this.userValidator.validateForAdd(username,firstName, lastName);
        this.userRepository.addUser(username, firstName, lastName);
    }

    public void removeUser(Long id) throws RepositoryException {
        this.userRepository.removeUser(id);
        this.friendshipRepository.removeFriendshipsFromUserId(id);
    }

    public void addFriend(String senderUsername, String receiverUsername) {
        Map.Entry<Long, Long> pairId = this.userRepository.getFriendsId(senderUsername, receiverUsername);
        this.friendshipRepository.addFriendship(pairId.getKey(), pairId.getValue());
        this.friendshipRepository.addFriendship(pairId.getValue(), pairId.getKey());
        this.userRepository.addFriendInList(senderUsername,receiverUsername);

    }

    public void removeFriend(String senderUsername,String receiverUsername) throws RepositoryException {
        Map.Entry<Long,Long> pairId = this.userRepository.getRemovedFriendsIdPair(senderUsername,receiverUsername);
        this.friendshipRepository.removeFriendship(pairId.getKey(), pairId.getValue());
        this.friendshipRepository.removeFriendship(pairId.getValue(), pairId.getKey());

    }

    public void logUser(String username) throws RepositoryException {
        this.userRepository.logUser(username);
    }
}
