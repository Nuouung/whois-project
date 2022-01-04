package avengers.whois.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateMemberRepository extends JpaRepository<CorporateMember, Long> {

    public Optional<WorkerMember> findByEmail(String email);

}
