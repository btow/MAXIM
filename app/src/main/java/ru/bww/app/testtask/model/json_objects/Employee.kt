package ru.bww.app.testtask.model.json_objects

data class Employee(
    val ID: String,
    val Name: String,
    val Title: String,
    val Phone: String,
    val Email: String
) {
    override fun toString() = "{\n" +
            "    \"ID\": \"${ID}\",\n" +
            "    \"Name\": \"${Name}\",\n" +
            "    \"Title\": \"${Title}\",\n" +
            "    \"Phone\": \"${Phone}\",\n" +
            "    \"Email\": \"${Email}\"\n" +
            "}"
}
