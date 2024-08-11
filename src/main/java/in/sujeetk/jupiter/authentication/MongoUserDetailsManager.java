package in.sujeetk.jupiter.authentication;

import in.sujeetk.jupiter.model.User;
import in.sujeetk.jupiter.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Optional;

public class MongoUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    UserRepository userRepository;

    public MongoUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {
        User mongoUser = User.builder()
            .userId(user.getUsername())
            .password(user.getPassword())
            .isUserLocked(false)
            .isMfaEnabled(false).build();
        userRepository.save(mongoUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> mongoUser = userRepository.findById(username);
        if (mongoUser.isPresent()) {
            System.out.println("User found");
            return mongoUser.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
