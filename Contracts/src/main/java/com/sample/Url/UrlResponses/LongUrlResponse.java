package com.sample.Url.UrlResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LongUrlResponse
{
    private String longUrl;
    private UrlError urlError;
}
