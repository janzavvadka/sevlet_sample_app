<%@tag pageEncoding="UTF-8"%>
<nav>
    <a class="button-nav-link" href="${pageContext.servletContext.contextPath}">Strona główna</a>
    <a class="button-nav-link" href="list">Lista produktów</a>
    <a class="button-nav-link" href="edit?action=save_product">
        Nowy produkt
    </a>

    <form action="login?action=logout" method="post">
        <input class="button-nav-link"  type="submit" value="Wyloguj" />
    </form>
</nav>
