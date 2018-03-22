package org.test.elevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.test.elevator.dto.Response;
import org.test.elevator.service.ElevatorService;

@RestController
public class ElevatorController {

	@Autowired
	private ElevatorService elevatorService;

	/**
	 * Moves the evlevator to requested floor number.
	 *
	 * @param id the elevator number
	 * @oaram floor the floor number
	 * @return
	 */
	@GetMapping("/elevator/{id}/{floor}")
	public Response moveToFloor(@PathVariable Integer id, @PathVariable Integer floor) {
		if((id != 1 && id != 2) ) {
			throw new IllegalArgumentException("Invalid elevator id");
		}else if (floor < 1 || floor > 6){
			throw new IllegalArgumentException("Invalid floor number");
		}
		elevatorService.moveToCurrentFloor(id, floor);
		Response acknowledgement = new Response();
		acknowledgement.setStatus("OK");
		acknowledgement.setMessage("Request received");
		return acknowledgement;
	}

	/**
	 * Locks the elevator breaks, to prevent it from moving.
	 *
	 * @param id the elevator number
	 * @return
	 */
	@GetMapping("/elevator/stop/{id}")
	public Response stopElevator(@PathVariable Integer id) {
		if(id != 1 && id != 2) {
			throw new IllegalArgumentException("Invalid elevator id");
		}
		elevatorService.stopElevator(id);
		Response acknowledgement = new Response();
		acknowledgement.setStatus("OK");
		acknowledgement.setMessage("Request received");
		return acknowledgement;
	}



}
