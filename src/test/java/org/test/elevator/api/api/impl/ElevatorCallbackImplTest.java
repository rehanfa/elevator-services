package org.test.elevator.api.api.impl;

import org.junit.Test;
import org.test.elevator.api.ElevatorCallback;
import org.test.elevator.api.impl.ElevatorCallbackImpl;

import static org.junit.Assert.assertEquals;

public class ElevatorCallbackImplTest {

    @Test
    public void testElevatorArrived() {
        ElevatorCallback elevatorCallback = new ElevatorCallbackImpl();
        elevatorCallback.elevatorArrived(4);
        assertEquals(((ElevatorCallbackImpl)elevatorCallback).getCurrentFloor(), 4);

    }

    @Test
    public void testGetCurrentFloor() {
        ElevatorCallback elevatorCallback = new ElevatorCallbackImpl();
        assertEquals(((ElevatorCallbackImpl)elevatorCallback).getCurrentFloor(), 0);

    }
}
