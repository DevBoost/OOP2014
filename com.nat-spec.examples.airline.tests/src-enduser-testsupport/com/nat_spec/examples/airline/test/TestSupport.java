package com.nat_spec.examples.airline.test;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;

import com.nat_spec.examples.airline.persistence.InMemoryPersistenceContext;
import com.nat_spec.examples.airline.persistence.entity.AirplaneType;
import com.nat_spec.examples.airline.persistence.entity.Flight;
import com.nat_spec.examples.airline.persistence.entity.Passenger;
import com.nat_spec.examples.airline.services.AirlineServices;
import com.nat_spec.examples.airline.services.OperationStatus;

import de.devboost.natspec.annotations.TextSyntax;

public class TestSupport {

	private AirlineServices services;
	private InMemoryPersistenceContext persistenceContext;

	public TestSupport(AirlineServices services,
			InMemoryPersistenceContext persistenceContext) {
		super();
		this.services = services;
		this.persistenceContext = persistenceContext;
	}

	@TextSyntax("With age of #2 years")
	public void setAge(Passenger passenger, int age) {
		passenger.setAge(age);
	}

	@TextSyntax("Assume no valid ticket is issued")
	public void assumeFailure(OperationStatus status) {
		Assert.assertFalse(status.toString(), status.isValid());
	}

	@TextSyntax("Assume #1 has passenger #2")
	public void hasPassenger(Flight flight, Passenger passenger) {
		Assert.assertTrue(flight.hasPassenger(passenger));
	}

	@TextSyntax("Book seat for #1 at #2")
	public OperationStatus bookSeat(Passenger passenger, Flight flight) {
		return services.bookSeat(passenger, flight);
	}

	@TextSyntax("Cancel seat for #1 at #2")
	public OperationStatus cancelSeat(Passenger passenger, Flight flight) {
		return services.cancelSeat(passenger, flight);
	}

	@TextSyntax("Given a Passenger #1 #2")
	public Passenger givenAPassenger(String firstname, String lastname) {
		return persistenceContext.createPassenger(firstname, lastname);
	}

	@TextSyntax("Given an Airplane #1")
	public AirplaneType givenAnAirplane(String airplaneType) {
		return persistenceContext.createAirplaneType(airplaneType);
	}

	@TextSyntax("Given a flight #1")
	public Flight givenAFlight(String flightName) {
		return persistenceContext.createFlight(flightName);
	}

	@TextSyntax("that is executed using a #1")
	public void withAirplane(AirplaneType airplaneType, Flight flight) {
		flight.setAirplane(airplaneType);
		persistenceContext.update(flight);
	}

	@TextSyntax("With #1 free seats")
	public void withFreeSeats(int numberOfSeats, Flight flight) {
		flight.setFreeSeats(numberOfSeats);
		persistenceContext.update(flight);
	}

	@TextSyntax("Assume a valid ticket is issued")
	public void assumeAValidTicketIsIssued(OperationStatus status) {
		assertTrue(status.getMessage(), status.isValid());
	}

	@TextSyntax("Assume cancellation successful")
	public void assumeCancellationSuccessful(OperationStatus status) {
		assertTrue(status.getMessage(), status.isValid());
	}
}
