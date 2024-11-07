package ClassScheduling;

import java.util.Objects;

public class TimeSlot {
    private int hour;
    private Day day;

    public TimeSlot(Day day,int hour){
        this.hour = hour;
        this.day = day;
    }

    public Day getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return getHour() == timeSlot.getHour() && getDay() == timeSlot.getDay();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHour(), getDay());
    }
}
