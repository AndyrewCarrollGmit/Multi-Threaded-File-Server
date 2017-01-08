package gmit.client;


import javax.xml.parsers.*;
import org.w3c.dom.*;

public class ContextParser {
	private Context ctx;

	public ContextParser(Context ctx) {
		super();
		this.ctx = ctx;
	}
	
	//get set ctx
	public Context getCtx() {
		return ctx;
	}

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}

	// method to parse an XML file into a DOM tree
	public void init() throws Throwable{
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		//get a new instance 
		DocumentBuilder db = dbf.newDocumentBuilder();
		//parse the file 
		Document doc = db.parse(Context.CONF_FILE);
		
		// returns the root element 
		Element root = doc.getDocumentElement();
		// gets childs node
		NodeList children = root.getChildNodes();
		
		
		//find the elements
		NamedNodeMap atts = root.getAttributes();
		
		for(int j=0;j<atts.getLength();j++)
		{
			if(atts.item(j).getNodeName().equals("username")){
				ctx.setUsername(atts.item(0).getNodeValue());
			}
		}//end for
		
		for(int i=0;i<children.getLength();i++){
			Node next = children.item(i);
			
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

}// end ContextParser