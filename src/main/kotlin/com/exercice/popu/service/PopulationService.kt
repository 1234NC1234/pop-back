package com.exercice.popu.service

import com.exercice.popu.model.`in`.Request
import com.exercice.popu.model.out.Response

interface PopulationService {
    fun getPopulationData(request: Request): Response?
}