package com.bearANDbird.login;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearANDbird.dto.DbDao;

/**
 * Servlet implementation class LoginProServlet
 */
@WebServlet(name="loginProServlet"
		,urlPatterns={"/loginPro"})
public class LoginProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginProServlet() {
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
		try{
			DbDao db = new DbDao();
			String username = request.getParameter("username");
			String passwd = request.getParameter("passwd");
			ResultSet rs = db.query("select pass from user_table where name = ?"
									,new String[]{username});
			if(rs.next()){
				if(rs.getString(1).equals(passwd)){
					request.getRequestDispatcher("/welcome.jsp").forward(request, response);
				}else{
					request.setAttribute("errMsg", "wrong passwd");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("errMsg", "wrong username");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
