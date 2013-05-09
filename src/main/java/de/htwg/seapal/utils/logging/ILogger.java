/**
 * 
 */
package de.htwg.seapal.utils.logging;

/**
 * Logging interface to use the same logging mechanism
 * in Web and on Android.
 * @author Benjamin
 */
public interface ILogger {
	void info(String tag, String msg);
	void warn(String tag, String msg);
	void error(String tag, String msg);
}
