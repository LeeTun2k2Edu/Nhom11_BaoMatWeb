package Control;

import DAO.LoaispDAO;
import DAO.SanPhamDAO;
import DAO.SignUpDAO;
import Model.Argon2;
import Model.LoaiSP;
import Model.MD5;
import Model.SanPham;
import Model.SendMail;
import Model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class SignUpControl
 */
@WebServlet("/signup")
public class SignUpControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<LoaiSP> listLSp = new LoaispDAO().getAllloaisp();
		request.setAttribute("listlSp", listLSp);
		List<SanPham> listChuaRaMat = new SanPhamDAO().chuaRaMat();
		request.setAttribute("chuaramat", listChuaRaMat);
		request.getRequestDispatcher("/shop-cart/signUp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		List<LoaiSP> listLSp = new LoaispDAO().getAllloaisp();
		request.setAttribute("listlSp", listLSp);
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		Argon2 argon2 = new Argon2();
		String passArgon2 = argon2.argon2(password);
		String repasssArgon2 = argon2.argon2(repassword);

		System.out.println(passArgon2);
		System.out.println(repasssArgon2);
		
		MD5 lib = new MD5(); 
		String passMD5 = lib.md5(password); 
		String repassMD5 =  lib.md5(repassword);
		System.out.println(passMD5);
		System.out.println(repassMD5);

		SignUpDAO dao = new SignUpDAO();
		Users a = dao.CheckUserExist(username);
		if (a == null) {

			int veri = dao.getRandom();

			HttpSession session = request.getSession();
			session.setAttribute("fullname", fullname);
			session.setAttribute("username", username);
			session.setAttribute("email", email);
			session.setAttribute("phone", phone);
			session.setAttribute("password", passArgon2);
			session.setAttribute("repassword", passArgon2);
			session.setAttribute("verify", veri);

			SendMail sm = new SendMail();
			Boolean test = sm.sendMail(email, veri, fullname);

			if (test == false) {
				request.setAttribute("mess1", "Email không chính xác");
				request.getRequestDispatcher("/shop-cart/signUp.jsp").forward(request, response);
			} else {
				response.sendRedirect("verify");
			}
		} else {
			request.setAttribute("mess1", "Tài khoản đã tồn tại");
			request.getRequestDispatcher("/shop-cart/signUp.jsp").forward(request, response);
		}
	}

}
