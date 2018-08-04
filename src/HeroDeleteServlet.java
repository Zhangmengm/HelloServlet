import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HeroDao;

public class HeroDeleteServlet extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		new HeroDao().deleteHero(id);
		response.sendRedirect("/listHero");
	}
}
