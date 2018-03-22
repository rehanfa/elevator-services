package org.test.elevator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.test.elevator.exception.StatusMessageException;
import org.test.elevator.dto.Status;
import org.test.elevator.service.Impl.ElevatorServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ElevatorServiceImplTest {

    @Test
    public void testStopElevator() {
        int elevatorId = 1;
        ElevatorServiceImpl elevatorService = new ElevatorServiceImpl();
        elevatorService.stopElevator(elevatorId);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStopElevatorWithException() {
        int elevatorId = 4;
        ElevatorServiceImpl elevatorService = new ElevatorServiceImpl();
        elevatorService.stopElevator(elevatorId);

    }

    @Test
    public void testMoveElevator() throws StatusMessageException {
        ElevatorServiceImpl elevatorService = new ElevatorServiceImpl();
        elevatorService.setTemplate(Mockito.mock(SimpMessagingTemplate.class));
        elevatorService.moveToCurrentFloor(1,5);
        verify(elevatorService.getTemplate(), atLeastOnce()).convertAndSend(anyString(), any(Status.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveElevatorWithInvalidFloor() throws StatusMessageException {
        ElevatorServiceImpl elevatorService = new ElevatorServiceImpl();
        elevatorService.moveToCurrentFloor(1,10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveElevatorWithInvalidId() throws StatusMessageException {
        ElevatorServiceImpl elevatorService = new ElevatorServiceImpl();
        elevatorService.moveToCurrentFloor(10,4);
    }
}
