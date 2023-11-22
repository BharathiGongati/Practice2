package org.jsp.reservationapi.dao;

import java.util.Optional;

import org.jsp.reservationapi.dto.Ticket;
import org.jsp.reservationapi.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao {
	@Autowired
	private TicketRepository repository;
    
	public Ticket saveTicket(Ticket ticket) {
		return repository.save(ticket);
	}
	
	public Ticket updateTicket(Ticket ticket) {
		return repository.save(ticket);
	}
	public Optional<Ticket> findById(int id) 
	{
		return repository.findById(id);
	}
	public void deleteTicket(Ticket ticket)
	{
		repository.delete(ticket);
	}

}
