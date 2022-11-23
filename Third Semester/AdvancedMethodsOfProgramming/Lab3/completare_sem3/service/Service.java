package socialnetwork.service;

import socialnetwork.domain.Friendship;
import socialnetwork.repository.Repository0;
import socialnetwork.repository.memory.FriendshipRepository;
import socialnetwork.repository.memory.InMemoryRepository0;
import socialnetwork.repository.memory.UserRepository;
import socialnetwork.utils.DoubleKeyLong;

import java.util.Map;

public class Service {
    private UserRepository userRepository;
    private FriendshipRepository friendshipRepository;

    public Service(UserRepository userRepository, FriendshipRepository friendshipRepository) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
        this.joinUsersFriends();
    }

    private void joinUsersFriends() {
        for (Map.Entry<DoubleKeyLong, Friendship> entry : this.friendshipRepository.getAll()) {
            this.userRepository.addFriendbyId(entry.getValue().getId().getFirstKey(), entry.getValue().getId().getSecondKey());
        }
    }

    public void addUser(String username, String firstName, String lastName) {
        this.userRepository.addUser(username, firstName, lastName);
    }

    public void removeUser(Long id) {
        this.userRepository.removeUser(id);
        this.friendshipRepository.removeFriendshipsFromUserId(id);
    }

    public void addFriend(String senderUsername, String receiverUsername) {
        Map.Entry<Long, Long> pairId = this.userRepository.getFriendsId(senderUsername, receiverUsername);
        this.friendshipRepository.addFriendship(pairId.getKey(), pairId.getValue());
        this.friendshipRepository.addFriendship(pairId.getValue(), pairId.getKey());
        this.userRepository.addFriendInList(senderUsername,receiverUsername);

    }

    public void removeFriend(String senderUsername,String receiverUsername)
    {
        Map.Entry<Long,Long> pairId = this.userRepository.getRemovedFriendsIdPair(senderUsername,receiverUsername);
        this.friendshipRepository.removeFriendship(pairId.getKey(), pairId.getValue());
        this.friendshipRepository.removeFriendship(pairId.getValue(), pairId.getKey());

    }

    public void logUser(String username)
    {
        this.userRepository.logUser(username);
    }
}
