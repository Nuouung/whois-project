package avengers.whois.domain.member;

import org.springframework.stereotype.Service;

import avengers.whois.web.member.CorporateMemberDto;
import avengers.whois.web.member.WorkerMemberDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final WorkerMemberRepository workerMemberRepository;
    private final CorporateMemberRepository corporateMemberRepository;

    @Override
    public String checkEmailW(String tempEmail) {
        if (workerMemberRepository.findByEmail(tempEmail).isPresent()) {
            return "red";
        } else {
            return "green";
        }
    }

    @Override
    public String checkEmailC(String tempEmail) {
        if (corporateMemberRepository.findByEmail(tempEmail).isPresent()) {
            return "red";
        } else {
            return "green";
        }
    }

    @Override
    public void joinW(WorkerMemberDto workerMemberDto) {
        // TODO Auto-generated method stub
    }

    @Override
    public void joinC(CorporateMemberDto corporateMemberDto) {
        // TODO Auto-generated method stub

    }
}
