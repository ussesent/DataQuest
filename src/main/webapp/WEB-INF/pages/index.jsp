<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="РћРЅР»Р°Р№РЅ СЃРµСЂРІРёСЃ СЃРѕР±С‹С‚РёР№ Game-РёРЅРґСѓСЃС‚СЂРёРё" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>GameEvents</title>
</head>

<body>
<h1 class="visually-hidden">РћРЅР»Р°Р№РЅ СЃРµСЂРІРёСЃ СЃРѕР±С‹С‚РёР№ Game-РёРЅРґСѓСЃС‚СЂРёРё</h1>

<!-------- HEADER ---------->
<header class="header">
    <div class="header__inner container">
        <div class="logo">
            <a href="#">
                <img
                        src="${pageContext.request.contextPath}/icons/header/header-icon.png"
                        alt="GameEvents logo"
                        class="logo__image"
                />
            </a>
        </div>

        <c:choose>
            <c:when test="${not empty user}">
                <!-- Р”Р»СЏ Р°РІС‚РѕСЂРёР·РѕРІР°РЅРЅС‹С… -->
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
            </c:when>

            <c:otherwise>
                <!-- // Р”Р»СЏ РЅРµР°РІС‚РѕСЂРёР·РѕРІР°РЅРЅС‹С… -->
                <ul class="header__auth">
                    <li class="header__auth-item">
                        <a href="${pageContext.request.contextPath}/pages/register" class="header__auth-link button"
                        >Р—Р°СЂРµРіРµСЃС‚СЂРёСЂРѕРІР°С‚СЊСЃСЏ</a
                        >
                    </li>
                    <li class="header__auth-item">
                        <a href="${pageContext.request.contextPath}/pages/login" class="header__auth-link button">Р’РѕР№С‚Рё</a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</header>

<!-------- MAIN ---------->
<main class="content">
    <!-------- BANNER_SECTION ---------->
    <section class="banner">
        <div class="banner__inner container">
            <div class="banner__inner-info">
                <h2 class="section-title banner__inner-title">GameEvents</h2>
                <p class="banner_inner-subtitle">
                    РРіСЂРѕРІРѕР№ РјРёСЂ РјРµРЅСЏРµС‚СЃСЏ РєР°Р¶РґС‹Р№ РґРµРЅСЊ. <br />
                    GameEvents РїРѕРєР°Р¶РµС‚, РіРґРµ РїСЂРѕРёСЃС…РѕРґРёС‚ СЃР°РјРѕРµ РёРЅС‚РµСЂРµСЃРЅРѕРµ вЂ” РѕС‚ РіСЂРѕРјРєРёС…
                    СЂРµР»РёР·РѕРІ РґРѕ РєСЂСѓРїРЅРµР№С€РёС… С‚СѓСЂРЅРёСЂРѕРІ.
                </p>
            </div>

            <div class="banner_inner-img">
                <img src="${pageContext.request.contextPath}/images/popular-games/game-image.png" alt="Р‘Р°РЅРЅРµСЂ" />
            </div>
        </div>
    </section>

    <!-------- ABOUT_SECTION ---------->
    <section class="about">
        <div class="about__inner container">
            <h2 class="section-title">Р§С‚Рѕ РґРµР»Р°РµС‚ GameEvents СѓРґРѕР±РЅС‹Рј</h2>
            <ul class="abuot__inner-list">
                <li class="about__iner-item">
                    <h3 class="about__iner-item-title">
                        1. РђРєС‚СѓР°Р»СЊРЅС‹Рµ СЃРѕР±С‹С‚РёСЏ РїРѕ РёРіСЂР°Рј
                    </h3>
                    <p class="about__inner-item-text">
                        РњС‹ СЃРѕР±РёСЂР°РµРј РёРЅС„РѕСЂРјР°С†РёСЋ Рѕ Р±Р»РёР¶Р°Р№С€РёС… СЂРµР»РёР·Р°С…, С‚СѓСЂРЅРёСЂР°С… Рё
                        РїСЂРµР·РµРЅС‚Р°С†РёСЏС… РёРіСЂ РІ РѕРґРЅРѕРј РјРµСЃС‚Рµ.
                    </p>
                </li>
                <li class="about__iner-item">
                    <h3 class="about__iner-item-title">2. РџРѕРґРїРёСЃРєРё РЅР° РёРіСЂС‹</h3>
                    <p class="about__inner-item-text">
                        РќР°СЃС‚СЂРѕР№С‚Рµ РёРЅС‚РµСЂРµСЃСѓСЋС‰РёРµ РїСЂРѕРµРєС‚С‹ Рё РїРѕР»СѓС‡Р°Р№С‚Рµ СѓРІРµРґРѕРјР»РµРЅРёСЏ Рѕ РЅРѕРІС‹С…
                        СЃРѕР±С‹С‚РёСЏС….
                    </p>
                </li>
                <li class="about__iner-item">
                    <h3 class="about__iner-item-title">3. Р§РёСЃС‚С‹Р№ РёРЅС‚РµСЂС„РµР№СЃ</h3>
                    <p class="about__inner-item-text">
                        Р’СЃС‘, С‡С‚Рѕ РЅСѓР¶РЅРѕ вЂ” Р±РµР· СЂРµРєР»Р°РјС‹ Рё Р»РёС€РЅРёС… РґРµС‚Р°Р»РµР№. РўРѕР»СЊРєРѕ РІР°Р¶РЅР°СЏ
                        РёРЅС„РѕСЂРјР°С†РёСЏ.
                    </p>
                </li>
            </ul>
        </div>
    </section>

    <!-------- MORE_POPUALAR_GAMES_SECTION ---------->
    <section class="popular-games">
        <div class="popular-games__inner container">
            <h2 class="section-title">РЎРµР№С‡Р°СЃ РїРѕРїСѓР»СЏСЂРЅС‹ :</h2>
            <ul class="popular-games__inner-list">
                <li class="popular-games__inner-item">
                    <a href="/">
                        <img src="${pageContext.request.contextPath}/images/popular-games/game-image.png" alt="РР·РѕР±СЂР°Р¶РµРЅРёРµ РёРіСЂС‹" />
                        <span>РРіСЂР° 1</span>
                    </a>
                </li>
                <li class="popular-games__inner-item">
                    <a href="/">
                        <img src="${pageContext.request.contextPath}/images/popular-games/game-image.png" alt="РР·РѕР±СЂР°Р¶РµРЅРёРµ РёРіСЂС‹" />
                        <span>РРіСЂР° 2</span>
                    </a>
                </li>
                <li class="popular-games__inner-item">
                    <a href="/">
                        <img src="${pageContext.request.contextPath}/images/popular-games/game-image.png" alt="РР·РѕР±СЂР°Р¶РµРЅРёРµ РёРіСЂС‹" />
                        <span>РРіСЂР° 3</span>
                    </a>
                </li>
            </ul>
        </div>
    </section>

    <!-------- SOME_EVENTS_SECTION ---------->
    <section class="events">
        <div class="events__inner container">
            <h2 class="section-title">РќРµРґР°РІРЅРёРµ СЃРѕР±С‹С‚РёСЏ :</h2>

            <c:if test="${not empty error}">
                <div class="error"><c:out value="${error}"/></div>
                <c:remove var="error" scope="session"/>
            </c:if>

            <ul class="events__inner-list">

                <c:forEach var="event" items="${lastEvents}">
                    <c:choose>
                        <c:when test="${not empty event.avatarUrl}">
                            <li class="events__inner-item-with-image">
                        </c:when>

                        <c:otherwise>
                            <li class="events__inner-item">
                        </c:otherwise>
                    </c:choose>
                    <div class="events__inner-item-date">
                            ${event.publicationDate}
                    </div>
                    <hr class="events__inner-item-separator" />

                    <a href="${pageContext.request.contextPath}/pages/secure/event?eventId=${event.id}">

                        <c:if test="${not empty event.avatarUrl}">
                            <div class="events__inner-item-image">
                                <img src="${event.avatarUrl}"
                                     alt="РР·РѕР±СЂР°Р¶РµРЅРёРµ СЃРѕР±С‹С‚РёСЏ" />
                            </div>
                        </c:if>

                        <div class="events__inner-item-info">
                            <h3 class="events__inner-item-title"><c:out value="${event.name}"/></h3>
                            <p>РћРїРёСЃР°РЅРёРµ: <c:out value="${event.shortDescription}"/></p>
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
            <a href="${pageContext.request.contextPath}/pages/secure/events" class="button button--more">Р±РѕР»СЊС€Рµ </a>
        </div>
    </section>
</main>

<!-------- FOOTER ---------->
<footer class="footer">
    <div class="footer__inner container">
        <span>Made by Ussesent</span>
    </div>
</footer>
</body>
</html>
