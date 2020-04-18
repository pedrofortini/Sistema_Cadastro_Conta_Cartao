package com.cadastro.cliente.api.application.controllers;

import com.cadastro.cliente.api.domain.machine.MachineService;
import com.cadastro.cliente.api.domain.machine.converter.MachineResponseConverter;
import com.cadastro.cliente.api.domain.machine.Machine;
import com.cadastro.cliente.api.domain.machine.MachineEventLog;
import com.cadastro.cliente.api.domain.machine.MachineEventLogService;
import com.cadastro.cliente.api.domain.machine.converter.MachineRequestConverter;
import io.swagger.api.MachinesApi;
import io.swagger.model.MachineDetailResponse;
import io.swagger.model.MachineRequest;
import io.swagger.model.MachineResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
public class MachinesController implements MachinesApi {

	@Inject
	private MachineService machineService;

	@Inject
	private MachineEventLogService eventLogService;

	@Inject
	private MachineRequestConverter requestConverter;

	@Inject
	private MachineResponseConverter responseConverter;

	@Override
	public ResponseEntity<MachineDetailResponse> getMachineById(@PathVariable("id") Long id) {

		Machine machine = machineService.findMachineById(id);
		List<MachineEventLog> machineEventLogs = eventLogService.findEventsOfMachine(machine);
		MachineDetailResponse response = responseConverter.convertToDetail(machine, machineEventLogs);

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<MachineResponse>> getMachines() {

		List<Machine> machines = machineService.getAllMachines();
		List<MachineResponse> machineResponses = responseConverter.convertListMachines(machines);
		return ResponseEntity.ok(machineResponses);
	}

	@Override
	public ResponseEntity<Void> saveMachine(@Valid @RequestBody MachineRequest machine) {

		Machine domainMachine = requestConverter.convert(machine);
		machineService.saveMachine(domainMachine);

		return ResponseEntity.ok().build();
	}
}
