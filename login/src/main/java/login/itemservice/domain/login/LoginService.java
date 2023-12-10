package login.itemservice.domain.login;

import login.itemservice.domain.member.Member;
import login.itemservice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     *
     * @return null > login fail
     */
    public Member login(String loginId, String password) {
//        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
//        Member member = findMember.get();
//
//        if (member.getPassword().equals(password)) {
//            return member;
//        } else {
//            return null;
//        }

        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
