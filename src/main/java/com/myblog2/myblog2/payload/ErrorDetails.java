package com.myblog2.myblog2.payload;

import java.util.Date;

public class ErrorDetails  {
    private Date TimeStamp;
    private String  message;
    private String  details;

    public ErrorDetails(Date timeStamp, String message, String details) {
        TimeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
