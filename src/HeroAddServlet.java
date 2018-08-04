import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import dao.HeroDao;

public class HeroAddServlet extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		Hero hero=new Hero();
		hero.setName(request.getParameter("name"));
		hero.setHp(Float.parseFloat(request.getParameter("hp")));
		hero.setDamage(Integer.parseInt(request.getParameter("damage")));
		new HeroDao().addHero(hero);
		response.sendRedirect("/listHero");
		
	}
}
