package com.aim17.myapplication.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

/* Design Notes:
* Java does not include any time period object (i.e. start time to end time)
* We want our object to be serializable to store it in a file.
* This model will do a naive and dirty implementation of a time slot
*   for two reasons:
*   1) To determine what works and what doesn't
*   2) Figure out more closely what the requirements of our time slot are
*
*  This imeplementation may or may not be suitable for final usage.
* */

public class TimeSlot implements Serializable {
    LocalDateTime startTime;
    // There are two ways to represent this:
    //  1) A begin time and a duration of the slot
    //  OR
    //  2) A begin time and an end time
    Duration duration;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    // This function requires api level 26+
    LocalDateTime getEndTime(){
        LocalDateTime endTime = startTime.plus(duration);
        return endTime;
    }

}
