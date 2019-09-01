package com.sample.Url.DTOs.UrlResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LongUrlResponseDTO
{
    private String longUrl;
}
