package com.kaczmarczyks.pokee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.kaczmarczyks.pokee.TestClockConfiguration.MOCK_POKE_TIME;
import static com.kaczmarczyks.pokee.TestClockConfiguration.TIMEZONE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PokeControllerE2ETest {

    private static final String MESSAGE_FIRST_PART = "This service has been poked ";
    private static final String MESSAGE_SECOND_PART = "since it's wake up on ";
    private static final String ONE_TIME_STRING = " time ";
    private static final String MULTIPLE_TIMES_STRING = " times ";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testPoke() {
        //when
        ResponseEntity<String> response1 = testRestTemplate.getForEntity("/", String.class);
        ResponseEntity<String> response2 = testRestTemplate.getForEntity("/", String.class);

        //then
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response1.getBody()).contains(MESSAGE_FIRST_PART +
                1 +
                ONE_TIME_STRING +
                MESSAGE_SECOND_PART +
                MOCK_POKE_TIME)
                .contains(TIMEZONE);

        //then
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response2.getBody()).contains(MESSAGE_FIRST_PART +
                2 +
                MULTIPLE_TIMES_STRING +
                MESSAGE_SECOND_PART +
                MOCK_POKE_TIME)
                .contains(TIMEZONE);
    }

}