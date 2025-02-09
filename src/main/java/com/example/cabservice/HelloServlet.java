package com.example.cabservice;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    // Existing /hello-servlet endpoint
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    // New GET endpoint: /new-endpoint
    @WebServlet("/new-endpoint")
    public static class NewEndpointServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>This is the new GET endpoint!</h1>");
            out.println("</body></html>");
        }
    }

    public void destroy() {
    }
}
