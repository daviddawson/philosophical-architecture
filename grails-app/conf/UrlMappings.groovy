class UrlMappings {

	static mappings = {
        "/module/$action?/$documentId?"(controller:"document")
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"home")
        "500"(view:'/error')

    //runtime stub control
    "/domain/$domain/$action"(controller:"domain")
    "/domain/status"(controller:"domain", action:"status")

	}
}
