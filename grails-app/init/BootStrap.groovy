class BootStrap {

    def init = { servletContext ->

        Author authorInstance = Author.findOrCreateByFirstName("John")
        if(!authorInstance.id) {
            authorInstance.lastName = "Snow"
            authorInstance.save()
        }

        Author authorInstance1 = Author.findOrCreateByFirstName("Micky")
        if(!authorInstance1.id) {
            authorInstance1.lastName = "Arthur"
            authorInstance1.save()
        }

        Map requestData1 = [name: "Gate Academy", amount: 100, author:[[firstName: "John", lastName: "Snow", id: 1],
                                                                       [firstName: "Micky", lastName: "Arthur", id: 2]]]

        Book bookInstance1 = new Book(requestData1)
        bookInstance1.author = authorInstance1

        bookInstance1.validate()

        if(bookInstance1.hasErrors()) {
            bookInstance1.errors.each {
                println it
            }
            return
        }
        bookInstance1.save()
    }
    def destroy = {
    }
}
