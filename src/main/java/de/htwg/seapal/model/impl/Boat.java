package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IBoat;
import de.htwg.seapal.model.ModelDocument;

import java.util.UUID;

public class Boat extends ModelDocument implements IBoat {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private String boatName;
    private String registerNr;
    private String sailSign;
    private String homePort;
    private String yachtclub;
    private String insurance;
    private String callSign;
    private String boatType;
    private String constructor;
    private Double length;
    private Double width;
    private Double draft;
    private Double mastHeight;
    private Double displacement;
    private String rigging;
    private Integer yearOfConstruction;
    private String motor;
    private Double tankSize;
    private Double wasteWaterTankSize;
    private Double freshWaterTankSize;
    private Double mainSailSize;
    private Double genuaSize;
    private Double spiSize;
    private String boatOwner;

    public Boat() {
        super(UUID.randomUUID().toString());
        this.boatName = "";
        this.registerNr = "";
        this.sailSign = "";
        this.homePort = "";
        this.yachtclub = "";
        this.insurance = "";
        this.callSign = "";
        this.boatType = "";
        this.constructor = "";
        this.length = 0D;
        this.width = 0D;
        this.draft = 0D;
        this.mastHeight = 0D;
        this.displacement = 0D;
        this.rigging = "";
        this.yearOfConstruction = 0;
        this.motor = "";
        this.tankSize = 0D;
        this.wasteWaterTankSize = 0D;
        this.freshWaterTankSize = 0D;
        this.mainSailSize = 0D;
        this.genuaSize = 0D;
        this.spiSize = 0D;
        this.boatOwner = "";
    }

    public Boat(IBoat boat) {
        super(boat);
        this.boatName = boat.getName();
        this.registerNr = boat.getRegisterNr();
        this.sailSign = boat.getSailSign();
        this.homePort = boat.getHomePort();
        this.yachtclub = boat.getYachtclub();
        this.insurance = boat.getInsurance();
        this.callSign = boat.getCallSign();
        this.boatType = boat.getBoatType();
        this.constructor = boat.getBoatConstructor();
        this.length = boat.getLength();
        this.width = boat.getLength();
        this.draft = boat.getDraft();
        this.mastHeight = boat.getMastHeight();
        this.displacement = boat.getDisplacement();
        this.rigging = boat.getRigging();
        this.yearOfConstruction = boat.getYearOfConstruction();
        this.motor = boat.getMotor();
        this.tankSize = boat.getFreshWaterTankSize();
        this.wasteWaterTankSize = boat.getFreshWaterTankSize();
        this.freshWaterTankSize = boat.getFreshWaterTankSize();
        this.mainSailSize = boat.getMainSailSize();
        this.genuaSize = boat.getGenuaSize();
        this.spiSize = boat.getGenuaSize();
        this.boatOwner = boat.getBoatOwner();
    }

    @Override
    public String getName() {
        return boatName;
    }

    @Override
    public void setName(String boatName) {
        this.boatName = boatName;
    }

    @Override
    public String getRegisterNr() {
        return registerNr;
    }

    @Override
    public void setRegisterNr(String registerNr) {
        this.registerNr = registerNr;
    }

    @Override
    public String getSailSign() {
        return sailSign;
    }

    @Override
    public void setSailSign(String sailSign) {
        this.sailSign = sailSign;
    }

    @Override
    public String getHomePort() {
        return homePort;
    }

    @Override
    public void setHomePort(String homePort) {
        this.homePort = homePort;
    }

    @Override
    public String getYachtclub() {
        return yachtclub;
    }

    @Override
    public void setYachtclub(String yachtclub) {
        this.yachtclub = yachtclub;
    }

    @Override
    public String getInsurance() {
        return insurance;
    }

    @Override
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    @Override
    public String getCallSign() {
        return callSign;
    }

    @Override
    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String type) {
        this.boatType = type;
    }

    @Override
    public String getBoatConstructor() {
        return constructor;
    }

    @Override
    public void setBoatConstructor(String constructor) {
        this.constructor = constructor;
    }

    @Override
    public Double getLength() {
        return length;
    }

    @Override
    public void setLength(Double length) {
        this.length = length;
    }

    @Override
    public Double getWidth() {
        return width;
    }

    @Override
    public void setWidth(Double width) {
        this.width = width;
    }

    @Override
    public Double getDraft() {
        return draft;
    }

    @Override
    public void setDraft(Double draft) {
        this.draft = draft;
    }

    @Override
    public Double getMastHeight() {
        return mastHeight;
    }

    @Override
    public void setMastHeight(Double mastHeight) {
        this.mastHeight = mastHeight;
    }

    @Override
    public Double getDisplacement() {
        return displacement;
    }

    @Override
    public void setDisplacement(Double displacement) {
        this.displacement = displacement;
    }

    @Override
    public String getRigging() {
        return rigging;
    }

    @Override
    public void setRigging(String rigging) {
        this.rigging = rigging;
    }

    @Override
    public Integer getYearOfConstruction() {
        return yearOfConstruction;
    }

    @Override
    public void setYearOfConstruction(Integer yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }

    @Override
    public String getMotor() {
        return motor;
    }

    @Override
    public void setMotor(String motor) {
        this.motor = motor;
    }

    @Override
    public Double getTankSize() {
        return tankSize;
    }

    @Override
    public void setTankSize(Double tankSize) {
        this.tankSize = tankSize;
    }

    @Override
    public Double getWasteWaterTankSize() {
        return wasteWaterTankSize;
    }

    @Override
    public void setWasteWaterTankSize(Double wasteWaterTankSize) {
        this.wasteWaterTankSize = wasteWaterTankSize;
    }

    @Override
    public Double getFreshWaterTankSize() {
        return freshWaterTankSize;
    }

    @Override
    public void setFreshWaterTankSize(Double freshWaterTankSize) {
        this.freshWaterTankSize = freshWaterTankSize;
    }

    @Override
    public Double getMainSailSize() {
        return mainSailSize;
    }

    @Override
    public void setMainSailSize(Double mainSailSize) {
        this.mainSailSize = mainSailSize;
    }

    @Override
    public Double getGenuaSize() {
        return genuaSize;
    }

    @Override
    public void setGenuaSize(Double genuaSize) {
        this.genuaSize = genuaSize;
    }

    @Override
    public Double getSpiSize() {
        return spiSize;
    }

    @Override
    public void setSpiSize(Double spiSize) {
        this.spiSize = spiSize;
    }

    @Override
    public String getBoatOwner() {
        return boatOwner;
    }

    @Override
    public void setBoatOwner(String boatOwner) {
        this.boatOwner = boatOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Boat) {
            Boat boat = (Boat) o;
            boolean returnValue = true;
            returnValue = returnValue && boatName.equals(boat.boatName);
            returnValue = returnValue && registerNr.equals(boat.registerNr);
            returnValue = returnValue && sailSign.equals(boat.sailSign);
            returnValue = returnValue && homePort.equals(boat.homePort);
            returnValue = returnValue && yachtclub.equals(boat.yachtclub);
            returnValue = returnValue && insurance.equals(boat.insurance);
            returnValue = returnValue && callSign.equals(boat.callSign);
            returnValue = returnValue && boatType.equals(boat.boatType);
            returnValue = returnValue && constructor.equals(boat.constructor);
            returnValue = returnValue && length.equals(boat.length);
            returnValue = returnValue && width.equals(boat.width);
            returnValue = returnValue && draft.equals(boat.draft);
            returnValue = returnValue && mastHeight.equals(boat.mastHeight);
            returnValue = returnValue && displacement.equals(boat.displacement);
            returnValue = returnValue && rigging.equals(boat.rigging);
            returnValue = returnValue && yearOfConstruction.equals(boat.yearOfConstruction);
            returnValue = returnValue && motor.equals(boat.motor);
            returnValue = returnValue && tankSize.equals(boat.tankSize);
            returnValue = returnValue && wasteWaterTankSize.equals(boat.wasteWaterTankSize);
            returnValue = returnValue && freshWaterTankSize.equals(boat.freshWaterTankSize);
            returnValue = returnValue && mainSailSize.equals(boat.mainSailSize);
            returnValue = returnValue && genuaSize.equals(boat.genuaSize);
            returnValue = returnValue && spiSize.equals(boat.spiSize);
            returnValue = returnValue && boatOwner.equals(boat.boatOwner);
            return returnValue;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
