package ru.dsr.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dsr.api.dto.ShortLinkCreationDto;
import ru.dsr.api.dto.ShortLinkDto;
import ru.dsr.api.entity.ShortLink;
import ru.dsr.api.mapper.ShortLinkMapper;
import ru.dsr.api.repositories.ShortLinkRepository;
import ru.dsr.api.utils.ShortCodeGenerator;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;

    @Autowired
    public ShortLinkService(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    public List<ShortLinkDto> getShortLinks() {
        return shortLinkRepository.findAll().stream()
                .map(ShortLinkMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public ShortLink getShortLink(Integer id) {
        return shortLinkRepository.findById(id).orElse(null);
    }

    @Transactional
    public ShortLink createShortLink(ShortLinkCreationDto creationDto) {
        String shortCode;
        ShortLink existingShortLink;

        do {
            shortCode = ShortCodeGenerator.generateShortCode();
            existingShortLink = shortLinkRepository.findByShortCode(shortCode);
        } while (existingShortLink != null);

        ShortLink newShortLink = new ShortLink(creationDto.getUrl(), shortCode);
        return shortLinkRepository.save(newShortLink);
    }

    public String getLongLinkByShortCode(String shortCode) {
        ShortLink shortLink = shortLinkRepository.findByShortCode(shortCode);
        return (shortLink != null) ? shortLink.getUrl() : null;
    }

    @Transactional
    public void delete(Integer id) {
        shortLinkRepository.deleteById(id);
    }


    //    @Transactional
//    public Integer insertOnConflict(ShortLink shortLink) {
//        String shortCode = generateShortCode();
//        ShortLink shortLink = new ShortLink(longLink, shortCode);
//        return shortLinkRepository.insertOnConflict(shortLink.getUrl(), shortLink.getShortCode());
//    }

//    @Transactional
//    public ShortLink createShortLink(String longLink) {
//        String shortCode = ShortCodeGenerator.generateShortCode();
//        ShortLink shortLink = new ShortLink(longLink, shortCode);
//        return shortLinkRepository.save(shortLink);
//    }
}
