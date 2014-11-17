package ch.ingredientmatching.main;

import ch.ingredientmatching.lexer.Index;
import ch.ingredientmatching.matcher.MatchWord;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MatchingServlet extends HttpServlet
{
	private static final long serialVersionUID = -336259876537718L;

	private Index index;

	public MatchingServlet() throws IOException {
		index = new Index();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse respose) throws ServletException, IOException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestBody = request.getInputStream().toString();

		response.setCharacterEncoding("UTF-8");
		try {
			List<MatchWord> matchWords = IngredientMatching.runMatchingProcess(requestBody);
			if (matchWords.size() > 0) {
				response.getWriter().print("Matching found: " + System.lineSeparator());
				int eaternityId = matchWords.get(0).getId();
				response.getWriter().print(eaternityId + " " + index.getEaternityNameFromId(eaternityId));
				response.setStatus(200);
			}
			else {
				response.getWriter().print("No Matching found");
				response.setStatus(200);
			}

		} catch (Exception e) {
			response.getWriter().print(e.getMessage());
			response.sendError(500);
		}

	}
}