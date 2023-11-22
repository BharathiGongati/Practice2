package org.jsp.reservationapi.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.reservationapi.dto.Bus;
import org.jsp.reservationapi.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
	@Autowired
	private BusRepository repository;
	
	public Bus saveBus(Bus bus) {
		return repository.save(bus);
	}

	public Bus updateBus(Bus bus) {
		return repository.save(bus);
	}
	public List<Bus> filter(String from,String to,LocalDate dop){
		return repository.filter(from,to,dop);
	}
	public Optional<Bus> findById(int id) {
		return repository.findById(id);
	}
	

}
