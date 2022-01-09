package avengers.whois.forchat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomE, Long> {
    Optional<RoomE> findBychatBox_chatNo(long chatNo);

    Optional<RoomE> findByRoomName(String roomName);

    Optional<RoomE> findByRoomId(String roomId);
}