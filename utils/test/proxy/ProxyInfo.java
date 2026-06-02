package sp.utils.test.proxy;

import java.util.List;

public class ProxyInfo{
	int port;
	List<Route> routes;
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
	
	public String toString() {
		return "port: "+ this.port ;
	}

	
}



class Route{
	String pathPrefix;
	String url;
	
	
	public Route(String pathPrefix, String url) {
		super();
		this.pathPrefix = pathPrefix;
		this.url = url;
	}
	public String getPathPrefix() {
		return pathPrefix;
	}
	public void setPathPrefix(String pathPrefix) {
		this.pathPrefix = pathPrefix;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}