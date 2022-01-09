package avengers.whois.forchat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import avengers.whois.forchat.entities.RoomE;

public interface RoomRepository extends JpaRepository<RoomE, Long> {
    Optional<RoomE> findBychatbox_chatNo(long chatNo);

    Optional<RoomE> findByRoomName(String roomName);

    Optional<RoomE> findByRoomId(String roomId);
}