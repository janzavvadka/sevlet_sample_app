<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="comp" %>
<html>
<title>Sklep muzyczny</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <c:choose>
        <c:when test="${action == 'save_product'}">
            <h1>Dodaj produkt</h1>
        </c:when>
        <c:when test="${action == 'update_product'}">
            <h1>Edycja produktów</h1>
        </c:when>
    </c:choose>
    <div class="counters">
        <div>Servlet counter: ${counter}</div>
        <div>Session counter: ${sessionScope.counter}</div>
        <div>Context counter: ${applicationScope.counter}</div>
    </div>
</header>
<main>
    <comp:navigation/>
    <section>
        <ul>
            <c:forEach items="${errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
        <form action="edit?action=${action}" method=post>
            <div>
                <input id="productId" type="hidden" name="productId" value="${productId}">
            </div>
            <div>
                <label for="productName">Nazwa:</label>
                <input id="productName" type="text" name="productName" value="${productName}">
            </div>
            <div>
                <label for="productPrice">Cena:</label>
                <input id="productPrice" type="text" name="productPrice" value="${productPrice}">
            </div>
            <input class="button-link" type="submit" value="Zapisz"/>
        </form>
        <a class="button-link" href="list">Anuluj</a>
    </section>
</main>
</body>
</html>
