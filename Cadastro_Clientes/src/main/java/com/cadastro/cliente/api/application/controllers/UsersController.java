package com.cadastro.cliente.api.application.controllers;

import com.cadastro.cliente.api.domain.user.User;
import com.cadastro.cliente.api.domain.user.UserAcessRequestService;
import com.cadastro.cliente.api.domain.user.UserService;
import com.cadastro.cliente.api.domain.user.converter.UserRequestConverter;
import com.cadastro.cliente.api.domain.user.converter.UserResponseConverter;
import io.swagger.api.UsersApi;
import io.swagger.model.UserAcessRequest;
import io.swagger.model.UserTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UsersController implements UsersApi {

	@Inject
	private UserService userService;

	@Inject
	private UserAcessRequestService userAcessRequestService;

	@Inject
	private UserRequestConverter userRequestConverter;

	@Inject
	private UserResponseConverter userResponseConverter;

	@Override
	public ResponseEntity<UserTemplate> getUserByLogin(@PathVariable("login") String login) {

		User user = userService.findUserByLogin(login);
		UserTemplate userTemplate = userResponseConverter.convert(user);

		return ResponseEntity.ok(userTemplate);
	}

	@Override
	public ResponseEntity<List<UserTemplate>> getUsers() {

		List<User> users = userService.getAllUsers();
		List<UserTemplate> userTemplates = userResponseConverter.convertUserList(users);

		return ResponseEntity.ok(userTemplates);
	}

	@Override
	public ResponseEntity<Void> saveUser(@Valid @RequestBody UserTemplate user) {

		User domainUser = userRequestConverter.convert(user);
		userService.saveUser(domainUser);

		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> saveUserAcessRequest(@Valid @RequestBody UserAcessRequest user) {

		userAcessRequestService.requestUserAcess(user.getMachineId(), user.getUserLogin());
		return ResponseEntity.ok().build();
	}
}
