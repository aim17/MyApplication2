package com.aim17.myapplication.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Task implements Serializable {
    String name;
    String details;
    Duration duration;
    LocalDateTime due_date;

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String newDetails)
    {
        details = newDetails;
    }
}
