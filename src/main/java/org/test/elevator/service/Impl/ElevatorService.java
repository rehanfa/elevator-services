package org.test.elevator.service.Impl;

import org.test.elevator.exception.StatusMessageException;

public interface ElevatorService {
    /**
     * Moves the evlevator to requested floor number.
     *
     * @param elevatorId the elevator number
     * @oaram floorParam the floor number
     * @Exception throws StatusMessageException
     * @return
     */
    void moveToCurrentFloor(int elevatorId, int floorParam) throws StatusMessageException;

    /**
     * Locks the elevator breaks, to prevent it from moving.
     *
     * @param elevatorId the elevator number
     * @return
     */
    void stopElevator(int elevatorId);
}
