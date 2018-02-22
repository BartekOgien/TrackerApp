package com.tracker.repository;

import com.tracker.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TicketDao extends JpaRepository<Ticket, Integer> {
}
