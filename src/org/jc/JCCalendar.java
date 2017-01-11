/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class JCCalendar {

    Calendar calendar = null;

    public JCCalendar() {
        calendar = Calendar.getInstance();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
    }

    public JCCalendar(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    public String getMonth() {
        int m = getMonthInt(calendar);
        String[] months = new String[]{"Enero", "Febrero", "Marzo",
            "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre",
            "Octubre", "Noviembre", "Diciembre"};
        if (m > 12) {
            return "No existe mes";
        }

        return months[m];

    }

    public String getMonth(Calendar calendar) {
        int m = getMonthInt(calendar);
        String[] months = new String[]{"Enero", "Febrero", "Marzo",
            "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre",
            "Octubre", "Noviembre", "Diciembre"};
        if (m > 12) {
            return "No existe mes";
        }

        return months[m];

    }

    public String getDay() {
        int x = getDayOfWeek(calendar);
        String[] days = new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves",
            "Viernes", "Sabado"};

        if (x > 7) {
            return "No exite Dia";
        }

        return days[x - 1];

    }

    public String getDay(Calendar calendar) {
        int x = getDayOfWeek(calendar);
        String[] days = new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves",
            "Viernes", "Sabado"};

        if (x > 7) {
            return "No exite Dia";
        }

        return days[x - 1];

    }

    public int getMonthInt() {
        return 1 + calendar.get(Calendar.MONTH);
    }

    public int getMonthInt(Calendar calendar) {
        return 1 + calendar.get(Calendar.MONTH);
    }

    public String getDate(Calendar calendar) {
        return getYear(calendar) + "-" + getMonthInt(calendar) + "-" + getDayOfMonth(calendar);
    }

    public String getDate() {
        return getYear(calendar) + "-" + getMonthInt(calendar) + "-" + getDayOfMonth(calendar);
    }

    public String getTime() {
        return getHour() + ":" + getMinute() + ":" + getSecond();
    }

    public int getDayOfMonth() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getDayOfMonth(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getDayOfYear() {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public int getWeekOfYear() {
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public int getWeekOfMonth() {
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    public int getDayOfWeek(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }

    public int getEra() {
        return calendar.get(Calendar.ERA);
    }

    public String getUSTimeZone() {
        String[] zones = new String[]{"Hawaii", "Alaskan", "Pacific",
            "Mountain", "Central", "Eastern"};

        return zones[10 + getZoneOffset()];
    }

    public int getZoneOffset() {
        return calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000);
    }

    public int getDSTOffset() {
        return calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000);
    }

    public int getAMPM() {
        return calendar.get(Calendar.AM_PM);
    }
}
