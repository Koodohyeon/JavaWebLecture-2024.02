package ch07_dao.kcity;

import jakarta.servlet.RequestDispatcher;	
import jakarta.servlet.ServletException;	
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ch07_dao.City;
import ch07_dao.CityDao;
	
// ("/ch07/kcity/*")	이렇게 모든 것을 포함하는거는 이런식으로 작성해도 된다.
@WebServlet({"/ch07/kcity/list", "/ch07/kcity/insert", "/ch07/kcity/update", "/ch07/kcity/delete" , "/ch07/kcity/wrong"})
public class KcityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CityDao cDao = new CityDao();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		String[] uri = requestUri.split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		RequestDispatcher rd = null;
		String name = null , district = null , countryCode = null , population_ = null;
		int population = 0, id = 0;
		City city = null;
		
		switch(action) {
		case "list":
			district = request.getParameter("district");
			district = (district == null || district.equals("")) ? "Kyonggi" : district;
			String num_ =  request.getParameter("num");
			int num = (num_ == null || num_.equals("")) ? 30 : Integer.parseInt(num_);
			String offset_ =  request.getParameter("offset");
			int offset = (offset_ == null || offset_.equals("")) ? 0 : Integer.parseInt(offset_);
			
			List<City> list = cDao.getCityList(district, num, offset);
			rd = request.getRequestDispatcher("/ch07/kcity/list.jsp");
			request.setAttribute("list", list); 
			rd.forward(request, response);
			break;
			
		case "insert":
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/ch07/kcity/insert.jsp");
				String[] districts ="Cheju,Chollabuk,Chollanam,Chungchongbuk,Chungchongnam,Inchon,Kang-won,Kwangju,Kyonggi,Kyongsangbuk,Kyongsangnam,Pusan,Seoul,Taegu,Taejon".split(",");
				request.setAttribute("district", districts);
				rd.forward(request, response);
				
			} else {
				name = request.getParameter("name");
				countryCode = request.getParameter("countryCode");
				district = request.getParameter("district");
				population_ = request.getParameter("population");
				population = (population_.equals("")) ? 0 : Integer.parseInt(population_);
				
				city = new City(name, countryCode, district, population);
				cDao.insertCity(city);
				
				response.sendRedirect("/jw/ch07/kcity/list?district=" + district + "&num=30&offset=0");
			}
			break;
			
		case "update":
			if (method.equals("GET")) {
				id = Integer.parseInt(request.getParameter("id"));
				city = cDao.getCity(id);
				rd = request.getRequestDispatcher("/ch07/kcity/update.jsp");
				request.setAttribute("city", city);
				request.setAttribute("district", district);
				rd.forward(request, response);
			} else {
				id = Integer.parseInt(request.getParameter("id"));
				name = request.getParameter("name");
				countryCode = request.getParameter("countryCode");
				district = request.getParameter("district");
				population_ = request.getParameter("population");
				population = (population_.equals("")) ? 0 : Integer.parseInt(population_);
				city = new City(id, name, countryCode, district, population);
				cDao.updateCity(city);
				response.sendRedirect("/jw/ch07/kcity/list?district=" + district + "&num=30&offset=0");
			}
			break;
			
		case "delete":
			id = Integer.parseInt(request.getParameter("id"));
			cDao.deleteCity(id);
			response.sendRedirect("/jw/ch07/kcity/list?district=Kyonggi&num=30&offset=0");
			break;
			
		default: 
			rd = request.getRequestDispatcher("/ch07/kcity/alertMsg.jsp");
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("url", "/jw/ch07/kcity/list");
			rd.forward(request, response);
		}
		
		PrintWriter out = response.getWriter();
		out.print(requestUri);
	}



}
