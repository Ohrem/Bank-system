<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All clients</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/datatables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<c:import url="util/header.jsp"/>
<div class="container">
    <div class="col-md-12">
        <div class="row" style="margin: 50px 0">
            <h1 style="padding: 0">Все клиенты</h1>
            <p style="padding: 0">${message}</p>
        </div>
        <div class="row">
            <table id="clients" class="table table-light table-bordered table-responsive">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Имя</th>
                        <th scope="col">Фамилия</th>
                        <th scope="col">Отчество</th>
                        <th scope="col">Дата рождения</th>
                        <th scope="col">Пол</th>
                        <th scope="col">Действия</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="client" items="${list}">
                    <tr>
                        <th scope="row">${client.id}</th>
                        <td>${client.name}</td>
                        <td>${client.surname}</td>
                        <td>${client.patronymic}</td>
                        <td>${client.DOB}</td>
                        <td>${client.sex}</td>
                        <td>
                            <div class="btn-group dropend">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                    Действия
                                </button>
                                <ul class="dropdown-menu" style="">
                                    <li>
                                        <form action="controller" method="POST">
                                            <input type="hidden" name="command" value="to_edit_user">
                                            <input type="hidden" name="user_id" value="${client.id}">
                                            <input class="dropdown-item" type="submit" value="Изменить">
                                        </form>
                                    </li>
                                    <li>
                                        <form action="controller" method="POST">
                                            <input type="hidden" name="command" value="delete_client_by_id">
                                            <input type="hidden" name="user_id" value="${client.id}">
                                            <input class="dropdown-item" type="submit" value="Удалить">
                                        </form>
                                    </li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li>
                                        <form action="controller" method="POST">
                                            <input type="hidden" name="command" value="to_create_new_deposit_page">
                                            <input type="hidden" name="user_id" value="${client.id}">
                                            <input class="dropdown-item" type="submit" value="Добавить депозит">
                                        </form>
                                    </li>
                                    <li>
                                        <form action="controller" method="POST">
                                            <input type="hidden" name="command" value="to_client_deposits">
                                            <input type="hidden" name="user_id" value="${client.id}">
                                            <input class="dropdown-item" type="submit" value="Просмотреть депозиты">
                                        </form>
                                    </li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li>
                                        <form action="controller" method="POST">
                                            <input type="hidden" name="command" value="to_create_new_credit_page">
                                            <input type="hidden" name="user_id" value="${client.id}">
                                            <input class="dropdown-item" type="submit" value="Добавить кредит">
                                        </form>
                                    </li>
                                    <li>
                                        <form action="controller" method="POST">
                                            <input type="hidden" name="command" value="to_client_credits">
                                            <input type="hidden" name="user_id" value="${client.id}">
                                            <input class="dropdown-item" type="submit" value="Просмотреть кредиты">
                                        </form>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<%--<script src="${pageContext.request.contextPath}/resources/datatables/datatables.min.js"></script>--%>
<%--<script>--%>
<%--    $(document).ready(function() {--%>
<%--        $('#clients').DataTable( {--%>
<%--            columnDefs: [--%>
<%--                { orderable: false, targets: 6 },--%>
<%--                { searchable: false, targets: 6}--%>
<%--            ],--%>
<%--            order: [[0, 'asc']]--%>
<%--        });--%>
<%--    } );--%>
<%--</script>--%>
</body>
</html>
