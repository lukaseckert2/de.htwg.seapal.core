package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IMarkController;
import de.htwg.seapal.database.IMarkDatabase;
import de.htwg.seapal.model.IMark;
import de.htwg.seapal.utils.logging.ILogger;
import de.htwg.seapal.utils.observer.Observable;

import java.util.List;
import java.util.UUID;

public class MarkController extends Observable implements IMarkController {

	private IMarkDatabase db;
	private final ILogger logger;

	@Inject
	public MarkController(IMarkDatabase db, ILogger logger) {
		this.db = db;
		this.logger = logger;
	}

	@Override
	public void deleteMark(UUID id) {
		db.delete(id);
		notifyObservers();
	}

	@Override
	public final void closeDB() {
		db.close();
		logger.info("MarkController", "Database closed");
	}

	@Override
	public IMark getMark(UUID markId) {
		return db.get(markId);
	}

	@Override
	public List<IMark> getAllMarks() {
		return db.loadAll();
	}

	@Override
	public boolean saveMark(IMark mark) {
		return db.save(mark);
	}
    @Override
    public List<? extends IMark> queryView(final String viewName, final String key) {
        return db.queryViews(viewName, key);
    }
}
