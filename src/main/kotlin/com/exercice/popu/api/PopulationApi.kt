package com.exercice.popu.api

import com.exercice.popu.model.`in`.FieldsEnum
import com.exercice.popu.model.data.PopulationApiResponse

interface PopulationApi {
    fun callPopulationApi(ordering: Pair<FieldsEnum, Int>?, filters: Map<FieldsEnum, String>?): PopulationApiResponse?
}