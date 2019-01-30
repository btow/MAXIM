package ru.bww.app.testtask.model.json_objects

data class Department(
    val ID: String,
    val Name: String,
    val Employees: List<Employee>,
    val Departments: List<Department>
) {
    override fun toString(): String {
        var result = "{\n" +
                "    \"ID\": \"${ID}\",\n" +
                "    \"Name\": \"${Name}\""
        if (Employees != null){
            result += ",\n    \"Employees\": [\n"
            var i = 0
            for (employ in Employees)
                result += "${if(i++ != 0)"," else ""}\n        ${employ}"
            result += "]"
        }
        if (Departments != null){
            result += ",\n    \"Employees\": [\n"
            var i = 0
            for (dep in Departments)
                result += "${if (i++ == 0) "" else ","}\n        ${dep}"
            result += "]\n"
        }
        result += "}"

        return result
    }
}
