package com.cadastro.cliente.api.domain.machine;

import com.cadastro.cliente.api.infrastructure.persistence.MachineEventLogRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MachineEventLogService {

    @Inject
    private MachineEventLogRepository repository;

    public List<MachineEventLog> findEventsOfMachine(Machine machine){

        return Optional.ofNullable(repository.findByMachine(machine)).orElse(new ArrayList<>());
    }
}
