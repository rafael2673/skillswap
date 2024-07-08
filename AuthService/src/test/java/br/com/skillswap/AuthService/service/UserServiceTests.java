package br.com.skillswap.AuthService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.skillswap.AuthService.dto.UserDTO;
import br.com.skillswap.AuthService.model.User;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;

public class UserServiceTests {

}
