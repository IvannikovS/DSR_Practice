package ru.dsr.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShortLinkDto {
    @NotNull
    private String url;
    @NotNull
    private String shortCode;
}
