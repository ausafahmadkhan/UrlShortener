package com.sample.Url.Controller;

import com.sample.Url.DTOs.UrlRequestDTO.LongUrlRequestDTO;
import com.sample.Url.DTOs.UrlRequestDTO.ShortUrlRequestDTO;
import com.sample.Url.DTOs.UrlResponseDTO.LongUrlResponseDTO;
import com.sample.Url.DTOs.UrlResponseDTO.ShortUrlResponseDTO;
import com.sample.Url.Mapper.UrlMapper;
import com.sample.Url.Services.UrlShortenerService;
import com.sample.Url.UrlRequests.LongUrlRequest;
import com.sample.Url.UrlRequests.ShortUrlRequest;
import com.sample.Url.UrlResponses.LongUrlResponse;
import com.sample.Url.UrlResponses.ResponseModel;
import com.sample.Url.UrlResponses.ShortUrlResponse;
import com.sample.Url.UrlResponses.UrlError;
import com.sample.Url.Validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Url")
public class UrlController
{
    @Autowired
    private UrlMapper urlMapper;

    @Autowired
    private UrlValidator urlValidator;

    @Autowired
    private UrlShortenerService urlShortenerService;

    @RequestMapping(path = "/getShort", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ResponseModel<ShortUrlResponse>> getShortUrl(@RequestBody LongUrlRequest longUrlRequest)
    {
        UrlError urlError = new UrlError();
        ShortUrlResponse shortUrlResponse = new ShortUrlResponse();
        try
        {
            urlValidator.isValidLongUrl(longUrlRequest);
            LongUrlRequestDTO longUrlRequestDTO = urlMapper.mapLongUrlRequestToDTO(longUrlRequest);
            ShortUrlResponseDTO shortUrlResponseDTO = urlShortenerService.getShortUrl(longUrlRequestDTO);
            shortUrlResponse = urlMapper.mapDTOToShortUrlResponse(shortUrlResponseDTO);
        }
        catch (IllegalArgumentException e)
        {
            urlError.setErrorMessage(e.getMessage());
            urlError.setStatus(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            urlError.setErrorMessage(e.getMessage());
            urlError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally
        {
            if (urlError.getErrorMessage() != null)
            {
                shortUrlResponse.setUrlError(urlError);
                return new ResponseEntity<ResponseModel<ShortUrlResponse>>(new ResponseModel<ShortUrlResponse>(shortUrlResponse), urlError.getStatus());
            }
            else
            {
                return new ResponseEntity<ResponseModel<ShortUrlResponse>>(new ResponseModel<ShortUrlResponse>(shortUrlResponse), HttpStatus.OK);
            }
        }
    }

    @RequestMapping(path = "/getLong", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ResponseModel<LongUrlResponse>> getLongUrl(@RequestBody ShortUrlRequest shortUrlRequest)
    {
        UrlError urlError = new UrlError();
        LongUrlResponse longUrlResponse = new LongUrlResponse();
        try
        {
            urlValidator.isValidShortUrl(shortUrlRequest);
            ShortUrlRequestDTO shortUrlRequestDTO = urlMapper.mapShortUrlToDTO(shortUrlRequest);
            LongUrlResponseDTO longUrlResponseDTO = urlShortenerService.getLongUrl(shortUrlRequestDTO);
            longUrlResponse = urlMapper.mapDTOToLongUrlResponse(longUrlResponseDTO);
        }
        catch (IllegalArgumentException e)
        {
            urlError.setErrorMessage(e.getMessage());
            urlError.setStatus(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            urlError.setErrorMessage(e.getMessage());
            urlError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally
        {
            if (urlError.getErrorMessage() != null)
            {
                longUrlResponse.setUrlError(urlError);
                return new ResponseEntity<ResponseModel<LongUrlResponse>>(new ResponseModel<LongUrlResponse>(longUrlResponse), urlError.getStatus());
            }
            else
                return new ResponseEntity<ResponseModel<LongUrlResponse>>(new ResponseModel<LongUrlResponse>(longUrlResponse), HttpStatus.OK);
        }
    }
}
