package app.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

@Entity
public class Room {

    @Id
    private RoomNames name;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateCreated;



    public Room() {
    }

    public Room(RoomNames name, Date dateCreated) {
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public RoomNames getName() {
        return name;
    }

    public void setName(RoomNames name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }


    @Override
    public String toString() {
        return "Room{" +
                "name=" + name +
                ", dateCreated=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (name != room.name) return false;
        return dateCreated != null ? dateCreated.equals(room.dateCreated) : room.dateCreated == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }
}
