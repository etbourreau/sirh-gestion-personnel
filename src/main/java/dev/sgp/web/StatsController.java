package dev.sgp.web;

import java.io.IOException;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.VisiteWeb;
import dev.sgp.service.VisiteService;

@WebServlet("/stats")
public class StatsController extends HttpServlet {

	@Inject private VisiteService visiteService;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, IntSummaryStatistics> visites = visiteService.listerVisites().stream().collect(
				Collectors.groupingBy(VisiteWeb::getChemin, Collectors.summarizingInt(VisiteWeb::getTempsExecution)));
		req.setAttribute("listeVisites", visites);
		req.getRequestDispatcher("/WEB-INF/views/stats.jsp").forward(req, resp);
	}
}
