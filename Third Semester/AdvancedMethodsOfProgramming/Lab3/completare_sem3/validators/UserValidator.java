package socialnetwork.validators;

import socialnetwork.domain.User;
import socialnetwork.exceptions.ValidationException;

public class UserValidator implements Validator<User> {


    public void validateForAdd(String username, String firstName, String lastName) throws ValidationException {
        String message="";
        if(username.isEmpty())
        {
            message = message + "Invalid username!\n";
        }
        if(firstName.isEmpty())
        {
            message = message + "Invalid first name!\n";
        }
        if(lastName.isEmpty())
        {
            message = message + "Invalid last name!\n";
        }

        if (message.length() > 0)
        {
            throw new ValidationException(message);
        }
    }

    @Override
    public void validate(User user) throws ValidationException
    {
        String message="";
        if(user.getUsername().isEmpty())
        {
            message = message + "Invalid username!\n";
        }
        if(user.getFirstName().isEmpty())
        {
            message = message + "Invalid first name!\n";
        }
        if(user.getLastName().isEmpty())
        {
            message = message + "Invalid last name!\n";
        }

        if (message.length() > 0)
        {
            throw new ValidationException(message);
        }


    }
}
