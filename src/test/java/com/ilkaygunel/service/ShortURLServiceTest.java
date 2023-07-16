package com.ilkaygunel.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ContextConfiguration(classes = ShortURLService.class)
public class ShortURLServiceTest {

    @Autowired
    private ShortURLService shortURLService;

    @Value("${short.url.domain}")
    private String shortUrlDomain;

    @Value("${server.port}")
    private String shortUrlPort;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShortenURL() {
        String shortUrl = shortURLService.shortenURL("http://ilkaygunel.com");
        assertTrue(shortUrl.contains(shortUrlDomain.concat(":").concat(shortUrlPort)));
    }

}
