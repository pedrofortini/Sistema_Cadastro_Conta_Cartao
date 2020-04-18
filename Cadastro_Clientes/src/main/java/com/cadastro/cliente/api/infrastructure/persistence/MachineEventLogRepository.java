package com.cadastro.cliente.api.infrastructure.persistence;

import com.cadastro.cliente.api.domain.machine.Machine;
import com.cadastro.cliente.api.domain.machine.MachineEventLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MachineEventLogRepository extends JpaRepository<MachineEventLog, Long> {

    List<MachineEventLog> findByMachine(Machine machine);
}
