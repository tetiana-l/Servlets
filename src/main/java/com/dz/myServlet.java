package com.dz;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class myServlet extends HttpServlet {

    private int countGetRequests = 0;
    private int countPostRequests = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doTasks(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doTasks(request, response);
    }

    private void doTasks(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String method = request.getMethod();
        if (method.equals("GET")) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
                String[] valuesOfMap = parameter.getValue();
                for (String valueOfMap : valuesOfMap) {
                    response.getWriter().println("key: " + parameter.getKey() + " value: " + valueOfMap);
                }
            }
            countGetRequests = countRequest(countGetRequests);
        } else if (method.equals("POST")) {
            countPostRequests = countRequest(countPostRequests);
        }
        response.getWriter().println("Num of GET: " + countGetRequests);
        response.getWriter().println("Num of POST: " + countPostRequests);
    }

    private int countRequest(int count) {
        return ++count;
    }
}
