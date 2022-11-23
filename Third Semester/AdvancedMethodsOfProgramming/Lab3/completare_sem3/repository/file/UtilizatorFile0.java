package socialnetwork.repository.file;

import socialnetwork.domain.User;
import socialnetwork.domain.validators.Validator;

import java.util.List;

public class UtilizatorFile0 extends AbstractFileRepository0<Long, User> {

    public UtilizatorFile0(String fileName) {
        super(fileName);
    }

    @Override
    public User extractEntity(List<String> attributes) {
        return null;
    }

//    @Override
//    public User extractEntity(List<String> attributes) {
//        //TODO: implement method
//        User user = new User(attributes.get(1),attributes.get(2),attributes.get(3),attributes.get(4));
//        user.setId(Long.parseLong(attributes.get(0)));
//
//        return user;
//    }

    @Override
    protected String createEntityAsString(User entity) {
        return entity.getId()+";"+entity.getFirstName()+";"+entity.getLastName();
    }
}
