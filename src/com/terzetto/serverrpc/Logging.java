package com.terzetto.serverrpc;

import org.apache.log4j.*;

public class Logging {
	static String logFile = (new java.util.Date().getTime()) + "";
	public static <LOGCLASS> Logger getLoger(Class<LOGCLASS> Class) {		
		Logger log = LogManager.getLogger(Class);
		try {			
			BasicConfigurator.configure();
			Logger.getRootLogger().setLevel(Level.ALL);
			FileAppender fa = new RollingFileAppender(new PatternLayout(
					"[%d{dd-MM-yyyy HH:mm:ss}] [%-5p] %C.%t(%L) : %m%n"), logFile + ".log");
			log.addAppender(fa);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return log;
	}
}