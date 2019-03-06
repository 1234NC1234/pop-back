package com.exercice.popu.api.util

import com.exercice.popu.api.impl.PopulationAPIFilterMapping
import com.exercice.popu.model.`in`.FieldsEnum
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.util.UriComponentsBuilder

@Configuration
class PopulationAPIUtil {
    @Value("\${populationapi.url:}")
    lateinit var apiurl: String
    @Value("\${populationapi.rows:}")
    lateinit var DEFAULT_ROWS: String;
    val QUERY_STRING = "q"
    val SORT_DESC_STRING = "-"


    fun buildAPIUrl(ordering: Pair<FieldsEnum, Int>?, filters: Map<FieldsEnum, String>): UriComponentsBuilder {
        val url = UriComponentsBuilder.fromHttpUrl(apiurl);
        var sortSign = "";

        if (ordering != null) {
            if (ordering.second < 0) {
                sortSign = SORT_DESC_STRING
            }
            val fieldToOrder = PopulationAPIFilterMapping.getByField(ordering.first).apiField
            url.queryParam(PopulationAPIFilterMapping.SORT.apiField, "$sortSign$fieldToOrder")
        }

        url.queryParam(QUERY_STRING, fieldEnumToString(filters, url))
        url.queryParam(PopulationAPIFilterMapping.ROWS.apiField, DEFAULT_ROWS)

        return url
    }


    fun fieldEnumToString(mapToTransform: Map<FieldsEnum, Any>, url: UriComponentsBuilder): String {
        return mapToTransform.map { x -> "${PopulationAPIFilterMapping.getByField(x.key).apiField}=${x.value}" }.joinToString("+")
    }
}