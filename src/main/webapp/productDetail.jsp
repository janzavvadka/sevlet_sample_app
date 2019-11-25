<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="comp" %>
<html>
<head>
    <title>Title</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <h1>Szczegóły produktu</h1>
        <div class="counters">
            <div>Servlet counter: ${counter}</div>
            <div>Session counter: ${sessionScope.counter}</div>
            <div>Context counter: ${applicationScope.counter}</div>
        </div>
    </header>
    <main>

        <comp:navigation/>
        <section>
            <div>
                Id: ${product.id}
            </div>
            <div>
                Nazwa: ${product.name}
            </div>
            <div>
                Cena: ${product.price}
            </div>
            <a class="button-link" href="edit?id=${product.id}&action=update_product">
                Edytuj
            </a>
        </section>
    </main>
</body>
</html>
