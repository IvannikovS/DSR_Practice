package ru.dsr.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsr.api.entity.ShortLink;
import ru.dsr.api.repositories.ShortLinkRepository;

import java.util.List;

@Service
public class ShortLinkService {
    private final ShortLinkRepository shortLinkRepository;

    @Autowired
    public ShortLinkService(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    public List<ShortLink> getShortLinks() {
        return shortLinkRepository.findAll();
    }

    public ShortLink createShortLink(String longLink) {
        String shortCode = generateShortCode(longLink);
        ShortLink shortLink = new ShortLink(longLink, shortCode);
        return shortLinkRepository.save(shortLink);
    }

    public String getLongLinkByShortCode(String shortCode) {
        ShortLink shortLink = shortLinkRepository.findByShortCode(shortCode);
        return (shortLink != null) ? shortLink.getUrl() : null;
    }

    private String generateShortCode(String longLink) {
        return "test123";
    }
}
