package com.cadastro.cliente.api.domain.machine;

import com.cadastro.cliente.api.application.exception.ResourceNotFoundException;
import com.cadastro.cliente.api.application.MessageConstants;
import com.cadastro.cliente.api.application.exception.PersistenceException;
import com.cadastro.cliente.api.infrastructure.persistence.MachineEventLogRepository;
import com.cadastro.cliente.api.infrastructure.persistence.MachineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
	
	@Inject
	MachineRepository machineRepository;

	@Inject
	MachineEventLogRepository machineEventLogRepository;

	Logger logger = LoggerFactory.getLogger(MachineService.class);

	public Machine findMachineById(Long id){

		Optional<Machine> machineOptional = machineRepository.findById(id);

		if(!machineOptional.isPresent()) {
			throw new ResourceNotFoundException(String.format(MessageConstants.MESSAGE_MACHINE_NOT_FOUND, id));
		}

		return machineOptional.get();
	}

	public List<Machine> getAllMachines(){

		return Optional.ofNullable(machineRepository.findAll()).orElse(new ArrayList<>());
	}

	public Machine saveMachine(Machine machine) {

		try {


			if (!machine.isMachineIsUp()) {
				machine.setLastDownTime(new Date());
			}
			else{
				machine.setLastDownTime(getLastDownTime(machine));
			}

			Machine savedMachine = this.machineRepository.save(machine);
			this.registerNewMachineEvent(machine);
			return savedMachine;
		}
		catch (Exception e){

			logger.error(String.format(MessageConstants.MESSAGE_ERROR_PERSISTING_MACHINE,
					machine.getName()), e);

			throw new PersistenceException(String.format(MessageConstants.MESSAGE_ERROR_PERSISTING_MACHINE,
					machine.getName()));
		}
	}

	private Date getLastDownTime(Machine machine){

		if(machine.getId() == null){
			return null;
		}

		Optional<Machine> machineOptional = machineRepository.findById(machine.getId());
		if(!machineOptional.isPresent()){
			return null;
		}

		return machineOptional.get().getLastDownTime();
	}

	public MachineEventLog registerNewMachineEvent(Machine machine){

		try {
			MachineEventLog machineEvent = new MachineEventLog();

			machineEvent.setMachine(machine);
			machineEvent.setTimeStamp(new Date());

			if (!machine.isMachineIsUp()) {
				machineEvent.setType(MachineEventType.NOT_RUNNING);
			} else {
				machineEvent.setType(MachineEventType.RUNNING);
			}

			return this.machineEventLogRepository.save(machineEvent);
		}
		catch (Exception e){

			logger.error(String.format(MessageConstants.MESSAGE_ERROR_PERSISTING_MACHINE_EVENT_LOG,
					machine.getName()), e);

			throw new PersistenceException(String.format(MessageConstants.MESSAGE_ERROR_PERSISTING_MACHINE_EVENT_LOG,
					machine.getName()));
		}
	}
}
