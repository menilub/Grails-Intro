package com.sharefile.model

import org.springframework.dao.DataIntegrityViolationException
import com.sharefile.auth.User

class ShareFileController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        def currentUser = getCurrentUser()
        def shareFiles = ShareFile.findAllByCreator(currentUser)
        def c = ShareFile.createCriteria()
        def fileList = c.list {
            users {
                eq('username', currentUser.username)
            }
        }
        fileList = (fileList + shareFiles).unique()
        [shareFileInstanceList: fileList]
    }

    def create() {
        [shareFileInstance: new ShareFile(params)]
    }

    def save() {
        def shareFileInstance = new ShareFile(params)
        def uploadedFile = request.getFile('data')
        if (!uploadedFile.empty) {
            //binding
            shareFileInstance.size = uploadedFile.getSize() / 1024
            String filename = uploadedFile.getOriginalFilename()
            shareFileInstance.ext = filename.substring(filename.lastIndexOf(".") + 1)
            shareFileInstance.contentType = uploadedFile.getContentType()
            shareFileInstance.creator = getCurrentUser()
        } else {
            render(view: "create", model: [shareFileInstance: shareFileInstance])
            return
        }
        if (!shareFileInstance.save(flush: true)) {
            render(view: "create", model: [shareFileInstance: shareFileInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), shareFileInstance.id])
        redirect(action: "show", id: shareFileInstance.id)
    }

    def show() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        [shareFileInstance: shareFileInstance]
    }

    def edit() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        [shareFileInstance: shareFileInstance]
    }

    def update() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (shareFileInstance.version > version) {
                shareFileInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'shareFile.label', default: 'ShareFile')] as Object[],
                        "Another user has updated this ShareFile while you were editing")
                render(view: "edit", model: [shareFileInstance: shareFileInstance])
                return
            }
        }

        shareFileInstance.properties = params

        if (!shareFileInstance.save(flush: true)) {
            render(view: "edit", model: [shareFileInstance: shareFileInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), shareFileInstance.id])
        redirect(action: "show", id: shareFileInstance.id)
    }

    def delete() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        try {
            shareFileInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

    def renderImage() {
        if (params.id) {
            def shareFileInstance = ShareFile.get(params.id)
            response.setContentLength(shareFileInstance.data?.length)
            response.outputStream.write(shareFileInstance.data)
            return
        } else {
            response.sendError(404)
        }
    }

    def downloadFile() {
        def shareFileInstance = ShareFile.get(params.id)
        if (shareFileInstance) {
            String downloadName = URLEncoder.encode(shareFileInstance.name + '.' + shareFileInstance.ext, 'UTF-8')
            response.setHeader("Content-Disposition", "attachment;filename=${downloadName}")
            response.setContentType("application-xdownload")
            response.getOutputStream() << new ByteArrayInputStream(shareFileInstance.data)
            return
        } else {
            render(view: '/error')
        }
    }

    private getCurrentUser() {
        User.get(springSecurityService.getPrincipal().id)
    }

}
