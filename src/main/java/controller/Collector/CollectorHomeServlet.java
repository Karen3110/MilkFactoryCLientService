//package controller.Collector;
//
//import model.exception.SignedInException;
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
//@WebServlet("/collector/home")
//
//public class CollectorHomeServlet extends HttpServlet {
//    private final CollectorService collectorService = new CollectorServiceImpl(new CollectorRepositoryImpl());
//
//    @Override
//    protected void am.basic.springdemo.service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//        try {
//            collectorService.checkSigned(req, resp);
//            req.getRequestDispatcher("/pages/collector/home.jsp").forward(req, resp);
//        } catch (SignedInException exception) {
//            System.out.println(exception.getMessage());
//            req.getRequestDispatcher("/pages/collector/login-collector.jsp").forward(req, resp);
//
//
//        }
//
//    }
//}
