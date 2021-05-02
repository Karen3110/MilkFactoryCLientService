//package controller.Collector;
//
//import am.basic.springdemo.repository.impl.AdminRepositoryImpl;
//import am.basic.springdemo.repository.impl.CollectorRepositoryImpl;
//import am.basic.springdemo.service.AdminService;
//import am.basic.springdemo.service.CollectorService;
//import am.basic.springdemo.service.impl.AdminServiceImpl;
//import am.basic.springdemo.service.impl.CollectorServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/collector/logout")
//
//public class CollectorLogOutServlet extends HttpServlet {
//
//    private final CollectorService collectorService = new CollectorServiceImpl(new CollectorRepositoryImpl());
//
//
//    @Override
//    public void am.basic.springdemo.service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//       collectorService.logOut(req,resp);
////        req.getRequestDispatcher("/login/admin").forward(req,resp);
//        resp.sendRedirect("/login/collector");
//    }
//}
