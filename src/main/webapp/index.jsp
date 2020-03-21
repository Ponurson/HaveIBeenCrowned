<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ponur
  Date: 19.03.2020
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HaveIBeenCrowned</title>
    <link href='<c:url value="./css/style2.css"/>' rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="main-div show">
    Go to <a target="_blank" href="https://takeout.google.com/settings/takeout">Google Takeout</a>,
    to download your location data. De-select everything and then check ‘Location History — JSON format’.
    In the next step, download your data as a zip archive, then unzip it. Use json file containing last 14 days.
    <div class="main-div" id="drop_zone_infected" ondrop="dropHandler(event);" ondragover="dragOverHandler(event);">
        <p>Drag your timeline files to this Drop Zone if you are infected</p>
    </div>
    <div class="main-div" id="drop_zone_contact" ondrop="dropHandler(event);" ondragover="dragOverHandler(event);">
        <p>Drag your timeline files to this Drop Zone if you want to check if you had contact with infected</p>
    </div>
    <p id="waitin" style="visibility: hidden">Please wait, your data is being processed...</p>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>
