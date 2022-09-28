package pe.joshluq.birrapp.data.datasource.remote.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class BeerResponse(
    @JsonProperty("id") val id: Int?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("description") val description: String?,
    @JsonProperty("image_url") val imageUrl: String?,
    @JsonProperty("abv") val abv: Double?,
)