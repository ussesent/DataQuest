<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta
            name="description"
            content="Р РµРіРёСЃС‚СЂР°С†РёСЏ GameEvents"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>GE СЂРµРіРёСЃС‚СЂР°С†РёСЏ</title>
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

        <div class="header__auth">
            <a href="${pageContext.request.contextPath}" class="header__auth-link button">РќР° РіР»Р°РІРЅСѓСЋ</a>
            <a href="${pageContext.request.contextPath}/pages/login" class="header__auth-link button">Р’РѕР№С‚Рё</a>
        </div>

    </div>
</header>

<main class="content">
    <section class="auth">
        <h1 class="section-title">Р РµРіРёСЃС‚СЂР°С†РёСЏ</h1>
        <form action="${pageContext.request.contextPath}/pages/register" method="post" class="auth-form">
            <input
                    type="text"
                    name="username"
                    placeholder="username"
                    class="auth-form__input"
            />
            <input
                    type="password"
                    name="password"
                    placeholder="password"
                    class="auth-form__input"
            />
            <input
                    type="submit"
                    value="Р—Р°СЂРµРіРµСЃС‚СЂРёСЂРѕРІР°С‚СЊСЃСЏ"
                    class="auth-form__submit"
            />
        </form>
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
