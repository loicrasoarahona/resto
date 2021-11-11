package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Vector;
import resto.Latabatra;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resto.Produit;

/**
 * Servlet implementation class Ajout
 */
@WebServlet("/ajout")
public class Ajout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ajout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Boolean lounge = ((Latabatra) request.getSession().getAttribute("latabatra")).getLounge();
		try {
			Vector<Produit> commande = (Vector<Produit>) request.getSession().getAttribute("commandeEnCours");
			int produit = Integer.parseInt(request.getParameter("food"));
			Produit p = Produit.getProduitByID(produit, (Connection) getServletContext().getAttribute("connection"));
			commande.add(p);
			out.println(p.getNom()+"&"+(lounge?p.getPrixLounge():p.getPrix()));
		} catch (Exception e) {
			out.println("0");
		}
		
		doGet(request, response);
	}

}
