<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ru">
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta
            name="description"
            content="РЎРѕР±С‹С‚РёСЏ GameEvents"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>GE РїРѕР»СЊР·РѕРІР°С‚РµР»Рё</title>
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



        <a href="${pageContext.request.contextPath}/pages/secure/owner/users" class="admin-link-in-header">РџРѕР»СЊР·РѕРІР°С‚РµР»Рё</a>

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
    <section class="users">
        <div class="users__inner container">
            <h1 class="section-title">РџРѕР»СЊР·РѕРІР°С‚РµР»Рё</h1>

            <c:choose>

                <c:when test="${not empty error}">
                    <div class="error"><c:out value="${error}"/></div>
                    <c:remove var="error" scope="session"/>
                </c:when>

                <c:otherwise>


                    <c:choose>
                        <c:when test="${empty users}">
                            <p>РџРѕР»СЊР·РѕРІР°С‚РµР»РµР№ РЅРµС‚ РѕРћ</p>
                        </c:when>

                        <c:otherwise>

                            <div>
                                <span>Р’СЃРµРіРѕ: ${users.size()} |</span>
                                <span>РђРґРјРёРЅРѕРІ: ${countAdmins}</span>
                            </div>

                            <table class="users__table">
                                <thead>
                                <tr>
                                    <th class="users__td">id</th>
                                    <th class="users__td">username</th>
                                    <th class="users__td">role</th>
                                    <th class="users__td">action</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="user" items="${users}">
                                    <tr class="users__tr">
                                        <td class="users__td"><c:out value="${user.id}"/></td>
                                        <td class="users__td"><c:out value="<c:out value="`${user.username}" />"/></td>
                                        <td class="users__td"><c:out value="${fn:toLowerCase(user.role)}"/></td>

                                        <td class="users__td users__action">
                                            <c:choose>
                                                <c:when test="${user.role eq 'USER'}">
                                                <form action="${pageContext.request.contextPath}/secure/owner/toAdmin" method="post">
                                                    <input type="hidden" name="userId" value="${user.id}" />
                                                    <input type="submit" value="to Admin" class="button toAdmin-button" />
                                                </form>
                                                </c:when>

                                                <c:when test="${user.role eq 'ADMIN'}">
                                                <form action="${pageContext.request.contextPath}/secure/owner/toUser" method="post">
                                                    <input type="hidden" name="userId" value="${user.id}" />
                                                    <input type="submit" value="to User" class="button toUser-button" />
                                                </form>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                        </c:otherwise>
                    </c:choose>

                </c:otherwise>
            </c:choose>

        </div>
    </section>
</main>
</body>
</html>
