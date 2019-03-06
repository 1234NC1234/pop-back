package com.exercice.popu.api.impl

import com.exercice.popu.api.PopulationApi
import com.exercice.popu.api.util.PopulationAPIUtil
import com.exercice.popu.model.`in`.FieldsEnum
import com.exercice.popu.model.data.PopulationApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Component
@Configuration
class PopulationApiImpl() : PopulationApi {

    @Autowired
    lateinit var populationAPIUtil: PopulationAPIUtil;

    override fun callPopulationApi(ordering: Pair<FieldsEnum, Int>?, filters: Map<FieldsEnum, String>?): PopulationApiResponse? {
        val callString = populationAPIUtil.buildAPIUrl(ordering, filters.orEmpty());
        val response = RestTemplate().getForEntity<PopulationApiResponse>(callString.build(false).toUri()).body
        return response;
    }
}