package org.test.elevator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.test.elevator.dto.Status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ElevatorServiceTest {

    @Test
    public void testStopElevator() {
        int elevatorId = 1;
        ElevatorService elevatorService = new ElevatorService();
        elevatorService.stopElevator(elevatorId);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStopElevatorWithException() {
        int elevatorId = 4;
        ElevatorService elevatorService = new ElevatorService();
        elevatorService.stopElevator(elevatorId);

    }

    @Test
    public void testMoveElevator() {
        ElevatorService elevatorService = new ElevatorService();
        elevatorService.setTemplate(Mockito.mock(SimpMessagingTemplate.class));
        elevatorService.moveToCurrentFloor(1,5);
        verify(elevatorService.getTemplate(), atLeastOnce()).convertAndSend(anyString(), any(Status.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveElevatorWithInvalidFloor() {
        ElevatorService elevatorService = new ElevatorService();
        elevatorService.moveToCurrentFloor(1,10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveElevatorWithInvalidId() {
        ElevatorService elevatorService = new ElevatorService();
        elevatorService.moveToCurrentFloor(10,4);
    }
}
