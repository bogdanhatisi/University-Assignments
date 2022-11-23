package socialnetwork.domain;

import socialnetwork.utils.DoubleKey;
import socialnetwork.utils.DoubleKeyLong;

public class Friendship extends Entity<DoubleKeyLong> {
    public Friendship(Long firstId,Long secondId) {
        this.setId(new DoubleKeyLong(firstId,secondId));
    }
}
