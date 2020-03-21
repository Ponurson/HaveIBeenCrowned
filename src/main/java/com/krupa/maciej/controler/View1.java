package com.krupa.maciej.controler;

import com.google.gson.Gson;
import com.krupa.maciej.model.OneMinute;
import com.krupa.maciej.model.OneMinuteDAO;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "View1", urlPatterns = "/view1")
public class View1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String thankYou = "Team of HaveIBeenCrowned wants to thank you for sharing your location data.\n" +
                "\n" +
                "    It will be stored on our servers for 14 days in ecrypted form and deleted\n" +
                "    after that period.\n" +
                "\n" +
                "    We wish you speedy recovery.";

        HttpSession session = request.getSession();
        System.out.println(session.getId());
        Gson gson = new Gson();
        System.out.println("dopost started");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println(json);
        }

        Map<String, ArrayList<Map<String, String>>> test = gson.fromJson(json, Map.class);
        ArrayList<Map<String, String>> test2 = test.get("data");
        for (Map<String, String> m : test2) {
            ArrayList<String> intermediateTimestamps = ControlerUtils.getIntermediateTimestamps(m.get("startTimestamp"),
                    m.get("stopTimestamp"));
            for (String time : intermediateTimestamps) {
                OneMinuteDAO.create(new OneMinute(m.get("location"), time));
                Date date = new Date(Long.parseLong(time));
                System.out.println(date.toString());
            }
        }
        System.out.println(json);
        response.setContentType("text/plain");
        response.getWriter().append(thankYou);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

