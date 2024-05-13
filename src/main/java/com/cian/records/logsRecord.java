package com.cian.records;

public record logsRecord(
        String confidenceLevel,
        String version,
        long timestamp,
        long contentLength,
        long status,
        String contentType,

        String server
)
{
}
