package avengers.whois.forchat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ChatRoomRepository extends JpaRepository<RoomE, Long> {
    Optional<RoomE> findBychatBox_chatNo(long chatNo);
}