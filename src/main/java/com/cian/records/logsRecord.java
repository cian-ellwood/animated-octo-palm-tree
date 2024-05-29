package com.cian.records;

//Beginning the lift and shift away from a records based model to a mongo objects based model instead
//TODO clean up the records class in the future
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
