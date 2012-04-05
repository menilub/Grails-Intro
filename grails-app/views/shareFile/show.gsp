<%@ page import="com.sharefile.model.ShareFile" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'shareFile.label', default: 'ShareFile')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-shareFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-shareFile" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list shareFile">

        <g:if test="${shareFileInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="shareFile.name.label"
                                                                        default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${shareFileInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${shareFileInstance?.description}">
            <li class="fieldcontain">
                <span id="description-label" class="property-label"><g:message code="shareFile.description.label"
                                                                               default="Description"/></span>

                <span class="property-value" aria-labelledby="description-label"><g:fieldValue
                        bean="${shareFileInstance}" field="description"/></span>

            </li>
        </g:if>

        <g:if test="${shareFileInstance?.size}">
            <li class="fieldcontain">
                <span id="size-label" class="property-label"><g:message code="shareFile.size.label"
                                                                        default="Size"/></span>

                <span class="property-value" aria-labelledby="size-label"><g:fieldValue bean="${shareFileInstance}"
                                                                                        field="size"/></span>

            </li>
        </g:if>

        <g:if test="${shareFileInstance?.ext}">
            <li class="fieldcontain">
                <span id="ext-label" class="property-label"><g:message code="shareFile.ext.label" default="Ext"/></span>

                <span class="property-value" aria-labelledby="ext-label"><g:fieldValue bean="${shareFileInstance}"
                                                                                       field="ext"/></span>

            </li>
        </g:if>

        <g:if test="${shareFileInstance?.contentType}">
            <li class="fieldcontain">
                <span id="contentType-label" class="property-label"><g:message code="shareFile.contentType.label"
                                                                               default="Content Type"/></span>

                <span class="property-value" aria-labelledby="contentType-label"><g:fieldValue
                        bean="${shareFileInstance}" field="contentType"/></span>

            </li>
        </g:if>

        <g:if test="${shareFileInstance?.data}">
            <li class="fieldcontain">
                <span id="data-label" class="property-label"><g:message code="shareFile.data.label"
                                                                        default="Data"/></span>

            </li>
        </g:if>

        <g:if test="${shareFileInstance?.creator}">
            <li class="fieldcontain">
                <span id="creator-label" class="property-label"><g:message code="shareFile.creator.label"
                                                                           default="Creator"/></span>

                <span class="property-value" aria-labelledby="creator-label"><g:link controller="user" action="show"
                                                                                     id="${shareFileInstance?.creator?.id}">${shareFileInstance?.creator?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${shareFileInstance?.dateCreated}">
            <li class="fieldcontain">
                <span id="dateCreated-label" class="property-label"><g:message code="shareFile.dateCreated.label"
                                                                               default="Date Created"/></span>

                <span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate
                        date="${shareFileInstance?.dateCreated}"/></span>

            </li>
        </g:if>

        <g:if test="${shareFileInstance?.lastUpdated}">
            <li class="fieldcontain">
                <span id="lastUpdated-label" class="property-label"><g:message code="shareFile.lastUpdated.label"
                                                                               default="Last Updated"/></span>

                <span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate
                        date="${shareFileInstance?.lastUpdated}"/></span>

            </li>
        </g:if>

        <g:if test="${shareFileInstance?.users}">
            <li class="fieldcontain">
                <span id="users-label" class="property-label"><g:message code="shareFile.users.label"
                                                                         default="Users"/></span>

                <g:each in="${shareFileInstance.users}" var="u">
                    <span class="property-value" aria-labelledby="users-label"><g:link controller="user" action="show"
                                                                                       id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${shareFileInstance?.id}"/>
            <g:link class="edit" action="edit" id="${shareFileInstance?.id}"><g:message code="default.button.edit.label"
                                                                                        default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
