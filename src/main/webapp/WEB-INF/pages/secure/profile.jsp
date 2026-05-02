<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="ru">
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta
            name="description"
            content="РџСЂРѕС„РёР»СЊ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ GameEvents"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <title>GE РїСЂРѕС„РёР»СЊ</title>
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

            <section class="profile">
                <div class="profile__inner container">
                    <h1 class="section-title">РџСЂРѕС„РёР»СЊ</h1>

                    <div class="profile__info">
                        <span class="profile__info-text">username: <c:out value="<c:out value="`${user.username}" />"/></span>
                        <span class="profile__info-text">ID: <c:out value="${user.id}"/></span>
                        <c:if test="${user.role eq 'ADMIN' or user.role eq 'OWNER'}">
                            <span class="profile__info-text">role: <c:out value="${user.role}"/></span>
                        </c:if>
                    </div>

                    <div class="profile__action">
                        <form action="${pageContext.request.contextPath}/secure/logout" method="post" class="profile__action-form">
                            <input type="submit" value="Р’С‹Р№С‚Рё РёР· Р°РєРєР°СѓРЅС‚Р°" class="button profile__action-exit">
                        </form>

                        <form action="${pageContext.request.contextPath}/secure/deleteAccount" method="post" class="profile__action">
                            <input type="submit" value="РЈРґР°Р»РёС‚СЊ Р°РєРєР°СѓРЅС‚" class="button profile__action-delete">
                        </form>
                    </div>
                </div>
            </section>


        <c:if test="${not empty sessionScope.error}">
            <div class="error">
                    <c:out value="${sessionScope.error}"/>
            </div>
            <!-- РЈРґР°Р»СЏРµРј СЃРѕРѕР±С‰РµРЅРёРµ РїРѕСЃР»Рµ РїРѕРєР°Р·Р° -->
            <c:remove var="error" scope="session"/>
        </c:if>
</main>
</body>
</html>
