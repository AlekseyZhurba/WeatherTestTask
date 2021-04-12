<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather</title>
</head>
<body>
<div>
    <h3>Current Temperature</h3>
    <p :text="${city.name}"></p>
    <h3>Temperature</h3>
    <p :text="${city.temp} + ' &#8451;'">Temperature</p>
</div>
</body>
</html>
