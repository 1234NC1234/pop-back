package com.exercice.popu.api.impl

import com.exercice.popu.model.`in`.FieldsEnum

enum class PopulationAPIFilterMapping(val fieldEnum: FieldsEnum, val apiField: String) {
    COMMUNE(FieldsEnum.COMMUNE_NAME, "nom_de_la_commune"),
    INSEE(FieldsEnum.INSEE_CODE, "code_insee"),
    DEPARTEMENT(FieldsEnum.DEPARTMENT_CODE, "code_departement"),
    POPULATION(FieldsEnum.POPULATION, "population_totale"),
    ROWS(FieldsEnum.ROWS, "rows"),
    SORT(FieldsEnum.SORT, "sort");

    companion object {
        fun getByField(fieldEnum: FieldsEnum): PopulationAPIFilterMapping {
            return values().filter { x ->
                fieldEnum.equals(x.fieldEnum)
            }.first();
        }
    }
}