package ClassScheduling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScheduleData {
    private List<Course> courses;
    private List<Room> rooms;
    private List<TimeSlot> timeSlots;
    private Random random;

    public ScheduleData(String course, String room, String timeSlot){

        courses = loadCourses(course);
        rooms = loadRooms(room);
        timeSlots = loadTimeSlot(timeSlot);
        random = new Random();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public Course getRandomCourses() {
        int index = random.nextInt(courses.size());
        return courses.get(index);
    }

    public Room getRandomRooms() {
        int index = random.nextInt(rooms.size());
        return rooms.get(index);
    }

    public TimeSlot getRandomTimeSlots() {
        int index = random.nextInt(timeSlots.size());
        return timeSlots.get(index);
    }


    public static ArrayList<TimeSlot> loadTimeSlot(String filename) {

        ArrayList<TimeSlot> list = new ArrayList<>();
        String line;

        try {
            FileReader myFile = new FileReader(filename);
            BufferedReader reader = new BufferedReader(myFile);

            reader.readLine(); // skip first line

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                System.out.println(parts[0]+" "+parts[1]);
                Day day = Day.valueOf(parts[0].trim().toUpperCase());
                int hour = Integer.parseInt(parts[1]);
                list.add(new TimeSlot(day,hour));
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<Room> loadRooms(String filename) {

        ArrayList<Room> list = new ArrayList<>();
        String line;

        try {
            FileReader myFile = new FileReader(filename);
            BufferedReader reader = new BufferedReader(myFile);

            reader.readLine(); // skip first line

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                System.out.println(parts[0]+" "+parts[1]);
                String name = parts[0];
                int capacity = Integer.parseInt(parts[1]);
                list.add(new Room(name,capacity));
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<Course> loadCourses(String filename) {

        ArrayList<Course> list = new ArrayList<>();
        String line;

        try {
            FileReader myFile = new FileReader(filename);
            BufferedReader reader = new BufferedReader(myFile);

            reader.readLine(); // skip first line

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                System.out.println(parts[0]+" "+parts[1]+" "+parts[2]+" "+parts[3]);
                String name = parts[0];
                String profName = parts[1];
                int students = Integer.parseInt(parts[2]);
                int duration = Integer.parseInt(parts[3]);
                list.add(new Course(name,profName,students,duration));
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


}
