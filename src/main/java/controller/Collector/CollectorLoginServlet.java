//package controller.Collector;
//
//import model.AdminModel;
//import model.CollectorAccessModel;
//import model.CollectorModel;
//import model.exception.NotFoundException;
//import am.basic.springdemo.repository.CollectorAccessRepository;
//import am.basic.springdemo.repository.impl.CollectorAccessRepositoryImpl;
//import am.basic.springdemo.repository.impl.CollectorRepositoryImpl;
//import am.basic.springdemo.service.CollectorService;
//import am.basic.springdemo.service.impl.CollectorServiceImpl;
//import util.CookieUtil;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/login/collector")
//public class CollectorLoginServlet extends HttpServlet {
//    private final CollectorService collectorService = new CollectorServiceImpl(new CollectorRepositoryImpl());
//    protected void am.basic.springdemo.service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("collector-login");
//        String password = req.getParameter("collector-password");
//
//        try {
//            CollectorModel collectorModel = collectorService.signIn(username, password);
//
//            System.out.println(collectorModel);
//
//            CookieUtil.setCookieValue("username", username, 5000000, resp);
//            CookieUtil.setCookieValue("password", password, 5000000, resp);
//
//            req.getSession().setAttribute("collector",collectorModel);
//            req.getSession().setMaxInactiveInterval(500000);
//
//            resp.sendRedirect("/collector/home");
//
//        } catch (NotFoundException notFoundException) {
//            req.setAttribute("ErrorMessage", notFoundException.getMessage());
//            req.getRequestDispatcher("/pages/collector/login-collector.jsp").forward(req, resp);
//        }
//
//    }
//}
