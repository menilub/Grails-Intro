package com.sharefile.model



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(ShareFileController)
@Mock(ShareFile)
class ShareFileControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/shareFile/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.shareFileInstanceList.size() == 0
        assert model.shareFileInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.shareFileInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.shareFileInstance != null
        assert view == '/shareFile/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/shareFile/show/1'
        assert controller.flash.message != null
        assert ShareFile.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/shareFile/list'


        populateValidParams(params)
        def shareFile = new ShareFile(params)

        assert shareFile.save() != null

        params.id = shareFile.id

        def model = controller.show()

        assert model.shareFileInstance == shareFile
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/shareFile/list'


        populateValidParams(params)
        def shareFile = new ShareFile(params)

        assert shareFile.save() != null

        params.id = shareFile.id

        def model = controller.edit()

        assert model.shareFileInstance == shareFile
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/shareFile/list'

        response.reset()


        populateValidParams(params)
        def shareFile = new ShareFile(params)

        assert shareFile.save() != null

        // test invalid parameters in update
        params.id = shareFile.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/shareFile/edit"
        assert model.shareFileInstance != null

        shareFile.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/shareFile/show/$shareFile.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        shareFile.clearErrors()

        populateValidParams(params)
        params.id = shareFile.id
        params.version = -1
        controller.update()

        assert view == "/shareFile/edit"
        assert model.shareFileInstance != null
        assert model.shareFileInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/shareFile/list'

        response.reset()

        populateValidParams(params)
        def shareFile = new ShareFile(params)

        assert shareFile.save() != null
        assert ShareFile.count() == 1

        params.id = shareFile.id

        controller.delete()

        assert ShareFile.count() == 0
        assert ShareFile.get(shareFile.id) == null
        assert response.redirectedUrl == '/shareFile/list'
    }
}
