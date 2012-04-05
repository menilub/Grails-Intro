package com.sharefile.model

import com.sharefile.auth.User

class ShareFile {

    private static final int TEN_MEG_IN_BYTES = 1024 * 1024 * 10

    String name
    String description
    Integer size
    String ext
    String contentType
    byte[] data
    User creator
    Date dateCreated
    Date lastUpdated

    static hasMany = [users: User]

    static constraints = {
        name(blank: false, maxSize: 50)
        description(blank: true)
        size(nullable: false)
        ext(blank: false, maxSize: 10)
        contentType(blank: false, maxSize: 10)
        data(nullable: false, size: 1..TEN_MEG_IN_BYTES)
    }

}
