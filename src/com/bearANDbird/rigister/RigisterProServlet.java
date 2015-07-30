package com.bearANDbird.rigister;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

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

		//		String phone = request.getParameter("phone");
		//		String passwd = request.getParameter("passwd");
		//		String nickname = request.getParameter("nickname");
		//		String sex = request.getParameter("sex");
		//		String place = request.getParameter("place");
		//		String birthday = request.getParameter("birthday");
		DbDao db = new DbDao();
		BufferedReader br = request.getReader();
		StringBuilder sb = new StringBuilder();
		String tempStr;
		while((tempStr = br.readLine()) != null ){
			sb.append(tempStr);
		}	
		JSONObject jsonObj;
		String phone;
		String passwd; 
		String nickname;
		String sex;
		String place;
		String birthday;
		JSONObject tempJsonObject = new JSONObject();
		try {
			jsonObj= new JSONObject(sb.toString());  
			phone = (String)jsonObj.get("phone");
			passwd = (String)jsonObj.get("passwd");
			nickname = (String)jsonObj.get("nickname");
			sex = (String)jsonObj.get("sex");
			place = (String)jsonObj.get("place");
			birthday = (String)jsonObj.get("birthday");
			ResultSet rs = db.query("select phone from user_rigisted where phone = ?", new Object[]{phone});
			if(rs.next()){
				tempJsonObject.put("result", "-1");
			}else{
				if(db.insert("insert into user_rigisted values(?,?,?,?,?,?)", new Object[]{phone,passwd,nickname,sex,place,birthday})){
					tempJsonObject.put("result", "0");
					File homeDir = new File(this.getServletContext().getRealPath("/")+"/WEB-INF/userhome/"+phone);
					if(!homeDir.exists()){
						homeDir.mkdirs();
					}
				}else{
					tempJsonObject.put("result", "-2");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("gin:exception:other exception");
			try {
				tempJsonObject.put("result", "-3");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		response.getWriter().println(tempJsonObject.toString());

	}


}
