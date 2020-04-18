package com.cadastro.cliente.api.domain.machine;

import com.cadastro.cliente.api.application.exception.PersistenceException;
import com.cadastro.cliente.api.application.exception.ResourceNotFoundException;
import com.cadastro.cliente.api.infrastructure.persistence.MachineEventLogRepository;
import com.cadastro.cliente.api.infrastructure.persistence.MachineRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MachineServiceTest {

    private MachineService service;
    private MachineRepository machineRepository;
    private MachineEventLogRepository machineEventLogRepository;

    @Before
    public void setUp() {

        this.service = new MachineService();
        this.machineRepository = PowerMockito.mock(MachineRepository.class);
        this.machineEventLogRepository = PowerMockito.mock(MachineEventLogRepository.class);

        Whitebox.setInternalState(service, "machineRepository", machineRepository);
        Whitebox.setInternalState(service, "machineEventLogRepository", machineEventLogRepository);
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenMachineNotFound(){

        PowerMockito.when(machineRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findMachineById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(String.format("Couldn't find Machine with id %s", 1L));
    }

    @Test
    public void shouldReturnMachineInstanceWhenMachineFound(){

        PowerMockito.when(machineRepository.findById(1L)).thenReturn(Optional.of(new Machine()));
        Assert.assertTrue(service.findMachineById(1L) instanceof Machine);
    }

    @Test
    public void shouldReturnMachineInstanceWithCorrectIdWhenMachineFound(){

        Machine machine = new Machine();
        machine.setId(1L);

        PowerMockito.when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));
        Machine ret = service.findMachineById(1L);
        Assert.assertEquals(machine.getId(), ret.getId());
    }

    @Test
    public void shouldReturnMachineEmptyMachineListIfFindAllReturnedNull(){

        PowerMockito.when(machineRepository.findAll()).thenReturn(null);
        List<Machine> ret = service.getAllMachines();
        Assert.assertEquals(new ArrayList<>(), ret);
    }

    @Test
    public void shouldReturnMachineListIfFindAllReturnedSomeMachines(){

        List<Machine> machineList = new ArrayList<>();
        machineList.add(new Machine());

        PowerMockito.when(machineRepository.findAll()).thenReturn(machineList);
        List<Machine> ret = service.getAllMachines();
        Assert.assertEquals(1, ret.size());
    }

    @Test
    public void shouldCallRepositorySaveMethodOnMachineWithCorrectData(){

        Machine machine = new Machine();
        machine.setMachineIsUp(false);

        Mockito.when(machineRepository.save(Mockito.any(Machine.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Machine savedMachine = service.saveMachine(machine);
        assertThat(savedMachine.isMachineIsUp()).isFalse();
        assertThat(savedMachine.getLastDownTime()).isBeforeOrEqualsTo(new Date());
    }

    @Test
    public void shouldThrowPersistenceExceptionWhenProblemPersistingMachine(){

        PowerMockito.when(machineRepository.save(Mockito.any(Machine.class))).thenThrow(new RuntimeException());

        Machine machine = new Machine();
        machine.setName("teste");

        assertThatThrownBy(() -> service.saveMachine(machine))
                .isInstanceOf(PersistenceException.class)
                .hasMessage(String.format("An error ocurred while persisting Machine %s",
                        machine.getName()));
    }

    @Test
    public void shouldCallRepositorySaveMethodOnMachineEventLogWithNotRunningMachineData(){

        Machine machine = new Machine();
        machine.setMachineIsUp(false);

        Mockito.when(machineEventLogRepository.save(Mockito.any(MachineEventLog.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        MachineEventLog savedMachineEventLog = service.registerNewMachineEvent(machine);

        assertThat(savedMachineEventLog.getType()).isEqualTo(MachineEventType.NOT_RUNNING);
        assertThat(savedMachineEventLog.getTimeStamp()).isBeforeOrEqualsTo(new Date());
        assertThat(savedMachineEventLog.getMachine().getId()).isEqualTo(machine.getId());
    }

    @Test
    public void shouldCallRepositorySaveMethodOnMachineEventLogWithRunningMachineData(){

        Machine machine = new Machine();
        machine.setMachineIsUp(true);

        Mockito.when(machineEventLogRepository.save(Mockito.any(MachineEventLog.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        MachineEventLog savedMachineEventLog = service.registerNewMachineEvent(machine);

        assertThat(savedMachineEventLog.getType()).isEqualTo(MachineEventType.RUNNING);
        assertThat(savedMachineEventLog.getTimeStamp()).isBeforeOrEqualsTo(new Date());
        assertThat(savedMachineEventLog.getMachine().getId()).isEqualTo(machine.getId());
    }

    @Test
    public void shouldThrowPersistenceExceptionWhenProblemPersistingMachineEvent(){

        PowerMockito.when(machineEventLogRepository.save(Mockito.any(MachineEventLog.class))).thenThrow(new RuntimeException());

        Machine machine = new Machine();
        machine.setName("teste");

        assertThatThrownBy(() -> service.registerNewMachineEvent(machine))
                .isInstanceOf(PersistenceException.class)
                .hasMessage(String.format("An error ocurred while persisting event log for Machine %s",
                        machine.getName()));
    }
}