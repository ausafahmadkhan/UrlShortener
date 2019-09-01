package com.sample.Url.Services;

import com.sample.Url.DTOs.UrlRequestDTO.LongUrlRequestDTO;
import com.sample.Url.DTOs.UrlRequestDTO.ShortUrlRequestDTO;
import com.sample.Url.DTOs.UrlResponseDTO.LongUrlResponseDTO;
import com.sample.Url.DTOs.UrlResponseDTO.ShortUrlResponseDTO;

public interface UrlShortenerService
{
    public ShortUrlResponseDTO getShortUrl(LongUrlRequestDTO longUrlRequestDTO);
    public LongUrlResponseDTO getLongUrl(ShortUrlRequestDTO shortUrlRequestDTO);
}
