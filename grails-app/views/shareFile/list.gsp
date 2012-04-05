<%@ page import="com.sharefile.model.ShareFile" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'shareFile.label', default: 'ShareFile')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-shareFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-shareFile" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'shareFile.name.label', default: 'Name')}"/>

            <g:sortableColumn property="description"
                              title="${message(code: 'shareFile.description.label', default: 'Description')}"/>

            <g:sortableColumn property="size" title="${message(code: 'shareFile.size.label', default: 'Size')}"/>

            <g:sortableColumn property="ext" title="${message(code: 'shareFile.ext.label', default: 'Creator')}"/>

            <th></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${shareFileInstanceList}" status="i" var="shareFileInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>
                    <g:if test="${shareFileInstance.creator?.username == session.user?.username}">
                    <g:link action="show"
                            id="${shareFileInstance.id}">${fieldValue(bean: shareFileInstance, field: "name")}</g:link></td>
                    </g:if>
                <g:else>
                    ${fieldValue(bean: shareFileInstance, field: "name")}
                </g:else>
                <td>${fieldValue(bean: shareFileInstance, field: "description")}</td>

                <td>${fieldValue(bean: shareFileInstance, field: "size")}K</td>

                <td>${fieldValue(bean: shareFileInstance, field: "creator")}</td>


                <td><g:link action="downloadFile" params="[id: shareFileInstance.id]">download</g:link></td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>
</body>
</html>
