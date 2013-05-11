package de.htwg.seapal.utils.logging.mock;

import de.htwg.seapal.utils.logging.ILogger;

/**
 * Silent logger implementation, which can be used if
 * no logging is required.
 * @author Benjamin
 */
public class NullLogger implements ILogger {

	@Override
	public void info(String tag, String msg) {

	}

	@Override
	public void warn(String tag, String msg) {

	}

	@Override
	public void error(String tag, String msg) {

	}

}
