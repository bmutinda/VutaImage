package net.rebmos.vutaimage;

import android.util.Log;

/**
 * 
 * @author Mutinda Boniface
 *
 */
public class VutaLogger {

	// make false on production
	static final boolean debug = true;
	static final String LOG_INFO_TAG = "LOG_INFO_TAG";
	static final String LOG_ERROR_TAG = "LOG_ERROR_TAG";
	static final String LOG_DEBUG_TAG = "LOG_DEBUG_TAG";

	public VutaLogger() {
	}

	public static void logInfo(String message) {
		if (debug) {
			Log.i(VutaLogger.LOG_INFO_TAG, message);
		}
	}

	public static void logError(String error) {
		if (debug) {
			Log.e(VutaLogger.LOG_ERROR_TAG, error);
		}
	}

	public static void logDebug(String message) {
		if (debug) {
			Log.e(VutaLogger.LOG_DEBUG_TAG, message);
		}
	}

}
