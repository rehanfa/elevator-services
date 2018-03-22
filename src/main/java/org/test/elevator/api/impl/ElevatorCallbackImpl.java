package org.test.elevator.api.impl;

import org.test.elevator.api.ElevatorCallback;

public class ElevatorCallbackImpl implements ElevatorCallback {
    private int currentFloor = 1;

    /**
     * Notifies that an elevator arrived on a specific floor.
     *
     * @param floor
     */
    @Override
    public void elevatorArrived(int floor) {
        this.setCurrentFloor(floor);

    }

    /**
     * Returns current floor for elevator
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * sets current floor for elevator
     */

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}
