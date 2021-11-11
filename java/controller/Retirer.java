package controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resto.Produit;

/**
 * Servlet implementation class Retirer
 */
@WebServlet("/retirer")
public class Retirer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Retirer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int index = Integer.parseInt(request.getParameter("index"));
			Vector<Produit> commandeEnCours = (Vector<Produit>) request.getSession().getAttribute("commandeEnCours");
			commandeEnCours.remove(index);
			response.getWriter().println("oui");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		doGet(request, response);
	}

}
