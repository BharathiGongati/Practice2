package org.jsp.reservationapi.repository;

import org.jsp.reservationapi.dto.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
