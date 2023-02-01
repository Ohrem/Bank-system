<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    </head>
    <nav class="navbar navbar-light">
        <div class="container-fluid justify-content-between align-items-center">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                <img src="${pageContext.request.contextPath}/resources/1.png" alt="" width="128" height="128"
                     class="d-inline-block align-text-middle">
                <p class="belInvest">БелИнвестБанк</p>
            </a>
            <form action="controller" method="post" class="mb-0" onsubmit="return confirm('Уверены?')">
                <input type="hidden" name="command" value="to_bank_day_closing">
                <input class="btn-reckless" type="submit" value="Закрыть банковский день">
            </form>
        </div>
    </nav>
</header>