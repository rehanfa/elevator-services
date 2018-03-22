package org.test.elevator.api;

/**
 * A Stub implementation of ElevatorFacade.
 * 
 * @author jrickards
 */
public class ElevatorFacadeStub implements ElevatorFacade {
	
	private boolean breaksOn = false;
	private int currentFloor = 0;
	private final ElevatorCallback callback;
	private int elevatorId;

	/**
	 * Initialise ElevatorFacadeStub with elevator id and callback.
	 *
	 * @param elevatorId the elevator number
	 * @param callback a class to notify completion of a task
	 * @return
	 */
	public ElevatorFacadeStub(int elevatorId, ElevatorCallback callback) {
		if (callback == null) throw new IllegalArgumentException("callback can not be null");
		this.elevatorId = elevatorId;
		this.callback = callback;
	}

	/**
	 * Moves an Elevator up one Floor.
	 */
	
	@Override
	public void moveUpOneFloor() {
		if (!breaksOn) {
			System.out.println("Current Floor " + ++currentFloor);
			callback.elevatorArrived(currentFloor);
		}
	}

	/**
	 * Moves an Elevator down one Floor.
	 */
	@Override
	public void moveDownOneFloor() {
		if (!breaksOn) {
			System.out.println("Current Floor " + --currentFloor);
			callback.elevatorArrived(currentFloor);
		}
	}

	/**
	 * Locks the elevator breaks, to prevent it from moving.
	 */
	@Override
	public void lockBreaks() {
		breaksOn = true;
		System.out.println("breaksOn=" + breaksOn);
	}

	/**
	 * Unlocks the elevator breaks allowing it to move again.
	 */
	@Override
	public void unlockBreaks() {
		breaksOn = false;
		System.out.println("breaksOn=" + breaksOn);
	}

	/**
	 * Returns current floor for elevator
	 */
	@Override
	public int getCurrentFloor(){
		return this.currentFloor;
	}

}
