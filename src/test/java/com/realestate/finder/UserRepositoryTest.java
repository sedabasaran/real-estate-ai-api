package com.realestate.finder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import com.realestate.finder.entity.User;
import com.realestate.finder.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User validUser(String email) {
        User user = new User();
        user.setFullName("Test User");
        user.setEmail(email);
        user.setPassword("password123");
        return user;
    }

    @Test
    void whenValidUser_thenSaved() {
        User saved = userRepository.save(validUser("test@test.com"));

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getEmail()).isEqualTo("test@test.com");
    }

    @Test
    void whenFindByEmail_thenReturnsUser() {
        userRepository.save(validUser("find@test.com"));

        Optional<User> found = userRepository.findByEmail("find@test.com");

        assertThat(found).isPresent();
        assertThat(found.get().getFullName()).isEqualTo("Test User");
    }

    @Test
    void whenDuplicateEmail_thenThrowsException() {
        userRepository.save(validUser("duplicate@test.com"));
        userRepository.flush();

        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(validUser("duplicate@test.com"));
            userRepository.flush();
        });
    }

    @Test
    void whenEmailNotFound_thenReturnsEmpty() {
        Optional<User> found = userRepository.findByEmail("notexist@test.com");

        assertThat(found).isEmpty();
    }
}
