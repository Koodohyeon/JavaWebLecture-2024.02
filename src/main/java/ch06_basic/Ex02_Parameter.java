package ch06_basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// localhost:8080/jw/ch06/params?id=101&title=title
@WebServlet("/ch06/params")
public class Ex02_Parameter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String id_ = req.getParameter("id");
    	int id = Integer.parseInt(id_);
    	String title = req.getParameter("title");
    	System.out.println("id: " + id + ", title: " + title);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}