package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resto.Categorie;
import resto.Produit;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection con = (Connection) getServletContext().getAttribute("connection");
		
		Vector<Categorie> listeCategorie = Categorie.getCategorie(0, null, 0, (Connection) getServletContext().getAttribute("connection"));
		request.setAttribute("listeCategorie", listeCategorie);
		
		Vector<Object[]> listeProduitParCategorie = Produit.getAllProduitParCategorie(con);
		request.setAttribute("listeProduitParCategorie", listeProduitParCategorie);
		
		request.setAttribute("page", "accueil");
		getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
