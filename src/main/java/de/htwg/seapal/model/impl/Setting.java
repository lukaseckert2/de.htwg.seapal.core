package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.ISetting;
import de.htwg.seapal.model.ModelDocument;

import java.util.UUID;

/**
 * personal setting of an account.
 */
public final class Setting extends ModelDocument implements ISetting {
    private String distanceUnit;
    private String temperatureUnit;
    private int trackingDelay;
    private int waypointDelay;
    private int historyTrend;
    private int circleRadius;

    public Setting(final ISetting setting) {
        super(setting.getUUID().toString());
        this.distanceUnit = setting.getDistanceUnit();
        this.temperatureUnit = setting.gettemperatureUnit();
        this.trackingDelay = setting.getTrackingDelay();
        this.waypointDelay = setting.getWaypointDelay();
        this.historyTrend = setting.getHistoryTrend();
        this.circleRadius = setting.getCircleRadius();
    }

    public Setting() {
        super(UUID.randomUUID().toString());
        this.distanceUnit = "";
        this.temperatureUnit = "";
        this.trackingDelay = 0;
        this.waypointDelay = 0;
        this.historyTrend = 0;
        this.circleRadius = 0;
    }

    @Override
    public String getDistanceUnit() {
        return this.distanceUnit;
    }

    @Override
    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    @Override
    public String gettemperatureUnit() {
        return this.temperatureUnit;
    }

    @Override
    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    @Override
    public int getTrackingDelay() {
        return this.trackingDelay;
    }

    @Override
    public void setTrackingDelay(int trackingDelay) {
        this.trackingDelay = trackingDelay;
    }

    @Override
    public int getWaypointDelay() {
        return this.waypointDelay;
    }

    @Override
    public void setWaypointDelay(int waypointDelay) {
        this.waypointDelay = waypointDelay;
    }

    @Override
    public int getHistoryTrend() {
        return this.historyTrend;
    }

    @Override
    public void setHistoryTrend(int historyTrend) {
        this.historyTrend = historyTrend;
    }

    @Override
    public int getCircleRadius() {
        return this.circleRadius;
    }

    @Override
    public void setcircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
