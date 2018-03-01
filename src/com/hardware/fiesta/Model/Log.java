package com.hardware.fiesta.Model;

import java.util.GregorianCalendar;

public class Log {

    private GregorianCalendar time = new GregorianCalendar();
    private String description;
    private String strTime = time.getTime().toString();
    private String type;

    public Log(String description ,String type) {

        this.description = description;
        this.type = type;
    }


    public String getDescription() {
        return description;
    }

    public String getStrTime() {
        return strTime;
    }

    public String getType() {
        return type;
    }
}
