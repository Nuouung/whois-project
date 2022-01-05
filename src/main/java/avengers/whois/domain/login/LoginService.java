package avengers.whois.domain.login;

import avengers.whois.domain.member.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

     private final WorkerMemberRepository workerMemberRepository;
     private final CorporateMemberRepository corporateMemberRepository;

     public static void main(String[] args) {
          Map<String, Member> mp = new HashMap<>();
          System.out.println(mp.getClass());
     }

     /**
     * 로그인
     * 객체를 편하게 다루기 위해 빈 상자인 인터페이스 Member를 만들어 기업회원, 개인회원을 상속케 했다.
     * 애플리케이션의 확장 가능성을 고려해 로그인 정보를 HashMap에 담았다. (지금은 괜찮은데 나중에 이 부분 때문에 코드를 고칠 일이 없도록 하기 위해!)
     */
     public Map<String, Member> login(String email, String password, String memberType) {

          // 개인 회원, 기업 회원인지 판별 후 로그인 절차 수행
          // null check는 악의적인 접근을 차단하는 로직!
          Map<String, Member> loginMap = new HashMap<>();
          if (memberType == null) {
               loginMap.put(LoginConst.LOGIN_FAIL, null);
          } else {
               switch (memberType) {
                    case LoginConst.MEMBER_WORKER:
                         loginWorker(email, password, loginMap);
                         break;
                    case LoginConst.MEMBER_CORPORATION:
                         loginCorporation(email, password, loginMap);
                         break;
               }
          }
          return loginMap;
     }

     // 개인회원 로그인
     private void loginWorker(String email, String password, Map<String, Member> loginMap) {
         Optional<WorkerMember> foundMember = workerMemberRepository.findByEmail(email);

         // 아이디 있음
         if (foundMember.isPresent()) {
             WorkerMember workerMember = foundMember.get();
             if (workerMember.getPassword().equals(password)) {
                 // 비밀번호 있음
                 loginMap.put(LoginConst.MEMBER_WORKER, workerMember);
                 return;
             }
         }
         loginMap.put(LoginConst.LOGIN_FAIL, null);
     }

     // 기업회원 로그인
     private void loginCorporation(String email, String password, Map<String, Member> loginMap) {
          Optional<CorporateMember> foundMember = corporateMemberRepository.findByEmail(email);

          // 아이디 있음
          if (foundMember.isPresent()) {
               CorporateMember corporateMember = foundMember.get();
               if (corporateMember.getPassword().equals(password)) {
                    // 비밀번호 있음
                    loginMap.put(LoginConst.MEMBER_CORPORATION, corporateMember);
                    return;
               }
          }
          loginMap.put(LoginConst.LOGIN_FAIL, null);
     }

     /**
     * 로그아웃
     */
     public void logout(HttpSession session) {
          if (session != null) {
               session.removeAttribute(LoginConst.loginSession);
               session.invalidate();
          }
     }
}
