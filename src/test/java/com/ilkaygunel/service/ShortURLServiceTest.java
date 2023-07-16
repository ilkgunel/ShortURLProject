package com.ilkaygunel.service;

import com.ilkaygunel.configuration.GeneralConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.properties")
@ContextConfiguration(classes = GeneralConfig.class)
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
public class ShortURLServiceTest {

    @InjectMocks
    ShortURLService shortURLService;

    @Value("${short.url.domain}")
    private String shortUrlDomain;

    @Value("${server.port}")
    private String shortUrlPort;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(shortURLService, "shortUrlDomain", shortUrlDomain);
        ReflectionTestUtils.setField(shortURLService, "shortUrlPort", shortUrlPort);
    }

    @Test
    public void testShortenURL() {
        String a = shortUrlDomain;
        String shortUrl = shortURLService.shortenURL("http://ilkaygunel.com");
        assertTrue(shortUrl.contains(shortUrlDomain.concat(":").concat(shortUrlPort)));
    }

}
