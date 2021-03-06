/**
 * @author Andy
 *
 */
package gmit.client;
//https://learnonline.gmit.ie/course/view.php?id=2346 -- Material on Moodle used to help with project
// A context represents the entire scope of an application, i.e.
public class Context {
	// xml file location
	public static final String CONF_FILE="src/conf.xml";
	// private variables
	private String username;
	private String serverHost;
	private String serverPort;
	private String download_dir;
	
	// Getters and Setters
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getServerHost() {
		return serverHost;
	}



	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}



	public String getServerPort() {
		return serverPort;
	}



	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}



	public String getDownload_dir() {
		return download_dir;
	}



	public void setDownload_dir(String download_dir) {
		this.download_dir = download_dir;
	}


	// OverRideMethod
	@Override
	public String toString() {
		return "Context [username=" + username + ", serverHost=" + serverHost + ", serverPort=" + serverPort + ", download_dir=" + download_dir + "]";
	}
}