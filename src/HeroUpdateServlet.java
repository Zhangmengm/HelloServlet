import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import dao.HeroDao;

public class HeroUpdateServlet extends HttpServlet {
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		Hero hero=new Hero();
		hero.setId(Integer.parseInt(request.getParameter("id")));
		hero.setName(request.getParameter("name"));
		hero.setHp(Float.parseFloat(request.getParameter("hp")));
		hero.setDamage(Integer.parseInt(request.getParameter("damage")));
		new HeroDao().updateHero(hero);
		response.sendRedirect("/listHero");
	}
}
