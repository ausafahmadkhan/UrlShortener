package com.sample.Url.Mapper;

import com.sample.Url.DTOs.UrlRequestDTO.LongUrlRequestDTO;
import com.sample.Url.DTOs.UrlRequestDTO.ShortUrlRequestDTO;
import com.sample.Url.DTOs.UrlResponseDTO.LongUrlResponseDTO;
import com.sample.Url.DTOs.UrlResponseDTO.ShortUrlResponseDTO;
import com.sample.Url.UrlRequests.LongUrlRequest;
import com.sample.Url.UrlRequests.ShortUrlRequest;
import com.sample.Url.UrlResponses.LongUrlResponse;
import com.sample.Url.UrlResponses.ShortUrlResponse;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper
{
     public LongUrlRequestDTO mapLongUrlRequestToDTO(LongUrlRequest longUrlRequest)
     {
         LongUrlRequestDTO longUrlRequestDTO = new LongUrlRequestDTO();
         longUrlRequestDTO.setLongUrl(longUrlRequest.getLongUrl());
         return longUrlRequestDTO;
     }

     public ShortUrlRequestDTO mapShortUrlToDTO(ShortUrlRequest shortUrlRequest)
     {
         ShortUrlRequestDTO shortUrlRequestDTO = new ShortUrlRequestDTO();
         shortUrlRequestDTO.setShortUrl(shortUrlRequest.getShortUrl());
         return shortUrlRequestDTO;
     }

     public LongUrlResponse mapDTOToLongUrlResponse(LongUrlResponseDTO longUrlResponseDTO)
     {
         LongUrlResponse longUrlResponse = new LongUrlResponse();
         longUrlResponse.setLongUrl(longUrlResponseDTO.getLongUrl());
         return longUrlResponse;
     }

     public ShortUrlResponse mapDTOToShortUrlResponse(ShortUrlResponseDTO shortUrlResponseDTO)
     {
         ShortUrlResponse shortUrlResponse = new ShortUrlResponse();
         shortUrlResponse.setShortUrl(shortUrlResponseDTO.getShortUrl());
         return shortUrlResponse;
     }
}
