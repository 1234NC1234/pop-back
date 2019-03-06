package com.exercice.popu.model.`in`

data class Request(val lastIndex:Int?, val sort:Pair<FieldsEnum, Int>?, val filters:Map<FieldsEnum, String>?)