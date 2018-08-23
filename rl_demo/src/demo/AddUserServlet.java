package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		response.setContentType("text/html;charset = GBK");
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		//String role = request.getParameter("role");
		Date curTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String regtime = sdf.format(curTime);
		PrintWriter out = response.getWriter();
		ServletContext ctx = this.getServletContext();
		String server = ctx.getInitParameter("server");
		String dbname = ctx.getInitParameter("dbname");
		String dbuser = ctx.getInitParameter("dbuser");
		String dbpwd = ctx.getInitParameter("dbpwd");
		User user = new User();
		user.setUsername(username);
		user.setUserpass(userpass);
		user.setRegtime(regtime);
		//user.setRole(Integer.parseInt(role));
		UserDao dao = new UserDao();
		dao.getConn(server, dbname, dbuser, dbpwd);
		if(dao.addUser(user)){
			out.print("×¢²á³É¹¦£¡");
			out.print("<br><a href = 'login.jsp'>È¥µÇÂ½</a>");
		}
		else{
			out.print("×¢²áÊ§°Ü£¡");
		}
		
		
	}

}
