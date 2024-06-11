package com.cian.entities;

import org.springframework.data.annotation.Id;

public class Logs {

    @Id
    private String id;
    private String confidenceLevel;
    private String version;
    private String service;
    private String message;
    private String server;

    public Logs() {}

    public Logs(
            String confidenceLevel,
            String version,
            String service,
            String message,
            String server) {
        this.confidenceLevel = confidenceLevel;
        this.version = version;
        this.service = service;
        this.message = message;
        this.server = server;
    }
    public String getConfidenceLevel() {
        return confidenceLevel;
    }
    public void setConfidenceLevel(String confidenceLevel){
        this.confidenceLevel = confidenceLevel;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getService(){
        return service;
    }
    public void setService(String service){
        this.service = service;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }
}
