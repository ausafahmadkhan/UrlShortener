package com.sample.Url.UrlResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlResponse
{
    private String shortUrl;
    private UrlError urlError;
}
