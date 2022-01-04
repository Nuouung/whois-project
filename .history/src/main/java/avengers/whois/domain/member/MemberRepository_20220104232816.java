package avengers.whois.domain.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerMemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findByEmail(String email);

}
