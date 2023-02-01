<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/datatables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<c:import url="util/header.jsp"/>
<div class="container">
    <div class="col-md-6 mx-auto">
        <div class="row" style="margin: 50px 0 0">
            <h1 style="padding: 0">
                <c:if test="${deposit != null}">Изменить</c:if>
                <c:if test="${deposit == null}">Добавить</c:if>
                депозит
            </h1>
        </div>
        <div class="row">
            <form class="row g-3 " action="controller" method="post" id="deposit_form" onsubmit="return confirm('Уверены?')">
                <input name="user_id" type="hidden" class="form-control" id="user_id" value="<c:if test="${deposit != null}">${deposit.client.id}</c:if><c:if test="${deposit == null}">${user.id}</c:if>">
                <input name="deposit_agreement_id" type="hidden" class="form-control" id="deposit_agreement_id" value="${deposit.id}" >
                <div class="col-6">
                    <label for="inputName" class="form-label">Имя</label>
                    <input name="name" type="text" class="form-control" id="inputName" value="<c:if test="${deposit != null}">${deposit.client.name}</c:if><c:if test="${deposit == null}">${user.name}</c:if>" readonly>
                </div>
                <div class="col-6">
                    <label for="inputSurname" class="form-label">Фамилия</label>
                    <input name="surname" type="text" class="form-control" id="inputSurname" value="<c:if test="${deposit != null}">${deposit.client.surname}</c:if><c:if test="${deposit == null}">${user.surname}</c:if>"  readonly>
                </div>
                <div class="col-6">
                    <label for="inputIdNumber" class="form-label">Идентификационный номер</label>
                    <input name="idNumber" type="text" class="form-control" id="inputIdNumber" value="<c:if test="${deposit != null}">${deposit.client.idNumber}</c:if><c:if test="${deposit == null}">${user.idNumber}</c:if>" readonly>
                </div>

                <div class="col-md-6">
                    <label for="agreement_number" class="form-label">Номер договора</label>
                    <input name="agreement_number" type="text" class="form-control" id="agreement_number" value="<c:if test="${deposit == null}">${agreement_number}</c:if><c:if test="${deposit != null}">${deposit.agreementNumber}</c:if>" pattern="3014\d{8}\d{0,1}" readonly>
                </div>

                <div class="col-6">
                    <label for="start_date" class="form-label">Начало депозита</label>
                    <input name="start_date" type="date" class="form-control" id="start_date" value="${deposit.startDate}" pattern="^(19|20)\d\d\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$" required>
                </div>

                <div class="col-6">
                    <label for="finish_date" class="form-label">Конец депозита</label>
                    <input name="finish_date" type="date" class="form-control" id="finish_date" value="${deposit.finishDate}" pattern="^(19|20)\d\d\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$" required>
                </div>
                <div class="col-6">
                    <label for="term_contract" class="form-label">Срок договора</label>
                    <input name="term_contract" type="date" class="form-control" id="term_contract" value="${deposit.termContract}" pattern="^(19|20)\d\d\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$" required>
                </div>
                <div class="col-6">
                    <label for="deposit_amount" class="form-label">Сумма депозита</label>
                    <input name="deposit_amount" type="number" class="form-control" id="deposit_amount" min="1" max="999999999999999" step="0.01" value="${deposit.depositAmount}" pattern="^\d{1,15}$|^\d{1,14}\,\d{1,2}$" required>
                </div>
                <div class="col-6">
                    <label class="form-label">Тип депозита</label>
                    <select id="deposit_type" name="deposit_type" class="form-select"
                            aria-label="Default select example" required>
                        <option value="PERPETUAL" selected>Бессрочный</option>
                        <option value="LONG_TERM">Долгосрочный</option>
                        <option value="SHORT_TERM">Краткосрочный</option>
                    </select>
                </div>
                <div class="col-6">
                    <label class="form-label">Валюта</label>
                    <select id="currency_type" name="currency_type" class="form-select"
                            aria-label="Default select example" required>
                        <option value="BYN">BYN</option>
                        <option value="USD">USD</option>
                        <option value="EUR">EUR</option>
                    </select>
                </div>

                <div class="col-12" style="width: 100%">
                    <c:if test="${deposit != null}">
                        <input type="hidden" name="command" value="edit_deposit">
                        <input type="submit" class="btn btn-success" value="Изменить депозит" style="width: 100%">
                    </c:if>
                    <c:if test="${deposit == null}">
                        <input type="hidden" name="command" value="add_deposit">
                        <input type="submit" class="btn btn-success" value="Оформить депозит" style="width: 100%">
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</body>
</html>
