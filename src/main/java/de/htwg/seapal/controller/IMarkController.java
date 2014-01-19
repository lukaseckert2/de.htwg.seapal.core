package de.htwg.seapal.controller;

import de.htwg.seapal.model.IMark;
import de.htwg.seapal.utils.observer.IObservable;

import java.util.List;
import java.util.UUID;

public interface IMarkController extends IObservable {

	/**
	 * Deletes a mark.
	 *
	 * @param id
	 *            The mark ID.
	 */
	void deleteMark(UUID id);

	/**
	 * Closes the database connection.
	 */
	void closeDB();

	/**
	 * Gets a mark by the given mark ID.
	 *
	 * @param markId
	 *            The mark ID.
	 * @return The mark or NULL, if no mark was found.
	 */
	IMark getMark(UUID markId);

	/**
	 * Gets all marks.
	 *
	 * @return All marks.
	 */
	List<IMark> getAllMarks();

	/**
	 * Saves the mark.
	 *
	 * @param mark
	 *            The mark to save.
	 * @return Returns TRUE, if the mark was newly created and FALSE when the
	 *         mark was updated.
	 */
	boolean saveMark(IMark mark);

    List<? extends IMark> queryView(String viewName, String key);
}
