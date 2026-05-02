<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="СЃ" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta
            name="description"
            content="РЎРѕР±С‹С‚РёСЏ GameEvents"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>GE РЎРѕР±С‹С‚РёСЏ</title>
</head>
<body>
<header class="header">
    <div class="header__inner container">
        <div class="logo">
            <a href="${pageContext.request.contextPath}">
                <img
                        src="${pageContext.request.contextPath}/icons/header/header-icon.png"
                        alt="GameEvents logo"
                        class="logo__image"
                />
            </a>
        </div>


        <c:if test="${user.role eq 'OWNER'}">
            <a href="${pageContext.request.contextPath}/pages/secure/owner/users" class="admin-link-in-header">РџРѕР»СЊР·РѕРІР°С‚РµР»Рё</a>
        </c:if>
        <nav class="header__nav">
            <ul class="header__nav-list">
                <li class="header__nav-item">
                    <a href="${pageContext.request.contextPath}/pages/secure/events" class="header__nav-link">РЎРѕР±С‹С‚РёСЏ</a>
                </li>
                <li class="header__nav-item">
                    <a href="${pageContext.request.contextPath}/pages/secure/subscriptions" class="header__nav-link">РџРѕРґРїРёСЃРєРё</a>
                </li>
                <li class="header__nav-item">
                    <a href="${pageContext.request.contextPath}/pages/secure/profile" class="header__nav-link">РџСЂРѕС„РёР»СЊ</a>
                </li>
            </ul>
        </nav>

    </div>
</header>

<main class="content">
    <div class="admin-panel container">
        <!-- РљРЅРѕРїРєР° С‚РѕР»СЊРєРѕ РґР»СЏ ADMIN/OWNER -->
        <c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'OWNER'}">
            <button id="openModalBtn" class="button button--add">Р”РѕР±Р°РІРёС‚СЊ СЃРѕР±С‹С‚РёРµ</button>
        </c:if>

        <!-- РњРѕРґР°Р»СЊРЅРѕРµ РѕРєРЅРѕ -->
        <div id="eventModal" class="admin-panel__modal">
            <div class="admin-panel__modal-content">
                <span class="close">&times;</span>
                <h2>Р”РѕР±Р°РІРёС‚СЊ СЃРѕР±С‹С‚РёРµ</h2>

                <form action="${pageContext.request.contextPath}/secure/addEvent" method="post" class="admin-panel__add-form" enctype="multipart/form-data">
                    <label>РќР°Р·РІР°РЅРёРµ:</label>
                    <input type="text" name="name" required>

                    <label>РљСЂР°С‚РєРѕРµ РѕРїРёСЃР°РЅРёРµ:</label>
                    <textarea name="shortDescription" required></textarea>

                    <label>РџРѕР»РЅРѕРµ РѕРїРёСЃР°РЅРёРµ:</label>
                    <textarea name="fullDescription" ></textarea>

                    <label>РР·РѕР±СЂР°Р¶РµРЅРёРµ:</label>
                    <input type="file" name="avatar">

                    <label>РРіСЂР°:</label>
                    <select name="gameId" required>
                        <option value="" disabled selected></option>
                        <c:forEach var="game" items="${games}">
                            <option value="${game.id}">${game.name}</option>
                        </c:forEach>
                    </select>

                    <label>Р”Р°С‚Р° Рё РІСЂРµРјСЏ РЅР°С‡Р°Р»Р°:</label>
                    <input type="datetime-local" name="event_start" >

                    <label>Р”Р°С‚Р° Рё РІСЂРµРјСЏ РѕРєРѕРЅС‡Р°РЅРёСЏ:</label>
                    <input type="datetime-local" name="event_end" >

                    <label>Р¤РѕСЂРјР°С‚:</label>
                    <select name="format" required>
                        <c:forEach var="format" items="${formats}">
                            <option value="${format}">${format}</option>
                        </c:forEach>
                    </select>

                    <button type="submit" class="button">РЎРѕР·РґР°С‚СЊ</button>
                </form>
            </div>
        </div>
    </div>

    <section class="events">
        <div class="events__inner container">

            <c:if test="${not empty addEventError}">
                <div class="error"><c:out value="${addEventError}"/></div>
                <c:remove var="addEventError" scope="session"/>
            </c:if>
            <h1 class="section-title">CРѕР±С‹С‚РёСЏ :</h1>

            <!-- РџСЂРѕРІРµСЂРєР° РѕС€РёР±РєРё Р·Р°РіСЂСѓР·РєРё -->
            <c:choose>
                <c:when test="${not empty error}">
                    <div class="error"><c:out value="${error}"/></div>
                    <c:remove var="error" scope="session"/>
                </c:when>

                <c:otherwise>

                    <!-- РџСЂРѕРІРµСЂРєР° РЅР° РїСѓСЃС‚РѕР№ СЃРїРёСЃРѕРє -->
                    <c:choose>
                        <c:when test="${empty events}">
                            <p>РџРѕРєР° РЅРµС‚ РґРѕСЃС‚СѓРїРЅС‹С… СЃРѕР±С‹С‚РёР№.</p>
                        </c:when>

                        <c:otherwise>

                            <ul class="events__inner-list">
                                <c:forEach var="event" items="${events}">
                                    <c:choose>
                                        <c:when test="${not empty event.avatarUrl}">
                                            <li class="events__inner-item-with-image">
                                        </c:when>
                                        <c:otherwise>
                                            <li class="events__inner-item">
                                        </c:otherwise>
                                    </c:choose>

                                    <div class="events__inner-item-date">
                                            <c:out value="${event.publicationDate}"/>
                                    </div>

                                    <hr class="events__inner-item-separator" />

                                    <a href="${pageContext.request.contextPath}/pages/secure/event?eventId=${event.id}">

                                        <c:if test="${not empty event.avatarUrl}">
                                            <div class="events__inner-item-image">
                                                <img src="${event.avatarUrl}" alt="РР·РѕР±СЂР°Р¶РµРЅРёРµ СЃРѕР±С‹С‚РёСЏ" />
                                            </div>
                                        </c:if>

                                        <div class="events__inner-item-info">
                                            <h3 class="events__inner-item-title"><c:out value="${event.name}"/></h3>
                                            <p>РћРїРёСЃР°РЅРёРµ: <c:out value="${event.shortDescription}"/></p>
                                            <span>РРіСЂР°: <c:out value="${gameNames[event.gameId]}"/></span>
                                            <div class="events__inner-item-meta">
                                                <span>Р¤РѕСЂРјР°С‚: <c:out value="${event.format}"/></span>

                                                <div>
                                                    <c:if test="${event.eventStart != null}">
                                                        <date>РќР°С‡Р°Р»Рѕ: <c:out value="${event.eventStartFormatted}"/></date> <br>
                                                    </c:if>

                                                    <c:if test="${event.eventEnd != null}">
                                                        <date>РљРѕРЅРµС†: <c:out value="${event.eventEndFormatted}"/></date>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>

                                    </a>
                                    </li>
                                </c:forEach>
                            </ul>

                        </c:otherwise>
                    </c:choose>

                </c:otherwise>
            </c:choose>

        </div>
    </section>
</main>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
