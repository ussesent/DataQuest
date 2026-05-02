<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
    <title><c:out value="${event.name}"/></title>
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
    <section class="event">
        <div class="event__inner container">

            <c:choose>

                <c:when test="${not empty error}">
                    <div class="error"><c:out value="${error}"/></div>
                    <c:remove var="error" scope="session"/>
                </c:when>

                <c:otherwise>
                <h1 class="section-title"><c:out value="${event.name}"/></h1>

                <c:if test="${not empty event.avatarUrl}">
                    <div class="event__image">
                        <img src="${event.avatarUrl}" alt="<c:out value='${event.name}'/>" />
                    </div>
                </c:if>

                <p class="event__full-description"><c:out value="${event.fullDescription}"/></p>

                    <span>РРіСЂР°: <c:out value="${gameName}"/></span>

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
                </c:otherwise>
            </c:choose>
        </div>
    </section>
</main>
</body>
</html>

