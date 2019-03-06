package com.exercice.popu.contoller.util

import com.exercice.popu.model.`in`.FieldsEnum
import com.exercice.popu.model.`in`.Request

object RequestMapper {

    private val SORT_PREFIX = "-"

    fun toRequestObject(allParams: Map<String, String>): Request {
        val filters: Map<FieldsEnum, String> = allParams
                .mapKeys { entry -> FieldsEnum.getByFieldName(entry.key) }
                .filter { entry -> entry.key.isFilter }

        val lastIndex: String? = allParams.get(FieldsEnum.INDEX.value)
        val lastIndexValue: Int? = if (lastIndex != null) {
            lastIndex.toInt()
        } else {
            null
        }

        val sortValue = allParams.get(FieldsEnum.SORT.value)
        val orderPair: Pair<FieldsEnum, Int>? = if (sortValue != null) {
            val field = FieldsEnum.getByFieldName(sortValue.removePrefix(SORT_PREFIX))
            val orderInt = if (sortValue.startsWith(SORT_PREFIX)) {
                -1
            } else {
                1
            }
            Pair(field, orderInt)
        } else {
            null
        }

        return Request(lastIndexValue, orderPair, filters)
    }
}