package hello.core.order;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip는 10%할인 적용")
    void vip_o(){
        //given
        Member member = new Member(1l,"memberVip", Grade.VIP);
        int discount = discountPolicy.discount(member,10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }
    @Test
    @DisplayName("vip 아니면 10%할인 적용")
    void vip_x(){
        //given
        Member member = new Member(1l,"memberVip", Grade.Basic);
        int discount = discountPolicy.discount(member,10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }

}
