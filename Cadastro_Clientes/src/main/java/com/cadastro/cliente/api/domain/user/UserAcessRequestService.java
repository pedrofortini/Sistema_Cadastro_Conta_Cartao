package com.cadastro.cliente.api.domain.user;

import com.cadastro.cliente.api.domain.machine.MachineService;
import com.cadastro.cliente.api.application.MessageConstants;
import com.cadastro.cliente.api.application.exception.ResourceAlreadyExistsException;
import com.cadastro.cliente.api.domain.machine.Machine;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserAcessRequestService {

    @Inject
    private MachineService machineService;

    @Inject
    private UserService userService;

    public void requestUserAcess(Long machineId, String userLogin) {

        Machine machine = machineService.findMachineById(machineId);
        User user = userService.findUserByLogin(userLogin);

        if (machine.getUserAcess().contains(user)){

            throw new ResourceAlreadyExistsException(
                    String.format(
                    MessageConstants.MESSAGE_ERROR_USER_ALREADY_HAVE_ACESS,
                    user.getLogin(), machine.getName()));
        }
        machine.getUserAcess().add(user);
        machineService.saveMachine(machine);
    }
}
