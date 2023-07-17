package ru.dsr.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dsr.api.entity.ShortLink;
import ru.dsr.api.services.ShortLinkService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @Autowired
    public ShortLinkController(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @GetMapping("/all")
    public List<ShortLink> getShortLinks() {
        return shortLinkService.getShortLinks();
    }

    @PostMapping("/shorten")
    public String createShortLink(@RequestBody String longLink) {
        ShortLink shortLink = shortLinkService.createShortLink(longLink);
        return "Short link created: " + "http://localhost:8080/api/" + shortLink.getShortCode();
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToLongLink(@PathVariable String shortCode) {
        String longLink = shortLinkService.getLongLinkByShortCode(shortCode);
        if (longLink != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, longLink)
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
