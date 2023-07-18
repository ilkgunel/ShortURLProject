package com.ilkaygunel.service;

import com.ilkaygunel.exception.customexceptions.NotFoundException;
import com.ilkaygunel.singleton.SingletonBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ShortURLService {

    @Value("${short.url.domain}")
    private String shortUrlDomain;

    @Value("${server.port}")
    private String shortUrlPort;

    public String shortenURL(String originalURL) {
        Long shortURLId = new Date().getTime();

        String shortUrl = shortUrlDomain.concat(":").concat(shortUrlPort).concat("/").concat(String.valueOf(shortURLId));

        Map<String, String> shortAndOriginalUrl = new HashMap<>();
        shortAndOriginalUrl.put(originalURL, shortUrl);

        SingletonBean.getInstance().shortUrls.put(shortURLId, shortAndOriginalUrl);

        return shortUrl;
    }

    public String findOriginalUrl(Long shortUrlId) throws NotFoundException {
        Map<String, String> shortAndOriginalUrl = SingletonBean.getInstance().shortUrls.get(shortUrlId);
        if (shortAndOriginalUrl == null) {
            throw new NotFoundException("The original url can't found with this short url id:".concat(String.valueOf(shortUrlId)));
        }
        AtomicReference<String> originalUrl = new AtomicReference<>();
        shortAndOriginalUrl.entrySet().stream().forEach(entry -> {
            originalUrl.set(entry.getKey());
        });
        return originalUrl.get();
    }
}
