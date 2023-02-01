<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add/edit client</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<c:import url="util/header.jsp"/>
<div class="container" style="margin-top: -150px">
    <div class="col-md-6 mx-auto">
        <div class="row" style="margin: 50px 0 0">
            <h1 style="padding: 0;">
                <c:if test="${user != null}">Изменить</c:if>
                <c:if test="${user == null}">Добавить</c:if>
                 клиента
            </h1>
        </div>
        <div class="row" style="margin-top: -10px">
            <form class="row g-3" action="controller" method="post" onsubmit="return confirm('Уверены?')">
                <div class="col-6">
                    <label for="inputSurname" class="form-label">Фамилия</label>
                    <input name="surname" type="text" class="form-control" id="inputSurname" value="${user.surname}" placeholder="Иванов" pattern="^[\p{Alpha}А-Яа-яЁё]{2,}-[\p{Alpha}А-Яа-яЁё]{2,}$|^[\p{Alpha}А-Яа-яЁё]{2,}$" required>
                </div>
                <div class="col-5">
                    <label for="inputOtchestvo" class="form-label">Отчество</label>
                    <input name="patronymic" type="text" class="form-control" id="inputOtchestvo" value="${user.patronymic}" placeholder="Иванович" pattern="^([\p{Alpha}А-Яа-я]{1,15})$" required>
                </div>
                <div class="col-4">
                    <label for="inputDate" class="form-label">Дата рождения</label>
                    <input name="DOB" type="date" class="form-control" id="inputDate" value="${user.DOB}" pattern="^(19|20)\d\d\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$" required>
                </div>
                <div class="col-3">
                    <label class="form-label">Пол</label>
                    <select name="sex" class="form-select" aria-label="Default select example" required>
                        <option value="MALE" <c:if test="${user.sex == 'MALE'}">selected</c:if>>Мужской</option>
                        <option value="FEMALE" <c:if test="${user.sex == 'FEMALE'}">selected</c:if>>Женский</option>
                    </select>
                </div>
                <div class="col-3">
                    <label for="inputAddress2" class="form-label">Серия паспорта</label>
                    <input name="passportSerial" type="text" class="form-control" id="inputAddress2" placeholder="МР" value="${user.passportSerial}" minlength="2" maxlength="2" pattern="^[A-Z]{2}$" required>
                </div>
                <div class="col-4">
                    <label for="inputCity" class="form-label">Номер паспорта</label>
                    <input name="passportNumber" type="text" class="form-control" id="inputCity" placeholder="1234567" minlength="7" maxlength="7" value="${user.passportNumber}" pattern="^\d{7}$" required>
                </div>
                <div class="col-5">
                    <label for="inputKemVidan" class="form-label">Кем выдан</label>
                    <input name="kemVidan" type="text" class="form-control" id="inputKemVidan" value="${user.kemVidan}" required>
                </div>
                <div class="col-4">
                    <label for="inputDOI" class="form-label">Срок действия</label>
                    <input name="dateOfIssue" type="date" class="form-control" id="inputDOI" value="${user.dateOfIssue}" pattern="^(19|20)\d\d\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$" required>
                </div>
                <div class="col-8">
                    <label for="inputIdNum" class="form-label">Идентификационный номер</label>
                    <input name="idNumber" type="text" class="form-control" id="inputIdNum" value="${user.idNumber}" pattern="^\d{7}[A-Za-z]\d{3}[A-Za-z]{2}\d$" placeholder="1234567A123AA1" required>
                </div>
                <div class="col-12">
                    <label for="inputPOB" class="form-label">Место рождения</label>
                    <input name="POB" type="text" class="form-control" id="inputPOB" value="${user.POB}" placeholder="Место рождения" required>
                </div>
                <div class="col-4">
                    <label class="form-label">Город проживания</label>
                    <select id="livingcity" name="livingCity" class="form-select" aria-label="Default select example" required>
                        <option value="MINSK" selected>Минск</option>
                        <option value="MOSCOW">Москва</option>
                        <option value="BREST">Брест</option>
                        <option value="SPB">Санкт-Петербург</option>
                        <option value="GRODNO">Гродно</option>
                        <option value="VITEBSK">Витебск</option>
                    </select>
                </div>
                <div class="col-8">
                    <label for="inputLivAddr" class="form-label">Адрес проживания</label>
                    <input name="livingAddress" type="text" class="form-control" id="inputLivAddr" value="${user.livingAddress}" required>
                </div>
                <div class="col-4">
                    <label class="form-label">Город регистрации</label>
                    <select id="registrationCity" name="registrationCity" class="form-select" aria-label="Default select example" required>
                        <option value="MINSK" selected>Минск</option>
                        <option value="MOSCOW">Москва</option>
                        <option value="BREST">Брест</option>
                        <option value="SPB">Санкт-Петербург</option>
                        <option value="GRODNO">Гродно</option>
                        <option value="VITEBSK">Витебск</option>
                    </select>
                </div>
                <div class="col-8">
                    <label for="inputRegAddr" class="form-label">Адрес регистрации</label>
                    <input name="registrationAddress" type="text" class="form-control" id="inputRegAddr" value="${user.registrationAddress}" required>
                </div>
                <div class="col-4">
                    <label class="form-label">Семейное положение</label>
                    <select name="maritalStatus" class="form-select" aria-label="Default select example" required>
                        <option value="MARRIED" <c:if test="${user.maritalStatus == 'MARRIED'}">selected</c:if>>Женат/замужем</option>
                        <option value="NOT_MARRIED" <c:if test="${user.maritalStatus == 'NOT_MARRIED'}">selected</c:if>>Не женат/не замужем</option>
                        <option value="DIVORCED" <c:if test="${user.maritalStatus == 'DIVORCED'}">selected</c:if>>Разведен/разведена</option>
                    </select>
                </div>
                <div class="col-4">
                    <label class="form-label">Гражданство</label>
                    <select name="citizenship" class="form-select" aria-label="Default select example" required>
                        <option value="BELARUS" <c:if test="${user.citizenship == 'BELARUS'}">selected</c:if>>Беларусь</option>
                        <option value="RUSSIA" <c:if test="${user.citizenship == 'RUSSIA'}">selected</c:if>>Россия</option>
                    </select>
                </div>
                <div class="col-4">
                    <label class="form-label">Инвалидность</label>
                    <select name="disability" class="form-select" aria-label="Default select example" required>
                        <option value="NO" <c:if test="${user.disability == 'NO'}">selected</c:if>>Нет</option>
                        <option value="FIRST_GROUP" <c:if test="${user.disability == 'FIRST_GROUP'}">selected</c:if>>1 группа</option>
                        <option value="SECOND_GROUP" <c:if test="${user.disability == 'SECOND_GROUP'}">selected</c:if>>2 группа</option>
                        <option value="THIRD_GROUP" <c:if test="${user.disability == 'THIRD_GROUP'}">selected</c:if>>3 группа</option>
                    </select>
                </div>
                <div class="col-6">
                    <label class="form-label">Пенсионер</label>
                    <select name="pensioner" class="form-select" aria-label="Default select example" required>
                        <option value="TRUE" <c:if test="${user.pensioner == true}">selected</c:if>>Да</option>
                        <option value="FALSE" <c:if test="${user.pensioner == false}">selected</c:if>>Нет</option>
                    </select>
                </div>
                <div class="col-6">
                    <label class="form-label">Военная обязаность</label>
                    <select name="militaryService" class="form-select" aria-label="Default select example" required>
                        <option value="TRUE" <c:if test="${user.militaryService == true}">selected</c:if>>Да</option>
                        <option value="FALSE" <c:if test="${user.militaryService == false}">selected</c:if>>Нет</option>
                    </select>
                </div>
                <div class="col-12">
                    <c:if test="${user == null}">
                        <input type="hidden" name="command" value="add_new_client">
                        <input type="submit" class="btn btn-success" value="Create" style="width: 100%">
                    </c:if>
                    <c:if test="${user != null}">
                        <input type="hidden" name="command" value="update_user">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <input type="submit" class="btn btn-success" value="Edit" style="width: 100%">
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
    document.querySelector('#livingcity option[value="${user.livingCity}"]').setAttribute('selected', 'selected');
    document.querySelector('#registrationCity option[value="${user.registrationCity}"]').setAttribute('selected', 'selected');
</script>
</body>
</html>
