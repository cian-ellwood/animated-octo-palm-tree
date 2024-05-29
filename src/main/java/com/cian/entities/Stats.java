package com.cian.entities;

import org.springframework.data.annotation.Id;

public class Stats {

    @Id
    private String component;
    private Long loadTime;
    private Long status;
    private String contentType;
    private String server;

    public Stats() {}

    public Stats(
            String component,
            Long loadTime,
            Long status,
            String contentType,
            String server) {
        this.component = component;
        this.loadTime = loadTime;
        this.status = status;
        this.contentType = contentType;
        this.server = server;
    }
    public String getComponent(){
        return component;
    }
    public void setComponent(String component){
        this.component = component;
    }
    public Long getLoadTime() {
        return loadTime;
    }
    public void setLoadTime(Long loadTime) {
        this.loadTime = loadTime;
    }
    public Long getStatus() {
        return status;
    }
    public void setStatus(Long status) {
        this.status = status;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }
}
