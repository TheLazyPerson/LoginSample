package com.bitwise.training.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String errorMessage;
    public static final String errorWrongUsername = "1";
    public static final String serverValidationError = "2";
    public static final String errorInternalError = "3";
    public static final String errorInvalidSession = "4";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        errorMessage = null;
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//setting gui
		
		String error = request.getParameter("error");
		setErrorMessage(error);
		setupGui(response);
	
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void setupGui(HttpServletResponse response) throws IOException {
		
		
		String titleOfPage = "Login Sample";
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
		
		String css = "body{padding-top:40px;padding-bottom:40px;background-color:#eee}.form-signin{max-width:330px;padding:15px;margin:0 auto}.form-signin .checkbox,.form-signin .form-signin-heading{margin-bottom:10px}.form-signin .checkbox{font-weight:400}.form-signin .form-control{position:relative;height:auto;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;padding:10px;font-size:16px}.form-signin .form-control:focus{z-index:2}.form-signin input[type=email]{margin-bottom:-1px;border-bottom-right-radius:0;border-bottom-left-radius:0}.form-signin input[type=password]{margin-bottom:10px;border-top-left-radius:0;border-top-right-radius:0}";
		
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

					//out.println("<form class=\"form-signin\" action=\"/LoginSample/LoginServlet\" method=\"post\">");
					out.println("<form class=\"form-signin\" action=\"/LoginSample/IndexPage\" method=\"post\">");
					
						if (errorMessage != null) {
							out.println("<div class=\"alert alert-danger\" role=\"alert\"> "+errorMessage+" </div>");
						}
						
						out.println("<h2 class=\"form-signin-heading\">Please sign in</h2>");
				        out.println("<label for=\"inputEmail\" class=\"sr-only\">Email address</label>");
				        out.println("<input type=\"email\" name=\"username\" id=\"inputEmail\" class=\"form-control\" placeholder=\"Email address\" required autofocus>");
				        out.println("<label for=\"inputPassword\" class=\"sr-only\">Password</label>");
				        out.println("<input type=\"password\" name=\"password\" id=\"inputPassword\" class=\"form-control\" placeholder=\"Password\" required>");
				        out.println("<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Sign in</button>");
				        out.println("</form>");
			        
			     out.println("</div> <!-- /container -->");
		
			    
			
				out.println(jqueryUrl);
				out.println(bootstrapJsUrl);
			out.println("</body>");
		out.println("</html>");
	}
	
	private void setErrorMessage(String error) {
		if (error != null) {
			
			if (error.equals(errorWrongUsername)) {
				errorMessage = "Invalid Username or Password! Please try again.";
			}
			if (error.equals(serverValidationError)) {
				errorMessage = "Please try again.";
			}
			if (error.equals(errorInternalError)) {
				errorMessage = "Internal Server Error! Please try Again.";
			}
			if (error.equals(errorInvalidSession)) {
				errorMessage = "Invalid Session! Please try Again.";
			}
			
		}
	}
}