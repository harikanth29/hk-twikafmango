package com.hk.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hk.db.PersonDB;
import com.hk.db.TweetDB;
import com.hk.db.impl.PersonDBImpl;
import com.hk.db.impl.TweetDbImpl;
import com.hk.pojo.Person;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/getTweet")
public class RestServiceImpl {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String sayPlainTextHello(@PathParam("id") String id) {
		TweetDB dbimpl = new TweetDbImpl();

		String result = dbimpl.getData(id);
		if (result == null) {
			result = "No record found";
		}
		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("allTweets")
	public String getAllTweets() {
		TweetDB dbimpl = new TweetDbImpl();
		StringBuffer strBuff = new StringBuffer();
		List<String> result = dbimpl.getAllTweet();
		for (String str : result) {
			strBuff.append(str);
		}

		return strBuff.toString();
	}

	@POST
	@Path("create/{name}/{company}/{mobile}")
	public void putPersonData(@PathParam("name") String name,
			@PathParam("company") String comapny,
			@PathParam("mobile") String mobile) {
		PersonDB db = new PersonDBImpl();
		String name1 = name;
		String com = comapny;
		String mobile1 = mobile;
		Person per = new Person();
		per.setComapny(com);
		per.setMobile(mobile1);
		per.setName(name1);
		db.createPerson(per);
//		db.putData(person);
	}

	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
				+ "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	}

}
