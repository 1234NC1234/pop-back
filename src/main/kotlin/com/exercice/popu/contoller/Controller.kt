package com.exercice.popu.contoller

import com.exercice.popu.contoller.annotation.FieldConstraint
import com.exercice.popu.contoller.util.RequestMapper
import com.exercice.popu.model.`in`.FieldsEnum
import com.exercice.popu.model.`in`.Request
import com.exercice.popu.model.out.Response
import com.exercice.popu.service.PopulationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@Validated
class HtmlController {
    @Autowired
    lateinit private var populationService: PopulationService;

    @GetMapping("/all")
    fun getAllCommunes(@RequestParam @FieldConstraint allParams: Map<String, String>): ResponseEntity<Response> {
        val request = RequestMapper.toRequestObject(allParams)
        return ResponseEntity(populationService.getPopulationData(request), HttpStatus.OK)
    }

    @GetMapping("/commune/{commune}")
    fun getCommunebyName(@PathVariable commune: String): ResponseEntity<Response> {
        val request = Request(null, Pair(FieldsEnum.POPULATION, -1), mapOf(FieldsEnum.COMMUNE_NAME to commune))
        return ResponseEntity(populationService.getPopulationData(request), HttpStatus.OK)
    }

}