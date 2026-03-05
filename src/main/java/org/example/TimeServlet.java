package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet ({"/time"})
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        writer.write(getTime(req));

        resp.setHeader("Refresh", "1");
    }

    private String getTime (HttpServletRequest request) {
        if (request.getParameterMap().containsKey("timezone")) {
            String timeZone = request.getParameter("timezone").replace(" ","+").toUpperCase();

            return ZonedDateTime.now(ZoneId.of(timeZone)).format(DateTimeFormatter.ofPattern(
                    "dd.MM.yyyy HH:mm:ss"
            ))+ " " + timeZone;
        }

        return ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern(
                "dd.MM.yyyy HH:mm:ss z"
        ));
    }
}