package com.cadastro.cliente.api.domain.user.converter;

import com.cadastro.cliente.api.domain.user.User;
import io.swagger.model.UserTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserRequestConverter {

    public User convert(UserTemplate request){

        User user = new User();

        user.setLogin(request.getLogin());
        user.setName(request.getName());
        user.setUserIsAdmin(request.getUserIsAdmin());

        return  user;
    }
}
