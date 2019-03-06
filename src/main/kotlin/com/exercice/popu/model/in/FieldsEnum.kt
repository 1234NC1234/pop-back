package com.exercice.popu.model.`in`

enum class FieldsEnum(val value: String, val isFilter:Boolean) {
    COMMUNE_NAME("commune", true),
    POPULATION("pop", true),
    INSEE_CODE("insee_code", true),
    DEPARTMENT_CODE("dep", true),
    ROWS("rows", true),
    INDEX("lastIndex", false),
    SORT("sort", false);

    companion object {
        fun getFieldsNames(): Set<String> {
            return FieldsEnum.values().map { x -> x.value }.toSet()
        }

        fun getByFieldName(fieldName: String): FieldsEnum {
            return FieldsEnum.values().first { x -> fieldName.equals(x.value) }
        }
    }

}