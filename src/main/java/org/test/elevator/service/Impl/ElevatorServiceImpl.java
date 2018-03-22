package org.test.elevator.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import org.test.elevator.exception.StatusMessageException;
import org.test.elevator.api.ElevatorFacade;
import org.test.elevator.api.ElevatorFactory;
import org.test.elevator.api.impl.ElevatorCallbackImpl;
import org.test.elevator.dto.Status;
import org.test.elevator.service.ElevatorService;

@Component
public class ElevatorServiceImpl implements ElevatorService {

	@Autowired
	private SimpMessagingTemplate template;

	static {

		ElevatorFactory.getElevatorFacade(1, new ElevatorCallbackImpl());
		ElevatorFactory.getElevatorFacade(2, new ElevatorCallbackImpl());
	}

	/**
	 * Moves the evlevator to requested floor number.
	 *
	 * @param elevatorId the elevator number
	 * @oaram floorParam the floor number
	 * @return
	 */

	public void moveToCurrentFloor(int elevatorId, int floorParam) throws StatusMessageException {
		if (elevatorId !=1 && elevatorId != 2){
			throw new IllegalArgumentException("Invalid elevator id");
		}
		else if (floorParam < 1 || floorParam > 6){
			throw new IllegalArgumentException("Invalid floor number");
		}
		ElevatorFacade elevatorFacade = ElevatorFactory.getElevatorFacade(elevatorId, null);
		elevatorFacade.unlockBreaks();
		int currentFloor = elevatorFacade.getCurrentFloor() ;
		int initialFloor = elevatorFacade.getCurrentFloor() ;
		if(currentFloor != floorParam) {
			this.sendStatus("Elevator "+ elevatorId + " got request to move to " + floorParam + " from floor " + currentFloor);
			if(currentFloor < floorParam) {
				while(floorParam != elevatorFacade.getCurrentFloor()) {
					currentFloor = elevatorFacade.getCurrentFloor();
					elevatorFacade.moveUpOneFloor();
					this.sendStatus("Elevator "+ elevatorId + " moved to floor " + (currentFloor + 1) + " from floor " + currentFloor);
				}
			} else if (currentFloor > floorParam) {
				while(floorParam != elevatorFacade.getCurrentFloor()) {
					currentFloor = elevatorFacade.getCurrentFloor();
					elevatorFacade.moveDownOneFloor();
					this.sendStatus("Elevator "+ elevatorId + " moved to floor " + (currentFloor - 1) + " from floor " + currentFloor);
				}
			}

			this.sendStatus("Elevator "+ elevatorId + " reached to floor " + floorParam + " from floor " + initialFloor);

		}
	}

	/**
	 * Locks the elevator breaks, to prevent it from moving.
	 *
	 * @param elevatorId the elevator number
	 * @return
	 */
	public void stopElevator(int elevatorId) {
		if (elevatorId !=1 && elevatorId != 2){
			throw new IllegalArgumentException("Invalid elevator id");
		}
		ElevatorFacade elevatorFacade = ElevatorFactory.getElevatorFacade(elevatorId, null);
		elevatorFacade.lockBreaks();
	}

	/**
	 * Sends message to the status topic
	 *
	 * @param message message to be consumed
	 * @return
	 */
	public Status sendStatus(String message) throws StatusMessageException{
		Status retVal = null;
		try {
			retVal = new Status(message);
			this.getTemplate().convertAndSend("/topic/status", retVal);
			return retVal;
		}catch (Exception e) {
			throw new StatusMessageException("Unable to send status message", e);
		}

	}

	public SimpMessagingTemplate getTemplate() {
		return template;
	}

	public void setTemplate(SimpMessagingTemplate template) {
		this.template = template;
	}
}