package fitre;

import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connexion.Connexion;
import resto.Latabatra;
import resto.Produit;
import resto.Restaurant;

/**
 * Servlet Filter implementation class MainFilter
 */
@WebFilter({ "/MainFilter", "*" })
public class MainFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MainFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			Connection con = null;
			
			if (request.getServletContext().getAttribute("connection") == null) {
				Connexion connexion = new Connexion();
				con = connexion.getConnection();
				request.getServletContext().setAttribute("connection", con);
			}
			if (req.getSession().getAttribute("commandeEnCours") == null) {
				Vector<Produit> liste = new Vector<Produit>();
				req.getSession().setAttribute("commandeEnCours", liste);
			}
			
			// temporaire
			if (req.getSession().getAttribute("latabatra") == null) {
				req.getSession().setAttribute("latabatra", Latabatra.getLatabatraByID(1, con));
			}
			if (req.getSession().getAttribute("restaurant") == null) {
				req.getSession().setAttribute("restaurant", Restaurant.getRestaurantByID(1, con));
			}
			
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
