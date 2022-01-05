package avengers.whois.domain.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateMemberRepository extends JpaRepository<CorporateMember, Long> {

    public Optional<CorporateMember> findByEmail(String email);

    public CorporateMember getByEmailAndPassword(String email, String password);

}
