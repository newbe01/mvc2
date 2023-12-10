package login.itemservice;

import jakarta.annotation.PostConstruct;
import login.itemservice.domain.item.Item;
import login.itemservice.domain.item.ItemRepository;
import login.itemservice.domain.member.Member;
import login.itemservice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));

        memberRepository.save(new Member("test", "test", "test"));
    }

}
