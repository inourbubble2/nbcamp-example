package com.navi.nbcampspringredisexample.api.service;

import com.navi.nbcampspringredisexample.database.entity.Ticket;
import com.navi.nbcampspringredisexample.database.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Ticket reserveTicket(Long ticketId, Long userId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();

        ticket.setReservedBy(userId);

        return ticket;
    }

}
