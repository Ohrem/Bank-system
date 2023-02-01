<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<c:import url="/pages/util/header.jsp"/>
<div class="container" style="margin-top: -80px; margin-bottom: 30px">
    <div class="h1 indent-b-48 text-center">
        <p style="color: #132B1E; font-family: 'French Script MT';font-size: 60px; font-weight: normal">БелИнвестБанку
            30 лет!</p>

    </div>
    <div class="benefits-secondary_wrapper" style="margin-top: 30px">
        <div class="benefits-secondary_item"
             style="background-color: #f5f5f7; width:1298px;  border-radius: 5px; min-height: 292px; overflow: hidden; padding: 20px 40px 20px; position: relative; display: block;">
            <img src="${pageContext.request.contextPath}/resources/pogo.png" class="benefits-secondary_image" alt="boll"
                 style="bottom: 0; display: block; height: 100%;max-height: 100%;object-position: left bottom;position: absolute; right: 0; width: auto; z-index: 0;">
            <div class="benefits-secondary_content" style="min-width: 256px; position: relative; z-index: 1;">
                <div class="benefits-secondary__title" style="font-size: 26px; line-height: 34px;">
                    <span class="colortext" style="color: #0d3d19">#Играй сильно!</span>
                </div>
                <div class="benefits-secondary_text"
                     style="margin-top: 8px; font-size: 16px; line-height: 24px; margin-right: 30px; width: 600px; ">
                    От начала времен было три великих изобретения: огонь, колесо и центральная банковская система.
                </div>

                <div class="benefits-secondary_title2" style="font-size: 26px; line-height: 34px; margin-top: 30px">
                    <span class="colortext" style="color: #0d3d19">#Участвуй!</span>
                </div>
                <div class="benefits-secondary_text"
                     style="margin-top: 8px; font-size: 16px; line-height: 24px; width: 600px">
                    Сейчас наибольший рост происходит в отраслях связанных с услугами. Сектор услуг растёт радикально
                    быстрее производственного. Это говорит о переходе к экономике восприятия в целом, когда клиенты
                    переходят от пассивного потребления продуктов к активному участию.

                </div>
                <div class="benefits-secondary_title3" style="font-size: 26px; line-height: 34px; margin-top: 30px">
                    <span class="colortext" style="color: #0d3d19">#Выбор за тобой!</span>
                </div>
                <div class="benefits-secondary_text" style="margin-top: 8px; font-size: 16px; line-height: 24px;">
                    Даем клиентам лишь то, чего они хотят!
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" style="margin-top: 30px; margin-bottom: 50px">
    <div class="col-md-12">
        <div class="row">
            <div class="col-sm-4">
                <div class="card">
                    <img src="${pageContext.request.contextPath}/resources/clients.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Клиенты</h5>
                        <p class="card-text"> Здесь вы можете добавить, изменить, удалить, а также просмотреть
                            необходимую информацию о клиентах</p>
                        <div class="d-grid gap-2 d-md-block">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="find_all_users">
                                <input class="btn btn-success" type="submit" value="Перейти">
                            </form>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="to_add_client">
                                <input class="btn btn-secondary" type="submit" value="Добавить">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="card">
                    <img src="${pageContext.request.contextPath}/resources/invest.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Депозиты</h5>
                        <p class="card-text">Этот раздел позволяет выполнять операции с депозитными счетами.</p>
                        </br>
                        <div class="d-grid gap-2 d-md-block">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="show_all_deposits">
                                <input class="btn btn-success" type="submit" value="Перейти">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="card">
                    <img src="${pageContext.request.contextPath}/resources/kredit.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Кредиты</h5>
                        <p class="card-text">Этот раздел позволяет добавлять, изменять, удалять, а также просматривать
                            необходимую информацию о кредитах.</p>

                        <div class="d-grid gap-2 d-md-block">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="show_all_credits">
                                <input class="btn btn-success" type="submit" value="Перейти">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</body>
</html>