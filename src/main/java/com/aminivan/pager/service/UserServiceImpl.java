package com.aminivan.pager.service;

import com.aminivan.pager.models.User;
import com.aminivan.pager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(int id) {
        var user = userRepository.findById(id);
        if(user.isEmpty()) throw new RuntimeException("User With Id" + id +"Not Found.");
        return user.get();
    }

    @Override
    public User save(User user) {
        if(user.getEmail() == null || user.getPassword() == null ||
        user.getGender() == null || user.getProfile() == null || user.getUsername() == null)
            throw new RuntimeException("User Data Not Valid");

        return userRepository.save(user);
    }

    @Override
    public User update(User userUpdated) {
        var result = userRepository.findById(userUpdated.getUserid());

        if(result.isEmpty()) throw new RuntimeException("User Data Not Found");
        var user = result.get();
        user.setEmail(userUpdated.getEmail());
        user.setPassword(userUpdated.getPassword());
        user.setProfile(userUpdated.getProfile());
        user.setUsername(userUpdated.getUsername());
        user.setGender(userUpdated.getGender());

        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        var result = userRepository.findById(id);

        if(result.isEmpty()) throw new RuntimeException("No Data Found");

        userRepository.delete(result.get());
    }
}
