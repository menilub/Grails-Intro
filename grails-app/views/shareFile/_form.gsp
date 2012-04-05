<%@ page import="com.sharefile.model.ShareFile" %>



<div class="fieldcontain ${hasErrors(bean: shareFileInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="shareFile.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" maxlength="50" required="" value="${shareFileInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shareFileInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="shareFile.description.label" default="Description"/>

    </label>
    <g:textField name="description" value="${shareFileInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shareFileInstance, field: 'data', 'error')} required">
    <label for="data">
        <g:message code="shareFile.data.label" default="Data"/>
        <span class="required-indicator">*</span>
    </label>
    <input type="file" id="data" name="data"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shareFileInstance, field: 'creator', 'error')} required">
    <label for="creator">
        <g:message code="shareFile.creator.label" default="Creator"/>
        <span class="required-indicator">*</span>
    </label>
        ${session.user}
</div>

<div class="fieldcontain ${hasErrors(bean: shareFileInstance, field: 'users', 'error')} ">
    <label for="users">
        <g:message code="shareFile.users.label" default="Share with:"/>

    </label>
    <g:select name="users" from="${com.sharefile.auth.User.findAllByIdNotEqual(session.user.id)}" multiple="multiple" optionKey="id" size="5"
              value="${shareFileInstance?.users*.id}" class="many-to-many"/>
</div>

