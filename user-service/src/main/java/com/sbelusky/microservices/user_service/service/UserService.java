package com.sbelusky.microservices.user_service.service;

import com.sbelusky.microservices.user_service.dto.UserRequest;
import com.sbelusky.microservices.user_service.dto.UserResponse;
import com.sbelusky.microservices.user_service.exception.UserNotFoundException;
import com.sbelusky.microservices.user_service.model.User;
import com.sbelusky.microservices.user_service.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates new User and saves it to the database
     *
     * @param userRequest contains information about the User to be created
     * @return UserResponse containing information about the created User
     */
    public UserResponse createUser(@Valid UserRequest userRequest) {

        // create new User
        User user = new User();
        user.setEmail(userRequest.email());
        user.setUserName(userRequest.userName());
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setRole(userRequest.role());
        user.setCreatedAt(LocalDateTime.now());

        // save User to the database
        userRepository.save(user);

        // return UserResponse containing information about created User
        UserResponse userResponse = getUserResponse(user);

        return userResponse;
    }

    /**
     * Retrieves User from the database
     *
     * @param email email of the User to be retrieved
     * @return UserResponse containing information about the retrieved User
     */
    public UserResponse getUserByEmail(String email) throws UserNotFoundException {
        // check if User exists with given email
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        // return UserResponse containing information about the retrieved User
        UserResponse userResponse = getUserResponse(user);

        return userResponse;
    }

    /**
     * Retrieves User from the database
     *
     * @param id id of the User to be retrieved
     * @return UserResponse containing information about the retrieved User
     */
    public UserResponse getUserById(Long id) throws UserNotFoundException {
        // check if User exists with given id
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // return UserResponse containing information about the retrieved User
        return getUserResponse(user);
    }

    /**
     * Converts User to UserResponse
     *
     * @param user User to be converted
     * @return UserResponse containing information about the User
     */
    private static UserResponse getUserResponse(User user) {
        // create new UserResponse
        UserResponse userResponse = new UserResponse(
                user.getUserName(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        return userResponse;
    }
}
