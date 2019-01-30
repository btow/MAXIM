package ru.bww.app.testtask.model.response_json

data class ResponseJSONHello (
    val Message: String?,
    val Success: Boolean
) {
    override fun toString() =
            "{\n" +
                    "    \"Message\": \"${Message}\",\n" +
                    "    \"Success\": ${Success}\n" +
                    "}"
}
