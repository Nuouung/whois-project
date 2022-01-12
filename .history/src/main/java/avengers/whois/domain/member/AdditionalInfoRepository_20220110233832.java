package avengers.whois.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {

    public AdditionalInfo getByNewMember_id(String id);

}
