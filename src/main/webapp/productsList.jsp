<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="comp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sklep muzyczny</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <h1>Lista Produktów</h1>
        <div class="counters">
            <div>Servlet counter: ${counter}</div>
            <div>Session counter: ${sessionScope.counter}</div>
            <div>Context counter: ${applicationScope.counter}</div>
        </div>
    </header>
    <main>
        <comp:navigation/>
        <section>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Cena</th>
                    <th>Usuń</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td><a href="detail?id=<c:out value='${product.id}' />">
                                ${product.name}
                        </a>
                        <td>${product.price}</td>
                        <td>
                            <button class="button-nav-link" onclick="deleteProject(${product.id})">Usuń</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </main>
    <script>
        function deleteProject(productId) {
            if (confirm('Are you sure?')) {
                var xhr = new XMLHttpRequest();
                xhr.open('DELETE', 'edit', true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.addEventListener("load", onSuccess);
                xhr.addEventListener("error", onError);
                xhr.send("id=" + productId);
            }
        }

        function onSuccess() {
            window.location.reload();
        }

        function onError() {
            console.log("An error occurred while product delete.");
        }
    </script>
</body>
</html>
