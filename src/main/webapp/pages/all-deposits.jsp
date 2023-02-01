<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/datatables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <title>Current client deposits</title>
</head>
<body>
<c:import url="util/header.jsp"/>
<h2>${message}</h2>
<div class="container">
    <div class="col-md-12">
        <div class="row" style="margin: 50px 0">
            <h1 style="padding: 0">
                <c:if test="${sessionScope.currentPage == 'all_deps'}">
                    Все депозиты
                </c:if>
                <c:if test="${sessionScope.currentPage == 'all_creds'}">
                    Все кредиты
                </c:if>
            </h1>
        </div>
        <div class="row">
            <table id="deposits" class="table table-light table-bordered table-responsive">
                <thead>
                    <tr>
                        <th scope="col">Номер договора</th>
                        <th scope="col">Начало договора</th>
                        <th scope="col">Конец договора</th>
                        <th scope="col">Срок договора</th>
                        <th scope="col">Сумма депозита</th>
                        <th scope="col">Тип договора</th>
                        <th scope="col">Вид валюты</th>
                        <th scope="col">Действия</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="deposits" items="${list}">
                    <tr>
                        <th>${deposits.agreementNumber}</th>
                        <td>${deposits.startDate}</td>
                        <td>${deposits.finishDate}</td>
                        <td>${deposits.termContract}</td>
                        <td>${deposits.depositAmount}</td>
                        <td>
                            <c:choose>
                                <c:when test="${deposits.depositType == 'PERPETUAL' or deposits.creditType == 'PERPETUAL'}">Бессрочный</c:when>
                                <c:when test="${deposits.depositType == 'SHORT_TERM' or deposits.creditType == 'SHORT_TERM'}">Краткосрочный</c:when>
                                <c:when test="${deposits.depositType == 'LONG_TERM' or deposits.creditType == 'LONG_TERM'}">Долгосрочный</c:when>
                            </c:choose>
                        </td>
                        <td>${deposits.currencyType}</td>
                        <td>
                            <div class="btn-group dropend">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                    Действия
                                </button>
                                <ul class="dropdown-menu" style="">
                                    <c:if test="${sessionScope.currentPage == 'all_deps'}">
                                        <li>
                                            <form action="controller" method="POST">
                                                <input type="hidden" name="command" value="to_edit_deposit">
                                                <input type="hidden" name="deposit_agreement_id" value="${deposits.id}">
                                                <input class="dropdown-item" type="submit" value="Изменить">
                                            </form>
                                        </li>
                                        <li>
                                            <form action="controller" method="POST">
                                                <input type="hidden" name="command" value="delete_deposit">
                                                <input type="hidden" name="user_id" value="${deposits.client.id}">
                                                <input type="hidden" name="deposit_agreement_id" value="${deposits.id}">
                                                <input class="dropdown-item" type="submit" value="Удалить">
                                            </form>
                                        </li>
                                    </c:if>
                                    <c:if test="${sessionScope.currentPage == 'all_creds'}">
                                        <li>
                                            <form action="controller" method="POST">
                                                <input type="hidden" name="command" value="to_edit_credit">
                                                <input type="hidden" name="deposit_agreement_id" value="${deposits.id}">
                                                <input class="dropdown-item" type="submit" value="Изменить">
                                            </form>
                                        </li>
                                        <li>
                                            <form action="controller" method="POST">
                                                <input type="hidden" name="command" value="delete_credit">
                                                <input type="hidden" name="user_id" value="${deposits.client.id}">
                                                <input type="hidden" name="deposit_agreement_id" value="${deposits.id}">
                                                <input class="dropdown-item" type="submit" value="Удалить">
                                            </form>
                                        </li>
                                    </c:if>

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
<script src="${pageContext.request.contextPath}/resources/datatables/datatables.min.js"></script>
<%--<script>--%>
<%--    $(document).ready(function() {--%>
<%--        $('#deposits').DataTable( {--%>
<%--            columnDefs: [--%>
<%--                { orderable: false, targets: 7 },--%>
<%--                { searchable: false, targets: 7}--%>
<%--            ],--%>
<%--            order: [[1, 'asc']]--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
</body>
</html>
