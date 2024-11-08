package ClassScheduling;

public class Gene {
    private Course course;
    private Room room;
    private TimeSlot timeSlot;

    public Gene(Course course, Room room, TimeSlot timeSlot){
        this.course = course;
        this.room = room;
        this.timeSlot = timeSlot;
    }

    /**
     * There was an error with genes getting cobbled by other references.
     * by copying the gene when crossing over this won't happen.
     * @param gene
     */
    public Gene(Gene gene){
        this.course = gene.getCourse();
        this.room = gene.getRoom();
        this.timeSlot = gene.getTimeSlot();
    }


    /**
     * a simple helper method to check if timeslots overlap
     * @param other other gene
     * @return true if overlaps, false if it doesn't
     */
    public boolean overlaps(Gene other) {

        // if they are on different days then they cannot overlap
        if(!this.getTimeSlot().getDay().equals(other.getTimeSlot().getDay())){
            return false; // so just return false
        }

        int start1 = this.getTimeSlot().getHour();
        int end1 = start1 + this.getCourse().getDuration();
        int start2 = other.getTimeSlot().getHour();
        int end2 = start2 + other.getCourse().getDuration();
        return (start1 < end2) && (start2 < end1);
    }


    public Course getCourse() {
        return course;
    }

    public Room getRoom() {
        return room;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public void setRoom(Room room) {
        this.room = room;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "Gene{" +
                "course=" + course +
                ", room=" + room +
                ", timeSlot=" + timeSlot +
                '}';
    }
}
