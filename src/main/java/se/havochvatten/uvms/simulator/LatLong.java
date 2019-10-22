package se.havochvatten.uvms.simulator;

import java.util.Date;

public class LatLong {

    public double latitude;
    public double longitude;
    public Date positionTime;
    public double bearing = Double.MIN_NORMAL;
    public double distance = 0;
    public double speed = 0;

    public LatLong(double latitude, double longitude, Date positionTime) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.positionTime = positionTime;
    }

    @Override
    public String toString() {
        String formatStr = "%2.6f";

        return "LatLong{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", positionTime=" + positionTime +
                ", bearing=" + bearing +
                ", distance=" + distance +
                ", speed=" + speed +
                '}';
    }
}
