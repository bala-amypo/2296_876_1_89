package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // empty by design
    }
}
