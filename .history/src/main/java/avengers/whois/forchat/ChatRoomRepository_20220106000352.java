package avengers.whois.forchat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<RoomE, Long> {
    Optional<RoomE> findBychatBox_chatNo(long chatNo);
}