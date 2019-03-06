
package com.exercice.popu.model.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code_region",
    "code_arrondissement",
    "nom_de_la_region",
    "code_commune",
    "population_totale",
    "population_municipale",
    "code_insee",
    "code_canton",
    "code_departement",
    "coordonnees",
    "population_comptee_a_part",
    "nom_de_la_commune"
})
public class Fields {

    @JsonProperty("code_region")
    private String codeRegion;
    @JsonProperty("code_arrondissement")
    private String codeArrondissement;
    @JsonProperty("nom_de_la_region")
    private String nomDeLaRegion;
    @JsonProperty("code_commune")
    private String codeCommune;
    @JsonProperty("population_totale")
    private Integer populationTotale;
    @JsonProperty("population_municipale")
    private Integer populationMunicipale;
    @JsonProperty("code_insee")
    private String codeInsee;
    @JsonProperty("code_canton")
    private String codeCanton;
    @JsonProperty("code_departement")
    private String codeDepartement;
    @JsonProperty("coordonnees")
    private List<Double> coordonnees = null;
    @JsonProperty("population_comptee_a_part")
    private Integer populationCompteeAPart;
    @JsonProperty("nom_de_la_commune")
    private String nomDeLaCommune;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("code_region")
    public String getCodeRegion() {
        return codeRegion;
    }

    @JsonProperty("code_region")
    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }

    @JsonProperty("code_arrondissement")
    public String getCodeArrondissement() {
        return codeArrondissement;
    }

    @JsonProperty("code_arrondissement")
    public void setCodeArrondissement(String codeArrondissement) {
        this.codeArrondissement = codeArrondissement;
    }

    @JsonProperty("nom_de_la_region")
    public String getNomDeLaRegion() {
        return nomDeLaRegion;
    }

    @JsonProperty("nom_de_la_region")
    public void setNomDeLaRegion(String nomDeLaRegion) {
        this.nomDeLaRegion = nomDeLaRegion;
    }

    @JsonProperty("code_commune")
    public String getCodeCommune() {
        return codeCommune;
    }

    @JsonProperty("code_commune")
    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    @JsonProperty("population_totale")
    public Integer getPopulationTotale() {
        return populationTotale;
    }

    @JsonProperty("population_totale")
    public void setPopulationTotale(Integer populationTotale) {
        this.populationTotale = populationTotale;
    }

    @JsonProperty("population_municipale")
    public Integer getPopulationMunicipale() {
        return populationMunicipale;
    }

    @JsonProperty("population_municipale")
    public void setPopulationMunicipale(Integer populationMunicipale) {
        this.populationMunicipale = populationMunicipale;
    }

    @JsonProperty("code_insee")
    public String getCodeInsee() {
        return codeInsee;
    }

    @JsonProperty("code_insee")
    public void setCodeInsee(String codeInsee) {
        this.codeInsee = codeInsee;
    }

    @JsonProperty("code_canton")
    public String getCodeCanton() {
        return codeCanton;
    }

    @JsonProperty("code_canton")
    public void setCodeCanton(String codeCanton) {
        this.codeCanton = codeCanton;
    }

    @JsonProperty("code_departement")
    public String getCodeDepartement() {
        return codeDepartement;
    }

    @JsonProperty("code_departement")
    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    @JsonProperty("coordonnees")
    public List<Double> getCoordonnees() {
        return coordonnees;
    }

    @JsonProperty("coordonnees")
    public void setCoordonnees(List<Double> coordonnees) {
        this.coordonnees = coordonnees;
    }

    @JsonProperty("population_comptee_a_part")
    public Integer getPopulationCompteeAPart() {
        return populationCompteeAPart;
    }

    @JsonProperty("population_comptee_a_part")
    public void setPopulationCompteeAPart(Integer populationCompteeAPart) {
        this.populationCompteeAPart = populationCompteeAPart;
    }

    @JsonProperty("nom_de_la_commune")
    public String getNomDeLaCommune() {
        return nomDeLaCommune;
    }

    @JsonProperty("nom_de_la_commune")
    public void setNomDeLaCommune(String nomDeLaCommune) {
        this.nomDeLaCommune = nomDeLaCommune;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
