package com.bitwise.training.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitwise.training.login.LoginServlet;

/**
 * Servlet implementation class IndexPage
 */
//@WebServlet("/IndexPage")
public class IndexPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static HashMap<String, Person> peopleDatbase= new HashMap<>();
    private String username;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexPage() {
        super();
        username = null;
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null ) {
			if (request.getParameter("username") == null) {
				response.sendRedirect("/LoginSample/LoginServlet?error="+ LoginServlet.errorInternalError);
			}
			
			getServletContext().setAttribute("username", request.getParameter("username"));
			username = (String) getServletContext().getAttribute("username");
			
			if (peopleDatbase.get(username) != null) {
				System.out.println(peopleDatbase.get(username).getName());
				response.sendRedirect("/LoginSample/HandleData");
			}
			setupGui(response);
		}else{
			System.out.println("Invalid Session");
			response.sendRedirect("/LoginSample/LoginServlet?error="+ LoginServlet.errorInvalidSession);
		}
		//doGet(request, response);
	}
	
	
	//used to setup gui
	private void setupGui(HttpServletResponse response) throws IOException {
		
		
		String titleOfPage = "Welcome!";
		String bootstrapUrl = "<link rel=\"stylesheet\" "
				+ "href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/"
				+ "css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32Om"
				+ "Ucww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" "
				+ "crossorigin=\"anonymous\">";
		String jqueryUrl = "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/"
				+ "jquery.min.js\"></script>";
		String bootstrapJsUrl = "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/"
				+ "js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJ"
				+ "A7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>";
		String addIe8AndIe9Support = "<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->"
				+ "<!--[if lt IE 9]><script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\">"
				+ "</script><script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>"
				+ "<![endif]-->";
		
		String css = "body{padding-top:40px;padding-bottom:40px;background-color:#eee}.form-signin{max-width:330px;padding:15px;margin:0 auto}.form-signin .checkbox,.form-signin .form-signin-heading{margin-bottom:10px}.form-signin .checkbox{font-weight:400}.form-signin .form-control{position:relative;height:auto;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;padding:10px;font-size:16px}.form-signin .form-control:focus{z-index:2}.form-signin input[type=email]{margin-bottom:-1px;border-bottom-right-radius:0;border-bottom-left-radius:0}.form-signin input[type=password]{margin-bottom:10px;border-top-left-radius:0;border-top-right-radius:0}.top{border-bottom-right-radius:0;border-bottom-left-radius:0}.mid{border-radius:0}.bottom{margin-bottom:10px;border-top-left-radius:0;border-top-right-radius:0}";
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
			out.println("<head>");
				out.println("<meta charset=\"utf-8\">");
				out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
				out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
			    
				out.println("<title>"+titleOfPage+"</title>");
				out.println(bootstrapUrl);
				out.println(addIe8AndIe9Support);
				out.println("<style>" + css + "</style>");
				
				
			out.println("</head>");
			out.println("<body>");
				out.println("<div class=\"container\">");
					out.println("<form class=\"form-signin\" action=\"/LoginSample/HandleData\" method=\"post\">");
				
						out.println("<h4 class=\"form-signin-heading\">Welcome "+ username +"</h4>");
						out.println("<button class=\"btn btn-lg btn-primary \" name=\"logout\" type=\"submit\">Logout</button>");    
					out.println("</form>");    
					//out.println("<form class=\"form-signin\" action=\"/LoginSample/IndexPage\" method=\"post\">");
					out.println("<form class=\"form-signin\" action=\"/LoginSample/HandleData\" method=\"post\">");
					
						out.println("<label for=\"name\" class=\"sr-only\">Name</label>");
				        out.println("<input type=\"text\" name=\"name\" id=\"name\" class=\"form-control top\" placeholder=\"Name\" required autofocus>");
				        out.println("<label for=\"phone\" class=\"sr-only\">Phone No:</label>");
				        out.println("<input type=\"number\" name=\"phone\" id=\"phone\" class=\"form-control mid\" placeholder=\"Phone No\" required>");
				        out.println("<label for=\"company\" class=\"sr-only\">Company</label>");
				        out.println("<input type=\"text\" name=\"company\" id=\"company\" class=\"form-control mid\" placeholder=\"Company\" required >");
				        out.println("<label for=\"position\" class=\"sr-only\">Position:</label>");
				        out.println("<input type=\"text\" name=\"position\" id=\"position\" class=\"form-control bottom\" placeholder=\"Position\" required>");
				        
				        out.println("<button class=\"btn btn-lg btn-primary btn-block\" name=\"createProfile\" type=\"submit\">Create Your Profile</button>");
				        out.println("</form>");
			        
			     out.println("</div> <!-- /container -->");
		
			    
			
				out.println(jqueryUrl);
				out.println(bootstrapJsUrl);
			out.println("</body>");
		out.println("</html>");
	}
    
	
}
