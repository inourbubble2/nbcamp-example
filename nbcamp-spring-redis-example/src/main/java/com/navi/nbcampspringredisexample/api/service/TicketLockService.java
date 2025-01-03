package com.navi.nbcampspringredisexample.api.service;

import com.navi.nbcampspringredisexample.database.entity.Ticket;
import com.navi.nbcampspringredisexample.database.repository.TicketRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketLockService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final TicketService ticketService;

    private static final String TICKET_LOCK_VALUE = "locked";

    private final TicketRepository ticketRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Ticket reserveTicketInner(Long ticketId, Long userId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();

        ticket.setReservedBy(userId);

        return ticket;
    }


    public Ticket reserveTicket(Long ticketId, Long userId) {
        String key = "ticket:lock:" + ticketId;
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, TICKET_LOCK_VALUE, Duration.ofSeconds(10));

        if (Boolean.TRUE.equals(success)) {
            // Lock 획득에 성공하면 reserveTicket 실행
            try {
                return reserveTicketInner(ticketId, userId);
//                return ticketService.reserveTicket(ticketId, userId);
            } finally {
                redisTemplate.delete(key); // Lock 반환
            }
        } else {
            // Lock 획득에 실패하면 에러 발생
            throw new IllegalStateException("Ticket Lock 획득 실패");
        }
    }
}
