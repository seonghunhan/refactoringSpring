package com.example.demo.src.unitTest;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LottoNumberGenerator {

    public List<Integer> generate(int money) {

        if (!isValidMoney(money)) {
            throw new RuntimeException("올바른 금액이 아닙니다");
        }
        return generate();
    }

    private boolean isValidMoney(int money) {
        return money == 1000;
    }

    private List<Integer> generate() {
        return new Random()
                .ints(1, 45 + 1)
                .distinct()
                .limit(6)
                .boxed()
                .collect(Collectors.toList());
    }

//    @DisplayName("로또 번호 갯수 테스트")
//    @Test
//    void lottoNumberSizeTest() {
//        //given
//        LottoNumberGenerator generator = new LottoNumberGenerator();
//        int price = 1_000;
//        //when
//        List<Integer> list = generator.generate(price);
//        //then
//        Assertions.assertThat(list.size()).isEqualTo(6);
//
//
//    }

}
