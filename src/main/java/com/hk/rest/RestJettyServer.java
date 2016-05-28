package com.hk.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

public class RestJettyServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");

//		For Spring context to load
//		context.addEventListener(new ContextLoaderListener());
//		context.setInitParameter("contextConfigLocation", "classpath*:**/testContext.xml");

//		ResourceHandler resHandler= new ResourceHandler();
//		resHandler.setBaseResource(Resource.newClassPathResource("/"));
		
		Server jettyServer = new Server(8081);
		jettyServer.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		
		jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.hk.rest");
		
//		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", Calculator.class.getCanonicalName(),Hello.class.getCanonicalName());
//		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", Hello.class.getCanonicalName());

		try {
			jettyServer.start();
			jettyServer.join();
		} finally {
			jettyServer.destroy();
		}
	}

//	public void loadSpringContext(){
//		
//		Server server = new Server(8090);
//
//		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//		context.setContextPath("/batch");
//
//		// Setup Spring context
//		context.addEventListener(new ContextLoaderListener());
////		context.setInitParameter("contextConfigLocation", "classpath*:**/testContext.xml");
//
//		server.setHandler(context);
//
//		// Add servlets
//		context.addServlet(new ServletHolder(new BatchReceiver()), "/receiver/*");
//		context.addServlet(new ServletHolder(new BatchSender()), "/sender/*");       
//
//		server.start();
//		server.join();
//	}
}
