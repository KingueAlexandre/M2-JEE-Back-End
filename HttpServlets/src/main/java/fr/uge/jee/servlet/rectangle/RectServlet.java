package fr.uge.jee.servlet.rectangle;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet("/rectangle")
public class RectServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html ; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/templates/rectangle-form.html");
        writer.println(readFromInputStream(inputStream));
        writer.flush();
    }

    public static String readFromInputStream(InputStream inputStream) throws IOException {
        var lines = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines();
        return lines.collect(Collectors.joining("\n"));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html ; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        var width = Integer.parseInt(request.getParameter("width"));
        var height = Integer.parseInt(request.getParameter("height"));
        writer.println("Area of the rectangle is: " + (width*height));
        writer.flush();
    }


}
