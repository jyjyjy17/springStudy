package hello.itemservice.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jpa.JpaItemRepositoryV2;
import hello.itemservice.repository.jpa.JpaItemRepositoryV3;
import hello.itemservice.repository.v2.ItemQueryRepositoryV2;
import hello.itemservice.repository.v2.ItemRepositoryV2;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import hello.itemservice.service.ItemServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class V2Config {
    //스프링이 자동으로 jpa에 쓰기 위해 빈등록함
    private final EntityManager em;
    //springboot가 자동으로 jpaRepository는 구현체를 (스프링이 만들고) (스프링부트는)빈등록해줌.
    private final ItemRepositoryV2 itemRepositoryV2;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV2(itemRepositoryV2, ItemQueryRepository());
    }

    @Bean
    public ItemQueryRepositoryV2 ItemQueryRepository() {
        return new ItemQueryRepositoryV2(em,jpaQueryFactory());
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }

    //이게 필요한 건 아닌데 Test에서 ItemRepository가 쓰여서 추가해줬다.
    //Test 하려면 사실 새로 만들어야 한다.. 의미가 없다.
    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV3(em,jpaQueryFactory());
    }
}
