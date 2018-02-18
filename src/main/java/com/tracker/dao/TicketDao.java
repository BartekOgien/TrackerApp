package com.tracker.dao;

import com.tracker.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TicketDao extends JpaRepository<Ticket, Integer> {
    Ticket findByIdNumber(Integer idNumber);
}
