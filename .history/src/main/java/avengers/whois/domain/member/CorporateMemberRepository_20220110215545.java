package avengers.whois.domain.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateMemberRepository extends JpaRepository<CorporateMember, Long> {

    public Optional<WorkerMember> getByEmailAndPassword(String email, String password);

}
