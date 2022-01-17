package avengers.whois.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultiFileRepository extends JpaRepository<MultiFile, Long>{

}
