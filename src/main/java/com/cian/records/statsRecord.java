package com.cian.records;

public record statsRecord(
        long loadTime,
        long status,
        String server,
        String contentType
)
{
}
