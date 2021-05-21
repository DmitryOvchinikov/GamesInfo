package com.classy.gamesinfo.data.model

data class IgdbAccess (
    val access_token: String,
    val expires_in: Int,
    val token_type: String
) {
    constructor() : this("",-1,"")
}

