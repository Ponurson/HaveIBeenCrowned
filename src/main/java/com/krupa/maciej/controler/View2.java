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
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "View2", urlPatterns = "/view2")
public class View2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        Gson gson = new Gson();
        System.out.println("dopost 2 started");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println(json);
        }
        Map<String, ArrayList<Map<String, String>>> test = gson.fromJson(json, Map.class);
        ArrayList<Map<String, String>> test2 = test.get("data");
        Date date = new Date();
        long minTimestamp = date.getTime();
        ArrayList<OneMinute> oneMinutes = new ArrayList<>();
        for (Map<String, String> m : test2) {
            ArrayList<String> intermediateTimestamps = ControlerUtils.getIntermediateTimestamps(m.get("startTimestamp"),
                    m.get("stopTimestamp"));
            for (String time : intermediateTimestamps) {
                minTimestamp = ((minTimestamp < Long.parseLong(time)) ? minTimestamp : Long.parseLong(time));
                oneMinutes.add(new OneMinute(m.get("location"), time));
            }
        }
        List<OneMinute> oneMinutesInfected = OneMinuteDAO.readByTimestamp("" + minTimestamp);
        HashSet<String> contactDates = new HashSet<>();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        for (OneMinute oM : oneMinutes) {
            if (oneMinutesInfected.contains(oM)){
                System.out.println(oM.getMinuteTimestamp());
                contactDates.add(simpleDateFormat.format(new Date(Long.parseLong(oM.getMinuteTimestamp()))));
            }
        }
        System.out.println("done");
        if (contactDates.size() > 0) {
            response.setContentType("text/plain");
            response.getWriter().append("It is possible you had contact with coronavirus on this dates:\n"
                + contactDates.toString() +
                    "\nWe sugest contacting information line at: <phone number>\n " +
                    "It may be necessary to self-quarantine");
        }else {
            response.setContentType("text/plain");
            response.getWriter().append("As far as we know you did not came in contact with coronavirus during last 14 days");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
