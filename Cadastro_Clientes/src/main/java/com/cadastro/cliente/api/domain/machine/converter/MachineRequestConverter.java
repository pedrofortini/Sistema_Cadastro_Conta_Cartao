package com.cadastro.cliente.api.domain.machine.converter;

import com.cadastro.cliente.api.application.MessageConstants;
import com.cadastro.cliente.api.application.exception.UnprocessableEnityException;
import com.cadastro.cliente.api.domain.user.User;
import com.cadastro.cliente.api.domain.machine.Machine;
import com.cadastro.cliente.api.domain.user.UserService;
import io.swagger.model.MachineRequest;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class MachineRequestConverter {

    @Inject
    private UserService userService;

    public Machine convert(MachineRequest request){

        Machine machine = new Machine();

        if(request.getId() != null){
            machine.setId(request.getId());
        }

        machine.setName(request.getName());
        machine.setMachineIsUp(request.getMachineIsUp());

        User adminUser = userService.findUserByLogin(request.getAdminUser());
        if(!adminUser.isUserIsAdmin()) {

            throw new UnprocessableEnityException(String.format(MessageConstants.MESSAGE_USER_IS_NOT_ADMIN,
                    request.getAdminUser()));
        }
        machine.setAdminUser(adminUser);
        machine.getUserAcess().add(adminUser);
        machine.setIpAddress(request.getIpAddress());

        return  machine;
    }
}
