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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PokeControllerE2ETest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testPoke() {
        //when
        ResponseEntity<String> response1 = testRestTemplate.getForEntity("/", String.class);
        ResponseEntity<String> response2 = testRestTemplate.getForEntity("/", String.class);

        //then
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response1.getBody()).contains("This service has been poked 1 time.");

        //then
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response2.getBody()).contains("This service has been poked 2 times.");
    }


}