package avengers.whois.domain.member;

import org.springframework.stereotype.Service;

import avengers.whois.web.member.MemberDto;
import avengers.whois.web.member.WorkerMemberDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(WorkerMemberDto workerMemberDto) {
        Member member = Member.createMember(memberDto);
        validateDuplicatedMember(memberDto.getEmail());
        memberRepository.save(member);
    }

    private void validateDuplicatedMember(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            // TODO 튕기는 로직
        }
    }

    @Override
    public void join_s(MemberDto memberDto) {
        Member member = Member.createMember(memberDto);
        memberRepository.save(member);
    }

    @Override
    public String checkEmail(String tempEmail) {
        if (memberRepository.findByEmail(tempEmail).isPresent()) {
            return "red";
        } else {
            return "green";
        }
    }

}
