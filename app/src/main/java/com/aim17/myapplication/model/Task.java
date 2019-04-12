package com.aim17.myapplication.model;

import java.io.Serializable;

public class Task implements Serializable {
    String name;
    String details;
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
