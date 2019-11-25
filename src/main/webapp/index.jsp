<%@ taglib tagdir="/WEB-INF/tags" prefix="comp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sklep muzyczny</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <h1 class="main-h1">Sklep Muzyczny</h1>
    <div class="counters">
        <div>Servlet counter: ${counter}</div>
        <div>Session counter: ${sessionScope.counter}</div>
        <div>Context counter: ${applicationScope.counter}</div>
    </div>
</header>
<main>
    <comp:navigation/>
</main>
</body>
</html>
