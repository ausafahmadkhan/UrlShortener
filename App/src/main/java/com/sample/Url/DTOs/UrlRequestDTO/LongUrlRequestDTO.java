package com.sample.Url.DTOs.UrlRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LongUrlRequestDTO
{
    private String longUrl;
}
