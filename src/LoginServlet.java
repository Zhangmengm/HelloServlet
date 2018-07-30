import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	public LoginServlet() {
        System.out.println("LoginServlet 构造方法 被调用");
    }
 
    public void init(ServletConfig config) {
        System.out.println("init(ServletConfig)");
    }
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		System.out.println("name:"+name);
		System.out.println("passworld:"+password);
		String html=null;
		if("zhangmeng".equals(name) && "123456".equals(password)){
			request.getRequestDispatcher("success.html").forward(request, response);
		}else{
			response.sendRedirect("fail.html");
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println(html);
	}
}
