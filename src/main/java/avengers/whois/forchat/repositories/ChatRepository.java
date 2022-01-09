package avengers.whois.forchat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import avengers.whois.forchat.entities.ChatE;

public interface ChatRepository extends JpaRepository<ChatE, Long> {

}
