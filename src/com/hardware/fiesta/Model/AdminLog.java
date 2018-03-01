package com.hardware.fiesta.Model;

import java.util.GregorianCalendar;

public class AdminLog extends Log{

    GregorianCalendar time = new GregorianCalendar();

    public AdminLog(String description) {
        super(description,"Admin");
    }
}
