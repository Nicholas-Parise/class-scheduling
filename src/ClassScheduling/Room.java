package ClassScheduling;

import java.util.Objects;

public class Room {
    private int capacity;
    private String name;

    public Room(String name,int capacity){
        this.capacity = capacity;
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return getCapacity() == room.getCapacity() && Objects.equals(getName(), room.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCapacity(), getName());
    }


    @Override
    public String toString() {
        return "Room{" +
                "capacity=" + capacity +
                ", name='" + name + '\'' +
                '}';
    }
}
