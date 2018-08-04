import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import dao.HeroDao;

public class HeroListServlet extends HttpServlet {
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		List<Hero> list=new HeroDao().listHero(0, 10);
		StringBuffer sb=new StringBuffer();
		sb.append("<table align='cneter' border='1' cellspacing='1'>\r\n");
		sb.append("<tr><td>id</td><td>name</td><td>hp</td><td>damage</td><td>edit</td><td>delete</td></tr>\r\n");
		String format="<tr><td>%d</td><td>%s</td><td>%.3f</td><td>%d</td><td><a href='editHero?id=%d'>edit</td><td><a href='deleteHero?id=%d'>delete</td></tr>\r\n";
		for(Hero hero:list){
			String str=String.format(format, hero.getId(),hero.getName(),hero.getHp(),hero.getDamage(),hero.getId(),hero.getId());
			sb.append(str);
		}
		sb.append("</table>");
		response.getWriter().write(sb.toString());
	}
}
