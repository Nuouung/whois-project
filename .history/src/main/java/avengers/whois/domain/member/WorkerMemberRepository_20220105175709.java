package avengers.whois.domain.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerMemberRepository extends JpaRepository<WorkerMember, Long> {

    public Optional<WorkerMember> findByEmail(String email);

    public WorkerMember getByEmailAndPassword(String email, String password);

}
