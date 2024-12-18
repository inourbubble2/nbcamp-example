package com.navi.nbcampspringredisexample.database.repository;

import com.navi.nbcampspringredisexample.database.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
