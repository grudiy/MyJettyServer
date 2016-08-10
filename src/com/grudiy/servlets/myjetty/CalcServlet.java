package com.grudiy.servlets.myjetty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@SuppressWarnings("serial")
public class CalcServlet extends HttpServlet {
    // Query String: ?operand1=11&operand2=222&operation=ADD

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        double operand1 = Integer.parseInt(request.getParameter("operand1"));
        double operand2 = Integer.parseInt(request.getParameter("operand2"));
        String operation = request.getParameter("operation");
        double result = 0;


        response.getWriter().println("<h1>" + operand1 + " " + operation + " " + operand2 + "<h1>");

        switch (operation){
            case "ADD": result = operand1 + operand2; break;
            case "MULTIPLY": result = operand1 * operand2; break;
            case "SUBTRACT": result = operand1 - operand2; break;
            case "DIVIDE": result = (operand1 / operand2); break;
        }

        response.getWriter().println("<h1>= " + result + "</h1>");
        response.getWriter().println("</br>");
        response.getWriter().println("Completed.");
    }
}