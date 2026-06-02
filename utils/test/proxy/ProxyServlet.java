package sp.utils.test.proxy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpHeaders;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProxyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static List<Route> routes=null;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		String requestPath = req.getPathInfo();
		String requestURI = req.getRequestURI();
		String servletPath = req.getServletPath();
		String queryString = req.getQueryString();
		String contextPath = req.getContextPath();
//		
//		System.out.println("requestPath:" + requestPath);
//		System.out.println("requestURI:" + requestURI);
//		System.out.println("servletPath:" + servletPath);
//		System.out.println("queryString:" + queryString);
//		System.out.println("contextPath:" + contextPath);
//		System.out.println("========");
		
		if(routes==null) {
			routes = RunManager.routes;
		}
		
		String proxyurl = null;
		
//		System.out.println("ttt:" +routes.size());
//		System.out.println("ttt:" +routes.get(0).getPathPrefix());
		
		for (Route r: routes) {
//			if(r.getPathPrefix().equals(requestPath)) {
//				proxyurl = r.getUrl();
//				break;
//			}
			
			if(requestPath.contains(r.getPathPrefix())) {
				proxyurl = r.getUrl();
				break;
			}
		}
		
//		if(proxyurl==null) {
//			proxyurl = "http://"+req.getServerName()+":"+req.getServerPort();
//		}
		
		
		StringBuffer proxyUrl = new StringBuffer();
		proxyUrl.append(proxyurl);
		proxyUrl.append(requestURI);
		if(servletPath!=null) proxyUrl.append(servletPath);
		if(queryString!=null) proxyUrl.append("?"+queryString);
		
//		System.out.println("###"+ proxyUrl.toString());
		
	    //header
		try {
			String result = getRequest(proxyUrl.toString());
			res.getWriter().write(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static String getBody(HttpServletRequest request) throws IOException {
		 
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
 
        body = stringBuilder.toString();
        return body;
    }
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String requestPath = req.getPathInfo();
		String requestURI = req.getRequestURI();
		
		if(routes==null) {
			routes = RunManager.routes;
		}
		
		String proxyurl = null;
		for (Route r: routes) {
			if(r.getPathPrefix().equals(requestPath)) {
				proxyurl = r.getUrl();
				break;
			}
		}
		
		
		StringBuffer proxyUrl = new StringBuffer();
		proxyUrl.append(proxyurl);
		proxyUrl.append(requestURI);
		
		String data = getBody(req);
	
		try {
			String result = postRequest(proxyUrl.toString(), data);
			res.getWriter().write(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
//		res.setStatus(200);
//		res.getWriter().write("Hello!");
	}
	
	private String postRequest(String URL, String DATA) throws Exception {
		URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setDoOutput(true);

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(DATA);
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null)  {
            stringBuffer.append(inputLine);
        }
        bufferedReader.close();

        String response = stringBuffer.toString();
        return response;
    }
	
	
	private String getRequest(String URL) throws Exception{

        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = connection.getResponseCode();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null)  {
            stringBuffer.append(inputLine);
        }
        bufferedReader.close();

        String response = stringBuffer.toString();
        return response;
    }
	
	

}
