package com.cadastro.cliente.api.domain.user;

import com.cadastro.cliente.api.application.exception.PersistenceException;
import com.cadastro.cliente.api.application.exception.ResourceNotFoundException;
import com.cadastro.cliente.api.infrastructure.persistence.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceTest {

    private UserService service;
    private UserRepository userRepository;

    @Before
    public void setUp() {

        this.service = new UserService();
        this.userRepository = PowerMockito.mock(UserRepository.class);

        Whitebox.setInternalState(service, "userRepository", userRepository);
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenUserNotFound(){

        PowerMockito.when(userRepository.findById("teste")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findUserByLogin("teste"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(String.format("Couldn't find User with login %s", "teste"));
    }

    @Test
    public void shouldReturnUserInstanceWhenUserFound(){

        PowerMockito.when(userRepository.findById("teste")).thenReturn(Optional.of(new User()));
        Assert.assertTrue(service.findUserByLogin("teste") instanceof User);
    }

    @Test
    public void shouldReturnUserInstanceWithCorrectLoginWhenUserFound(){

        User user = new User();
        user.setLogin("teste");

        PowerMockito.when(userRepository.findById("teste")).thenReturn(Optional.of(user));
        User ret = service.findUserByLogin("teste");
        Assert.assertEquals(user.getLogin(), ret.getLogin());
    }

    @Test
    public void shouldReturnUserEmptyUserListIfFindAllReturnedNull(){

        PowerMockito.when(userRepository.findAll()).thenReturn(null);
        List<User> ret = service.getAllUsers();
        Assert.assertEquals(new ArrayList<>(), ret);
    }

    @Test
    public void shouldReturnUserListIfFindAllReturnedSomeUsers(){

        List<User> userList = new ArrayList<>();
        userList.add(new User());

        PowerMockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> ret = service.getAllUsers();
        Assert.assertEquals(1, ret.size());
    }

    @Test
    public void shouldCallRepositorySaveMethodOnUserWithCorrectData(){

        User user = new User();
        user.setLogin("teste");

        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        User savedUser = service.saveUser(user);
        assertThat(savedUser.getLogin()).isEqualTo("teste");
    }

    @Test
    public void shouldThrowPersistenceExceptionWhenProblemPersistingUser(){

        PowerMockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(new RuntimeException());

        User user = new User();
        user.setLogin("teste");

        assertThatThrownBy(() -> service.saveUser(user))
                .isInstanceOf(PersistenceException.class)
                .hasMessage(String.format("An error ocurred while persisting User %s",
                        user.getLogin()));
    }

}