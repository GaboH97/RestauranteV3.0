/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

/**
 *
 * @author Lenovo
 */
public class TimeConstants {

    public static final int WORK_DAY_DURATION = 480; //In minutes

    private int globalClockCounter;
    private int dayCounter;
    private int dayCount;
    private int weekCount;

    public TimeConstants() {
        globalClockCounter = 0;
        dayCounter = 0;
    }
    
    public void advanceDay(){
        this.dayCount++;
    }

    public void advance(int minutes) {
        dayCounter += minutes;
    }

    public int getGlobalClockCounter() {
        return globalClockCounter;
    }

    public void setGlobalClockCounter(int globalClockCounter) {
        this.globalClockCounter = globalClockCounter;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }

}
