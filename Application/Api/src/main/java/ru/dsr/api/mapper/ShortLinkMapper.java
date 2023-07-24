package ru.dsr.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.dsr.api.dto.ShortLinkCreationDto;
import ru.dsr.api.dto.ShortLinkDto;
import ru.dsr.api.entity.ShortLink;

@Mapper
public interface ShortLinkMapper {
    ShortLinkMapper INSTANCE = Mappers.getMapper(ShortLinkMapper.class);

    @Mapping(source = "url", target = "url")
    @Mapping(source = "shortCode", target = "shortCode")
    ShortLinkDto toDto(ShortLink shortLink);
    @Mapping(source = "url", target = "url")
    @Mapping(source = "shortCode", target = "shortCode")
    ShortLink toEntity(ShortLinkDto shortLinkDto);
    @Mapping(source = "url", target = "url")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shortCode", ignore = true)
    ShortLink toEntityFromShortLinkCreationDto(ShortLinkCreationDto shortLinkCreationDto);

}
