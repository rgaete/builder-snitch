package com.lan.ci.client;

import java.io.Serializable;

/**
 * Created by rgaete on 05-06-15.
 */
public class RequestMessage implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jobName;
    private String url;
    private Build build;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }
}
