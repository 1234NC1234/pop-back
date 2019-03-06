package com.exercice.popu.service.impl

import com.exercice.popu.api.PopulationApi
import com.exercice.popu.model.`in`.Request
import com.exercice.popu.model.data.PopulationApiResponse
import com.exercice.popu.model.out.Commune
import com.exercice.popu.model.out.Response
import com.exercice.popu.service.PopulationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PopulationServiceImpl : PopulationService {
    @Autowired
    lateinit private var api: PopulationApi;

    override fun getPopulationData(request: Request): Response? {
        val response: PopulationApiResponse? = api.callPopulationApi(request.sort, request.filters)

        if (response != null) {
            val communeList = response.records.map { x ->
                Commune(x.fields.nomDeLaCommune,
                        x.fields.populationTotale.toLong(),
                        x.fields.codeInsee,
                        x.fields.codeDepartement)
            }
            return Response(communeList, communeList.size - 1)
        }
        return null;
    }

}