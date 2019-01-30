package ru.bww.app.testtask.model.response_json

import ru.bww.app.testtask.model.json_objects.Department

data class ResponseJSONStructure (
    val ID : String,
    val Name :  String,
    val Departments :  List<Department>
) {
    override fun toString(): String {
        var result = "{\n" +
                "    \"ID\": \"${ID}\"\n" +
                "    \"Name\": \"${Name}\"\n"
        if (Departments != null) {
            result += "    \"Departments\": [\n"
            var i = 0
            for (dep in Departments)
                result += "${if (i++ == 0) "" else ","}\n        ${dep}"
            result += "        ]\n"
        }
        result += "}"
        return result
    }
}
