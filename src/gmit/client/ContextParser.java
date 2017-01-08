/**
 * @author Andy
 *
 */
package gmit.client;


import javax.xml.parsers.*;
import org.w3c.dom.*;


public class ContextParser {
	private Context ctx;

	public ContextParser(Context ctx) {
		super();
		this.ctx = ctx;
	}
	
	

	// Method from XML example 
	public void init() throws Throwable{
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		//parse
		Document doc = db.parse(Context.CONF_FILE);
		
		//Get the root of the node tree
		Element root = doc.getDocumentElement();
		 //Get the child node of the root
		NodeList children = root.getChildNodes();
		//Get the attributes as a map
		NamedNodeMap atts = root.getAttributes();
		
		for(int j=0;j<atts.getLength();j++)
		{
			if(atts.item(j).getNodeName().equals("username")){
				ctx.setUsername(atts.item(0).getNodeValue());
			}
		}//end for
		
		//find the elements
		for(int i=0;i<children.getLength();i++){
			Node next = children.item(i);
			
			//Check if it is an element node
			if(next instanceof Element){
				Element e = (Element)next;
				
			if(e.getNodeName().equals("server-host")){
					ctx.setServerHost(e.getTextContent());
				}
			else if(e.getNodeName().equals("server-port")){
					ctx.setServerPort(e.getTextContent());
				}
			else if(e.getNodeName().equals("download-dir")){
					ctx.setDownload_dir(e.getTextContent());
				}
				
			}	//end if 
		} //end for 
	}// init 
	public Context getCtx() {
		return ctx;
	}

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}
}// end ContextParser