package com.bearANDbird.rigister;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearANDbird.dto.DbDao;

/**
 * Servlet implementation class RigisterProServlet
 */
@WebServlet(name="rigisterProServlet",urlPatterns={"/rigisterPro"})
public class RigisterProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RigisterProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String place = request.getParameter("place");
		System.out.println(username+":"+passwd+":"+phone+":"+sex+":"+place);
		response.setStatus(200);
		response.getWriter().println("received");
		
//		DbDao db = new DbDao();
//		ResultSet rs = (ResultSet)db.query("select * from user_rigisted where phone = ?", new String[]{phone});
//		if(rs.next()){
//			
//		}
		
	}

}
