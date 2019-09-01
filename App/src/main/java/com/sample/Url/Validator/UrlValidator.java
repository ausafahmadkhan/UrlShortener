package com.sample.Url.Validator;

import com.sample.Url.UrlRequests.LongUrlRequest;
import com.sample.Url.UrlRequests.ShortUrlRequest;
import org.springframework.stereotype.Component;

@Component
public class UrlValidator
{
    public boolean isValidLongUrl(LongUrlRequest longUrlRequest)
    {
        if (!longUrlRequest.getLongUrl().isEmpty() && longUrlRequest.getLongUrl() != null)
            return true;
        else
            throw new IllegalArgumentException();
    }

    public boolean isValidShortUrl(ShortUrlRequest shortUrlRequest)
    {
        if (!shortUrlRequest.getShortUrl().isEmpty() && shortUrlRequest.getShortUrl() != null)
            return true;
        else
            throw new IllegalArgumentException();
    }
}
