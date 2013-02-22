package com.exilesoft.exercise;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ShutdownHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.exilesoft.exercise.company.type.CompanyType;
import com.exilesoft.exercise.company.type.CompanyTypeRepository;


public class WebServer {

	private final Server server;
	private final WebAppContext webApplication;
	private String shutdownToken;
	private final int port;
	private final HandlerList handlers = new HandlerList();

	public WebServer(int port, String contextPath) {
		this.port = port;
		this.server = new Server(port);

		this.webApplication = new WebAppContext("src/main/webapp", contextPath);

		server.setHandler(handlers);
	}

	public void setDataSource(DataSource dataSource) throws NamingException {
	    new EnvEntry("java:/DefaultDS", dataSource);
	}

	private void setShutdownToken(String shutdownToken) {
		this.shutdownToken = shutdownToken;
	}

	public void start() throws Exception {
		if (shutdownToken != null) {
			handlers.addHandler(new ShutdownHandler(server, shutdownToken));
		}
		handlers.addHandler(webApplication);
		server.start();
	}

	public<T> T getBean(Class<T> beanClass) {
		return getAppContext().getBean(beanClass);
	}

	public String getWebAppUrl() {
		try {
			return new URL("http", "localhost", server.getConnectors()[0].getLocalPort(),
					webApplication.getContextPath()).toString();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public void setApplicationName(String applicationName) {
	    getBean(ApplicationInfo.class).setName(applicationName);
	}

	public List<CompanyType> getCompanyTypes() {
		return getBean(CompanyTypeRepository.class).list();
	}

	public void addCompanyTypes(String... types) {
	    CompanyTypeRepository typeRepository = getBean(CompanyTypeRepository.class);
	    for (String type : types) {
	        typeRepository.create(new CompanyType(type));
	    }
	}

	private ApplicationContext getAppContext() {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(webApplication.getServletContext());
	}

	public static void main(String[] args) throws Exception {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:file:target/db/addressbook");
        dataSource.setUser("sa");
		System.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");

		WebServer server = new WebServer(10080, "/");
		server.setShutdownToken("sdgsdlgnslndglnsgdsgsdg");
		server.attemptShutdown();
		server.setDataSource(dataSource);
		server.start();

		if(server.getCompanyTypes().isEmpty()) {
			server.addCompanyTypes("Software", "Offshoring");
		}

		System.out.println(server.getWebAppUrl());
	}

	private void attemptShutdown() throws IOException {
		URL url = new URL("http", "localhost", port, "/shutdown?token=" + shutdownToken);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		try {
			connection.getResponseCode();
		} catch (ConnectException e) {
			// OK: Not running
		}
	}


}
