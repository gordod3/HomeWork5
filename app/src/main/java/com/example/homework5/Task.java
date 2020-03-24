package com.example.homework5;
import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    public String title, description;
    public Date startDate, deadline;
    public Boolean isDone = false;
}
