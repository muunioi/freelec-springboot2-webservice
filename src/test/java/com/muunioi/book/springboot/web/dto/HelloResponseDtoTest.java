package com.muunioi.book.springboot.web.dto;

import org.junit.Test;
/* import static org.junit.Assert.assertThat; */
/* Junit의 asserThat이 아닌 assertj의 asserThat 사용 */
import static org.assertj.core.api.Assertions.assertThat;


public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
