package dev.sgp.filtre;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import dev.sgp.entite.VisiteWeb;
import dev.sgp.service.VisiteService;
import dev.sgp.util.Constantes;

public class FrequentationFilter implements Filter {
	
	private FilterConfig config = null;
	private final VisiteService visites = Constantes.VISITE_SERVICE;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getRequestURI();
		if(path.contains("/bootstrap-") || path.contains("/resources/")){
			return;
		}
		long before = System.currentTimeMillis();
		chain.doFilter(request, response);
		long after = System.currentTimeMillis();
		int delay = (int) (after - before);
		visites.sauvegarderVisite(new VisiteWeb(visites.getId(), path, delay));
	}

	@Override
	public void destroy() {
	}

}
