package sp.utils.test.proxy;

import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;



public class ProxyServer {

	public void start(String host, int port, List<Route> routes) throws Exception {
		Server server = new Server();
		ServerConnector http = new ServerConnector(server);
		http.setHost(host);
		http.setPort(port);
		server.addConnector(http);
		
//		for(Route r: routes){
			ServletHandler servletHandler = new ServletHandler();
//			System.out.println("### Start Prefix : "+r.getPathPrefix());
//			ProxyServlet servlet = new ProxyServlet(routes);
			servletHandler.addServletWithMapping(ProxyServlet.class,"/*");
			server.setHandler(servletHandler);
//		}


		server.start();
		server.join();
	}
}
