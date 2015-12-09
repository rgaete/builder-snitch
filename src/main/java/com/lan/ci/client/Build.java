package com.lan.ci.client;

import java.io.Serializable;

/**
 * Created by rgaete on 05-06-15.
 */
public class Build implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String full_url;

    public String getFull_url() {
        return full_url;
    }

    public void setFull_url(String full_url) {
        this.full_url = full_url;
    }

    private int number;
    private String phase;
    private String url;

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        return number;

    }

    public void setNumber(int number) {
        this.number = number;
    }
    //"scm":{},"artifacts":{}}
}
