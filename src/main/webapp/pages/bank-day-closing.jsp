<%@ page import="com.example.kurs6.enity.Client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/datatables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <title>Сальдо банковского дня</title>
</head>
<body>
<c:import url="util/header.jsp"/>
<div class="container">
    <div class="col-md-12">
        <div class="row" style="margin: 50px 0">
            <h1 style="padding: 0">Сальдо банковского дня</h1>
            <p style="padding: 0">${message}</p>
        </div>
        <div class="row">
            <table id="bank_day" class="table table-light table-bordered align-middle table table-striped" style="background-color: #d1e7dd">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Клиент</th>
                    <th scope="col">Номер счета</th>
                    <th scope="col">Вид услуги</th>
                    <th scope="col">Программа</th>
                    <th scope="col">Сальдо счета</th>
                    <th scope="col">Статус счета</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="index" items="${indexes}">
                    <tr>
                        <th scope="row" rowspan="2">${agreement.get(index).client.id}</th>
                        <td rowspan="2">${agreement.get(index).client.name} ${agreement.get(index).client.surname}
                            <br>${agreement.get(index).client.patronymic}</td>
                        <td>${main_bill.get(index).number} (Основной)</td>
                        <td rowspan="2">Депозит</td>
                        <td rowspan="2">
                            <c:if test="${agreement.get(index).depositType == 'PERPETUAL'}">4%</c:if>
                            <c:if test="${agreement.get(index).depositType == 'LONG_TERM'}">10%</c:if>
                            <c:if test="${agreement.get(index).depositType == 'SHORT_TERM'}">4%</c:if>
                        </td>
                        <td>${main_bill.get(index).billAmount}</td>
                        <td>Активен</td>
                    </tr>
                    <tr>
                        <td>${service_bill.get(index).number} (Вспомогательный)</td>
                        <td>${service_bill.get(index).billAmount}</td>
                        <td>Активен</td>
                    </tr>
                </c:forEach>
                <c:forEach var="index" items="${credit_indexes}">
                    <tr>
                        <th scope="row" rowspan="2">${credit_agreement.get(index).client.id}</th>
                        <td rowspan="2">${credit_agreement.get(index).client.name} ${credit_agreement.get(index).client.surname}
                            <br>${credit_agreement.get(index).client.patronymic}</td>
                        <td>${credit_main_bill.get(index).number} (Основной)</td>
                        <td rowspan="2">Кредит</td>
                        <td rowspan="2">
                            <c:if test="${credit_agreement.get(index).creditType == 'PERPETUAL'}">12%</c:if>
                            <c:if test="${credit_agreement.get(index).creditType == 'LONG_TERM'}">17%</c:if>
                            <c:if test="${credit_agreement.get(index).creditType == 'SHORT_TERM'}">21%</c:if>
                        </td>
                        <td>${credit_main_bill.get(index).billAmount}</td>
                        <td>Активен</td>
                    </tr>
                    <tr>
                        <td>${credit_service_bill.get(index).number} (Вспомогательный)</td>
                        <td>${credit_service_bill.get(index).billAmount}</td>
                        <td>Активен</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</body>
</html>
