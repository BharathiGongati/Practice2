package org.jsp.reservationapi.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(name = "from_loc", nullable = false)
	private String from;
	@Column(name = "to_loc", nullable = false)
	private String to;
	@Column(nullable = false)
	private String bus_no;
	@Column(nullable = false, name = "date_of_departure ")
	private LocalDate dop;
	@Column(nullable = false)
	private int nos;
	private Time dep_time;// java.sql
	private Time arr_time;
	private String image_url;
	private double cost_per_seat;
	@ManyToOne
	@JoinColumn(name = "admin_id")
	@JsonIgnore
	private Admin admin;
	@OneToMany(mappedBy = "bus")
	private List<Ticket> tickets;
	
}
