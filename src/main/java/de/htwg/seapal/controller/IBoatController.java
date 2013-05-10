package de.htwg.seapal.controller;

import java.util.List;
import java.util.UUID;

import de.htwg.seapal.model.IBoat;
import de.htwg.seapal.utils.observer.IObservable;

/**
 * The boat controller interface.
 */
public interface IBoatController extends IObservable {

	/**
	 * Gets the boat name of the given ID.
	 * @param id The boat ID.
	 * @return The boat name.
	 */
	String getBoatName(UUID id);

	/**
	 * Sets the boat name of the given boat ID.
	 * @param id The boat ID.
	 * @param boatName The boat name.
	 */
	void setBoatName(UUID id, String boatName);

	/**
	 * Gets the register number of the given boat ID.
	 * @param id The boat ID.
	 * @return The register number.
	 */
	String getRegisterNr(UUID id);

	/**
	 * Sets the register number of the given boat ID.
	 * @param id
	 * @param registerNr
	 */
	void setRegisterNr(UUID id, String registerNr);

	String getSailSign(UUID id);

	void setSailSign(UUID id, String SailSign);

	String getHomePort(UUID id);

	void setHomePort(UUID id, String HomePort);

	String getYachtclub(UUID id);

	void setYachtclub(UUID id, String yachtclub);

	UUID getOwner(UUID id);

	void setOwner(UUID id, UUID Owner);

	String getInsurance(UUID id);

	void setInsurance(UUID id, String Insurance);

	String getCallSign(UUID id);

	void setCallSign(UUID id, String CallSign);

	String getType(UUID id);

	void setType(UUID id, String Type);

	String getConstructor(UUID id);

	void setConstructor(UUID id, String Constructor);

	double getLength(UUID id);

	void setLength(UUID id, double Length);

	double getWidth(UUID id);

	void setWidth(UUID id, double width);

	double getDraft(UUID id);

	void setDraft(UUID id, double draft);

	double getMastHeight(UUID id);

	void setMastHeight(UUID id, double mastHeight);

	double getDisplacement(UUID id);

	void setDisplacement(UUID id, double displacement);

	String getRigging(UUID id);

	void setRigging(UUID id, String rigging);

	int getYearOfConstruction(UUID id);

	void setYearOfConstruction(UUID id, int yearOfConstruction);

	String getMotor(UUID id);

	void setMotor(UUID id, String motor);

	double getTankSize(UUID id);

	void setTankSize(UUID id, double tankSize);

	double getWasteWaterTankSize(UUID id);

	void setWasteWaterTankSize(UUID id, double wasteWaterTankSize);

	double getFreshWaterTankSize(UUID id);

	void setFreshWaterTankSize(UUID id, double freshWaterTankSize);

	double getMainSailSize(UUID id);

	void setMainSailSize(UUID id, double mainSailSize);

	double getGenuaSize(UUID id);

	void setGenuaSize(UUID id, double genuaSize);

	double getSpiSize(UUID id);

	void setSpiSize(UUID id, double spiSize);

	String getString(UUID id);

	UUID newBoat();

	void deleteBoat(UUID id);

	void closeDB();

	List<UUID> getBoats();
	
	List<IBoat> getAllBoats();
	
	/**
	 * Saves the boat.
	 * @param boat The boat to save.
	 * @return Returns TRUE, if the boat was newly created
	 * 	       and FALSE when the boat was updated.
	 */
	boolean saveBoat(IBoat boat);
}
