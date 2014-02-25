package de.htwg.seapal.model;

public interface ISetting extends IModel {
    public String getDistanceUnit();

    public void setDistanceUnit(final String distanceUnit);

    public String gettemperatureUnit();

    public void setTemperatureUnit(final String temperatureUnit);

    public int getTrackingDelay();

    public void setTrackingDelay(final int trackingDelay);

    public int getWaypointDelay();

    public void setWaypointDelay(final int waypointDelay);

    public int getHistoryTrend();

    public void setHistoryTrend(final int historyTrend);

    public int getCircleRadius();

    public void setcircleRadius(final int circleRadius);
}
