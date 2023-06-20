package study

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
) {

    class Builder {

        private var name: String? = null
        private var company: String? = null
        private var skills: Skills? = null

        fun name(value: String) {
            name = value
        }

        fun company(value: String) {
            company = value
        }

        fun skills(block: Skills.Builder.() -> Unit) {
            skills = Skills.Builder().apply(block).build()
        }

        fun build(): Person {
            return Person(
                name.orEmpty(),
                company.orEmpty(),
                skills.orEmpty(),
            )
        }
    }
}

fun introduce(block: Person.Builder.() -> Unit): Person {
    return Person.Builder().apply(block).build()
}
