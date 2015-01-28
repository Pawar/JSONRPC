package com.terzetto.serverrpc;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class RPCServer {
//	Logger log = null;
	Config conf = null;

	public RPCServer(Config conf) {
//		log = Logging.getLoger(RPCServer.class);
		this.conf = conf;
	}

	public RPCServer(String ip, String port) {
//		log = Logging.getLoger(RPCServer.class);
		this.conf = Config.getConfig(ip, port);
	}

	public void start() {
		System.out.println("Initializing configurations...");
		Server server = new Server();
		ServerConnector http = new ServerConnector(server);
		System.out.println("Server ip address: " + conf.getServerIP());
		http.setHost(conf.getServerIP());
		System.out.println("Server port address: " + conf.getServerPort());
		http.setPort(conf.getServerPort());
		http.setIdleTimeout(300000);
		server.addConnector(http);
		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		context.addServlet(RPC.class, "/RPC");
		try {
			System.out.println("Server is started: "
					+ new java.sql.Time(System.currentTimeMillis()));
			server.start();
			server.join();
		} catch (Exception e) {
	//		log.log(Level.WARN, e.getMessage(), e);
			e.printStackTrace();
		}
	}

	/**
	 * @param userclass is Name of the class to be add for RPC
	 * @param object is Name of the object to be used for client RPC to use
	 * @return true if instance of class is created, false otherwise
	 */

	@SuppressWarnings("unchecked")
	public <USERCLASS> boolean addClass(Class<USERCLASS> userclass,
			String object) {
		try {
			ClassLoader classloader = Thread.currentThread()
					.getContextClassLoader();
			@SuppressWarnings("rawtypes")
			Class userClass = classloader.loadClass(userclass.getName());
			UserClass.classname.put(object, userClass);
			UserClass.classobject.put(object, userclass.newInstance());			
			return true;
		} catch (Exception e) {
	//		log.log(Level.WARN, e.getMessage(), e);
			e.printStackTrace();
		}
		return false;
	}

}
