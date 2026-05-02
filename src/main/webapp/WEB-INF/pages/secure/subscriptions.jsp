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


</body>
</html>
