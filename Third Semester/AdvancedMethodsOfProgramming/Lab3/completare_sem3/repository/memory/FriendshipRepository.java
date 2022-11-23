package socialnetwork.repository.memory;

import socialnetwork.domain.Friendship;
import socialnetwork.domain.validators.Validator;
import socialnetwork.utils.DoubleKey;
import socialnetwork.utils.DoubleKeyLong;

import java.io.*;
import java.util.Map;
import java.util.Objects;

public class FriendshipRepository extends InMemoryRepository0<DoubleKeyLong, Friendship> {
    private final String filepath;

    public FriendshipRepository( String filepath) {
        super();
        this.filepath = filepath;
        this.readAll();
    }

    private Friendship getFriendshipFromString(String[] friendshipString)
    {
        Long firstId = Long.parseLong(friendshipString[0]);
        Long secondId = Long.parseLong(friendshipString[1]);

        return new Friendship(firstId,secondId);

    }

    private void readAll() {
        try {
            String splitBy=",";
            BufferedReader bufReader = new BufferedReader(new FileReader(this.filepath));
            String line=bufReader.readLine();

            while(line!=null) {
                String[] friendshipString=line.split(splitBy);
                Friendship friendship= this.getFriendshipFromString(friendshipString);
                this.save(friendship);

                line=bufReader.readLine();
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private void writeAll() {
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(this.filepath));
            PrintWriter printWriter= new PrintWriter(bufWriter);

            for (Map.Entry<DoubleKeyLong, Friendship> entry: this.getAll()) {
                printWriter.write(String.valueOf(entry.getKey().getFirstKey())+","+String.valueOf(entry.getKey().getSecondKey())+"\n");
            }
            printWriter.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    private void appendFile(Friendship friendship) {
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(this.filepath,true));
            PrintWriter printWriter= new PrintWriter(bufWriter);
            printWriter.append(String.valueOf(friendship.getId().getFirstKey())).append(",").append(String.valueOf(friendship.getId().getSecondKey())).append("\n");
            printWriter.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void addFriendship(Long firstId, Long secondId){
        Friendship friendship=new Friendship(firstId, secondId);
        if (this.getEntity(new DoubleKeyLong(firstId,secondId))==null) {
            this.save(friendship);
            this.appendFile(friendship);
        }
        else {
            throw new Error("These users are already friends!:)\n");
        }
    }

    public void removeFriendship(Long firstId, Long secondId) {
        Friendship deletedFriendship=this.delete(new DoubleKeyLong(firstId,secondId));

        this.writeAll();
    }

    public void removeFriendshipsFromUserId(Long id) {
        this.getAll().removeIf(entry-> ((Objects.equals(entry.getKey().getFirstKey(), id) || Objects.equals(entry.getKey().getSecondKey(),id))));

        this.writeAll();
    }

}
