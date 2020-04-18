package com.cadastro.cliente.api.domain.user.converter;

import com.cadastro.cliente.api.domain.user.User;
import io.swagger.model.UserTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserResponseConverter {

    public List<UserTemplate> convertUserList(List<User> users){

        return users.stream().map(u -> convert(u)).collect(Collectors.toList());
    }

    public UserTemplate convert(User user){

        UserTemplate userTemplate = new UserTemplate();
        userTemplate.setLogin(user.getLogin());
        userTemplate.setName(user.getName());
        userTemplate.setUserIsAdmin(user.isUserIsAdmin());

        return userTemplate;
    }
}
