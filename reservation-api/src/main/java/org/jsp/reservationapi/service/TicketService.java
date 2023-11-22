package org.jsp.reservationapi.service;

import java.util.Optional;

import org.jsp.reservationapi.dao.BusDao;
import org.jsp.reservationapi.dao.TicketDao;
import org.jsp.reservationapi.dao.UserDao;
import org.jsp.reservationapi.dto.Bus;
import org.jsp.reservationapi.dto.EmailConfiguration;
import org.jsp.reservationapi.dto.ResponseStructure;
import org.jsp.reservationapi.dto.Ticket;
import org.jsp.reservationapi.dto.User;
import org.jsp.reservationapi.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
	@Autowired
	private TicketDao dao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BusDao busDao;
	@Autowired
	public EmailConfiguration config;
	@Autowired
	public EmailService service;

	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(Ticket ticket, int user_id, int bus_id) {
		Optional<Bus> budata = busDao.findById(bus_id);
		
		Optional<User> recUser = userDao.findById(user_id);
		

		ResponseStructure<Ticket> structure = new ResponseStructure<>();
		if (budata.isPresent() && recUser.isPresent()) {
			Bus b = budata.get();
			User u = recUser.get();
			b.getTickets().add(ticket);
			u.getTickets().add(ticket);
			ticket.setCost(ticket.getNumber_of_seats() * b.getCost_per_seat());
			ticket.setBus(b);
			ticket.setUser(u);
			config.setTo(u.getEmail());
			config.setSubject("cinfirmed on ticket Booking");
			config.setText("number of seats booked" + ticket.getNumber_of_seats() + "/n" + "seat numbers"
					+ ticket.getSeat_no() + "/n" + b.getBus_no());
			String message = service.sendEmail(config);
			busDao.updateBus(b);
			userDao.updateUser(u);
			structure.setData(dao.saveTicket(ticket));
			structure.setMessege(message);
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);

		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(Ticket ticket, int bus_id, int user_id) {
		ResponseStructure<Ticket> structure = new ResponseStructure<>();
		Optional<Bus> busdata = busDao.findById(bus_id);
		Optional<User> userdata = userDao.findById(user_id);
		if (busdata.isPresent() && userdata.isPresent()) {
			Bus b = busdata.get();
			User u = userdata.get();
			ticket.setCost(ticket.getNumber_of_seats() * busdata.get().getCost_per_seat());
			ticket.setBus(busdata.get());
			ticket.setUser(userdata.get());
			config.setTo(u.getEmail());
			config.setSubject("updated on ticket Booking");
			config.setText(
					"Dear passender " + u.getName() + "/n" + "number of seats booked" + ticket.getNumber_of_seats()
							+ "/n" + "seat numbers" + ticket.getSeat_no() + "/n" + b.getBus_no() + "/n" + "Thank You");
			String message = service.sendEmail(config);
			busDao.updateBus(busdata.get());
			userDao.updateUser(userdata.get());
			structure.setData(dao.saveTicket(ticket));
			structure.setMessege(message);
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<String>> deleteTicket(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Ticket> recticket = dao.findById(id);
		if (recticket.isEmpty()) {
			throw new IdNotFoundException();
		}
		User u = recticket.get().getUser();
		Bus b = recticket.get().getBus();
		Ticket ticket = recticket.get();
		dao.deleteTicket(recticket.get());
		config.setTo(u.getEmail());
		config.setSubject("Deleted your ticket");
		config.setText("Dear passender " + u.getName() + "/n" + "number of seats booked" + ticket.getNumber_of_seats()
				+ "/n" + "seat numbers" + ticket.getSeat_no() + "/n" + b.getBus_no() + "/n" + "Thank You");
		String message = service.sendEmail(config);
		structure.setData("Booking has been cancelled");
		structure.setMessege(message);
		structure.setStatuscode(HttpStatus.OK.value());
		

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}

}
