package dev.sgp.filtre;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import dev.sgp.entite.VisiteWeb;
import dev.sgp.service.VisiteService;

@WebFilter("/*")
public class FrequentationFilter implements Filter {
	
	@Inject private VisiteService visites;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//Initializing the filter
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getRequestURI();
		long before = System.currentTimeMillis();
		chain.doFilter(request, response);
		long after = System.currentTimeMillis();
		int delay = (int) (after - before);
		if(path.contains("/res/") || path.contains("/assets/") || path.contains("/api/")){
			return;
		}
		visites.sauvegarderVisite(new VisiteWeb(visites.getId(), path, delay));
	}

	@Override
	public void destroy() {
		//Autodestroying
	}

}
