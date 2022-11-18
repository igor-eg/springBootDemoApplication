package ru.netology.springbootdemoapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.Assert.assertEquals;

//@SpringBootTest
//class SpringBootDemoApplicationTests {

//@Test
//void contextLoads() {
// }

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    private final GenericContainer<?> myAppFirst = new GenericContainer<>("tcapp:1.0")
            .withExposedPorts(8081);
    private final GenericContainer<?> myAppSecond = new GenericContainer<>("tcapp:2.0")
            .withExposedPorts(8081);

    @BeforeEach
    void setUp() {
        myAppFirst.start();
        myAppSecond.start();
    }

    @Test
    void contextLoadsFirst() {
        ResponseEntity<String> forEntityFirst = restTemplate.getForEntity("http://localhost:" + myAppFirst
                .getMappedPort(8081), String.class);
        System.out.println(forEntityFirst.getBody());

        assertEquals("Current profile is dev", forEntityFirst.getBody());

        ResponseEntity<String> forEntitySecond = restTemplate.getForEntity("http://localhost:" + myAppFirst
                .getMappedPort(8081), String.class);
        System.out.println(forEntitySecond.getBody());

        assertEquals("Current profile is production", forEntitySecond.getBody());

    }

    @Test
    void contextLoadsSecond() {
        ResponseEntity<String> forEntityFirst = restTemplate.getForEntity("http://localhost:" + myAppFirst
                .getMappedPort(8080), String.class);
        System.out.println(forEntityFirst.getBody());

        assertEquals("Current profile is dev", forEntityFirst.getBody());

        ResponseEntity<String> forEntitySecond = restTemplate.getForEntity("http://localhost:" + myAppFirst
                .getMappedPort(8080), String.class);
        System.out.println(forEntitySecond.getBody());

        assertEquals("Current profile is production", forEntitySecond.getBody());

    }

}

//}
