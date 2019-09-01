package com.sample.Url.Services;

import com.google.common.hash.Hashing;
import com.sample.Url.DTOs.UrlRequestDTO.LongUrlRequestDTO;
import com.sample.Url.DTOs.UrlRequestDTO.ShortUrlRequestDTO;
import com.sample.Url.DTOs.UrlResponseDTO.LongUrlResponseDTO;
import com.sample.Url.DTOs.UrlResponseDTO.ShortUrlResponseDTO;
import com.sample.Url.Persistence.Model.UrlDAO;
import com.sample.Url.Persistence.Repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static com.google.common.hash.Hashing.murmur3_32;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService
{
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public ShortUrlResponseDTO getShortUrl(LongUrlRequestDTO longUrlRequestDTO)
    {
        ShortUrlResponseDTO shortUrlResponseDTO = new ShortUrlResponseDTO();
        LongUrlResponseDTO longUrlResponseDTO = new LongUrlResponseDTO();
        String id;
        do {
           id = Hashing.murmur3_32().hashString(longUrlRequestDTO.getLongUrl(),StandardCharsets.UTF_8).toString();
           ShortUrlRequestDTO shortUrlRequestDTO = new ShortUrlRequestDTO(id);
           longUrlResponseDTO = getLongUrl(shortUrlRequestDTO);
           if (longUrlResponseDTO.getLongUrl().equals(longUrlRequestDTO.getLongUrl()))
           {
               shortUrlResponseDTO.setShortUrl(id);
               return shortUrlResponseDTO;
           }
        }
        while (!longUrlResponseDTO.getLongUrl().equals("Url is not present."));
        shortUrlResponseDTO.setShortUrl(id);
        UrlDAO urlDAO = new UrlDAO();
        urlDAO.setLongUrl(longUrlRequestDTO.getLongUrl());
        urlDAO.setShortUrl(shortUrlResponseDTO.getShortUrl());
        urlRepository.save(urlDAO);
        return shortUrlResponseDTO;
    }

    @Override
    public LongUrlResponseDTO getLongUrl(ShortUrlRequestDTO shortUrlRequestDTO)
    {
        LongUrlResponseDTO longUrlResponseDTO = new LongUrlResponseDTO();
        UrlDAO urlDAO = urlRepository.findById(shortUrlRequestDTO.getShortUrl()).orElse(null);
        if (urlDAO == null)
            longUrlResponseDTO.setLongUrl("Url is not present.");
        else
            longUrlResponseDTO.setLongUrl(urlDAO.getLongUrl());
        return longUrlResponseDTO;
    }

}
