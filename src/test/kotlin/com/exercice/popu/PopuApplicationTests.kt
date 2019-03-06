package com.exercice.popu

import com.exercice.popu.api.util.PopulationAPIUtil
import com.exercice.popu.contoller.util.RequestMapper
import com.exercice.popu.model.`in`.FieldsEnum
import com.exercice.popu.model.`in`.Request
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class PopuApplicationTests {

    @Autowired
    lateinit var populationAPIUtil: PopulationAPIUtil

    @Test
    fun testEmptyFiltersAndOrderings() {
        assertEquals(populationAPIUtil.buildAPIUrl(null, emptyMap()).build(true).toUri().toURL().toString(), "https://public.opendatasoft.com/api/records/1.0/search/?dataset=population-francaise-communes-2014&q=&rows=40")
    }

    @Test
    fun testOneFilterAndEmptyOrderings() {
        val filterMap = mapOf(FieldsEnum.COMMUNE_NAME to "toto")
        assertEquals(populationAPIUtil.buildAPIUrl(null, filterMap).toUriString(), "https://public.opendatasoft.com/api/records/1.0/search/?dataset=population-francaise-communes-2014&q=nom_de_la_commune%3Dtoto&rows=40")
    }

    @Test
    fun testManyFiltersAndEmptyOrderings() {
        val filterMap = mapOf(FieldsEnum.COMMUNE_NAME to "toto") +
                mapOf(FieldsEnum.DEPARTMENT_CODE to "77") +
                mapOf(FieldsEnum.INSEE_CODE to "12345")

        assertEquals(populationAPIUtil.buildAPIUrl(null, filterMap).toUriString(), "https://public.opendatasoft.com/api/records/1.0/search/?dataset=population-francaise-communes-2014&q=nom_de_la_commune%3Dtoto+code_departement%3D77+code_insee%3D12345&rows=40")
    }

    @Test
    fun testEmptyFiltersAndOrderingsDesc() {
        assertEquals(populationAPIUtil.buildAPIUrl(Pair(FieldsEnum.POPULATION, -1), emptyMap()).toUriString(), "https://public.opendatasoft.com/api/records/1.0/search/?dataset=population-francaise-communes-2014&sort=-population_totale&q=&rows=40")
    }

    @Test
    fun testEmptyFiltersAndOrderingsAsc() {
        assertEquals(populationAPIUtil.buildAPIUrl(Pair(FieldsEnum.POPULATION, 1), emptyMap()).toUriString(), "https://public.opendatasoft.com/api/records/1.0/search/?dataset=population-francaise-communes-2014&sort=population_totale&q=&rows=40")
    }

    @Test
    fun testManyFiltersAndOneOrderingAsc() {
        val filterMap = mapOf(FieldsEnum.COMMUNE_NAME to "toto") +
                mapOf(FieldsEnum.DEPARTMENT_CODE to "77") +
                mapOf(FieldsEnum.INSEE_CODE to "12345")

        assertEquals(populationAPIUtil.buildAPIUrl(Pair(FieldsEnum.POPULATION, 1), filterMap).toUriString(), "https://public.opendatasoft.com/api/records/1.0/search/?dataset=population-francaise-communes-2014&sort=population_totale&q=nom_de_la_commune%3Dtoto+code_departement%3D77+code_insee%3D12345&rows=40")
    }

    @Test
    fun testManyFiltersAndOneOrderingDesc() {
        val filterMap = mapOf(FieldsEnum.COMMUNE_NAME to "toto") +
                mapOf(FieldsEnum.DEPARTMENT_CODE to "77") +
                mapOf(FieldsEnum.INSEE_CODE to "12345")

        assertEquals(populationAPIUtil.buildAPIUrl(Pair(FieldsEnum.POPULATION, -1), filterMap).toUriString(), "https://public.opendatasoft.com/api/records/1.0/search/?dataset=population-francaise-communes-2014&sort=-population_totale&q=nom_de_la_commune%3Dtoto+code_departement%3D77+code_insee%3D12345&rows=40")
    }


    @Test
    fun testRequestMapper() {
        val paramsMap: Map<String, String> = mapOf(FieldsEnum.COMMUNE_NAME.value to "noumea") +
                mapOf(FieldsEnum.DEPARTMENT_CODE.value to "77") +
                mapOf(FieldsEnum.ROWS.value to "10") +
                mapOf("sort" to "-pop") +
                mapOf("lastIndex" to "123")

        val request: Request = RequestMapper.toRequestObject(paramsMap)
        assertEquals(request.lastIndex, 123)
        if (request.filters != null && request.sort != null) {
            assertEquals(request.sort, Pair(FieldsEnum.POPULATION, -1))
            assertEquals(request.lastIndex, 123)

            val keys = listOf<FieldsEnum>(FieldsEnum.DEPARTMENT_CODE, FieldsEnum.COMMUNE_NAME, FieldsEnum.ROWS)

            assertEquals(request.filters.orEmpty().keys.containsAll(keys), true)
            assertEquals(request.filters.orEmpty().keys.contains(FieldsEnum.POPULATION), false)
            assertEquals(request.filters.orEmpty().keys.contains(FieldsEnum.INSEE_CODE), false)

            assertEquals(request.filters.orEmpty().get(FieldsEnum.COMMUNE_NAME), "noumea")
            assertEquals(request.filters.orEmpty().get(FieldsEnum.DEPARTMENT_CODE), "77")
            assertEquals(request.filters.orEmpty().get(FieldsEnum.ROWS), "10")
        } else {
            fail()
        }
    }

    @Test
    fun testRequestMapper2() {
        val paramsMap: Map<String, String> = mapOf(FieldsEnum.COMMUNE_NAME.value to "noumea") +
                mapOf(FieldsEnum.POPULATION.value to "12") +
                mapOf(FieldsEnum.INSEE_CODE.value to "12345") +
                mapOf("sort" to "pop") +
                mapOf("lastIndex" to "123")

        val request: Request = RequestMapper.toRequestObject(paramsMap)
        assertEquals(request.lastIndex, 123)
        if (request.filters != null && request.sort != null) {
            assertEquals(request.sort, Pair(FieldsEnum.POPULATION, 1))
            assertEquals(request.lastIndex, 123)

            val keys = listOf<FieldsEnum>(FieldsEnum.POPULATION, FieldsEnum.COMMUNE_NAME, FieldsEnum.INSEE_CODE)

            assertEquals(request.filters.orEmpty().keys.containsAll(keys), true)
            assertEquals(request.filters.orEmpty().keys.contains(FieldsEnum.DEPARTMENT_CODE), false)
            assertEquals(request.filters.orEmpty().keys.contains(FieldsEnum.ROWS), false)

            assertEquals(request.filters.orEmpty().get(FieldsEnum.POPULATION), "12")
            assertEquals(request.filters.orEmpty().get(FieldsEnum.INSEE_CODE), "12345")
        } else {
            fail()
        }
    }

}
