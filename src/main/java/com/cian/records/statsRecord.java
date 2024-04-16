package com.cian.records;

public record statsRecord(
        long loadTime,
        long status,
        String contentType,

        String server
)
{
}
