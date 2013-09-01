package com.gopivotal.cf.ce;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.gemstone.gemfire.tutorial.model.PostID;
import com.gemstone.gemfire.tutorial.model.Profile;
import com.gemstone.gemfire.tutorial.storage.GemfireDAO;

/**
 * Servlet implementation class MainClientServlet
 */
@WebServlet("/MainClientServlet")
public class MainClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GemfireDAO dao=null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	private String getCredentialValue(Map svcsMap, String name){
		
		ArrayList userProvidedSvcs = (ArrayList) svcsMap.get("user-provided");
		HashMap<String, Object> hm = (HashMap<String, Object>) userProvidedSvcs.get(0);
		HashMap<String, String> credentials = (HashMap<String, String>) hm.get("credentials");
		//userProvidedSvcs.get(0)
		return credentials.get(name);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (dao == null) {
			Map<String, String> env = System.getenv();
			String services = env.get("VCAP_SERVICES");
			ObjectMapper mapper = new ObjectMapper();
			Map svcsMap = mapper.readValue(services, Map.class);
			String locatorip = getCredentialValue(svcsMap, "locatorip");
			int locatorport = Integer.parseInt(getCredentialValue(svcsMap, "port"));
			dao = new GemfireDAO();
			dao.initClient(locatorip, locatorport);
		}
		
		String newPerson = request.getParameter("person");
		String newPost = request.getParameter("post");
		if (newPerson != null && newPerson != "") {
		      Profile profile = new Profile();

		      dao.addPerson(newPerson, profile);
		}
		
		if (newPost != null && newPost != "") {
			int spaceIndex = newPost.indexOf(' ');
			dao.addPost(newPost.substring(0, spaceIndex), newPost.substring(spaceIndex+1));
		}
			
	    Set<String> people = dao.getPeople();
	    Iterator<String> peopleit = people.iterator();
	    String peopleS = "";
	    while (peopleit.hasNext()) {
	    	peopleS = peopleS + peopleit.next() + "<br>";
	    }
	    
	    Set<PostID> posts = dao.getPosts();
	    String postsS = "";
	    Iterator<PostID> postsit = posts.iterator();
	    while (postsit.hasNext()) {
	    	PostID post = postsit.next();
	    	postsS = postsS + post.getAuthor() + ": " + dao.getPost(post) + "<br>";
	    }
	    
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	    request.setAttribute("people",	peopleS);
	    request.setAttribute("posts", postsS);
	    dispatcher.forward( request, response );
	}

}
