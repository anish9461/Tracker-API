package project.truckerapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
public class Tires {

    @Id
    private String TiresID;

    @OneToOne(mappedBy = "tires")
    private Readings readings;

    private int frontLeft;
    private int frontRight;
    private int rearLeft;
    private int rearRight;


    public Tires()
    {

        this.TiresID = UUID.randomUUID().toString();

    }

    public String getTiresID() {
        return TiresID;
    }

    public void setTiresID(String tiresID) {
        TiresID = tiresID;
    }

    public int getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(int frontLeft) {
        this.frontLeft = frontLeft;
    }

    public int getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(int frontRight) {
        this.frontRight = frontRight;
    }

    public int getRearLeft() {
        return rearLeft;
    }

    public void setRearLeft(int rearLeft) {
        this.rearLeft = rearLeft;
    }

    public int getRearRight() {
        return rearRight;
    }

    public void setRearRight(int rearRight) {
        this.rearRight = rearRight;
    }

    @Override
    public String toString() {
        return "Tires{" +
                "TiresID='" + TiresID + '\'' +
                ", frontLeft=" + frontLeft +
                ", frontRight=" + frontRight +
                ", rearLeft=" + rearLeft +
                ", rearRight=" + rearRight +
                '}';
    }
}
