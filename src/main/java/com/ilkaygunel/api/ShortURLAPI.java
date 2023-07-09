package com.ilkaygunel.api;

import com.ilkaygunel.service.ShortURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ShortURLAPI {

    private final ShortURLService shortURLService;

    @Autowired
    public ShortURLAPI(ShortURLService shortURLService) {
        this.shortURLService = shortURLService;
    }

    @PostMapping("/url/shorten/")
    public ResponseEntity<String> getShortURL(@RequestBody String originalURL) {
        String shortUrl = shortURLService.shortenURL(originalURL);
        return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
    }

    @GetMapping("/{shortUrlId}")
    public ResponseEntity<Object> findAndNavigateToOriginalUrl(@PathVariable(value = "shortUrlId") final String shortUrlId) throws URISyntaxException {
        String originalUrl = shortURLService.findOriginalUrl(Long.valueOf(shortUrlId));
        URI originalURI = new URI(originalUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(originalURI);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

}
