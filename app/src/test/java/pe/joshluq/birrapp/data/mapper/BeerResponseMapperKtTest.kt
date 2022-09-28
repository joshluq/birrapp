package pe.joshluq.birrapp.data.mapper

import junit.framework.TestCase
import org.junit.Test
import pe.joshluq.birrapp.data.datasource.remote.rest.response.BeerResponse

class BeerResponseMapperKtTest {

    @Test
    fun testTransformList() {
        // Assemble pre conditions
        val response = listOf(
            BeerResponse(0, "foo", "bar")
        )

        // Act
        val result = transformList(response)

        // Verify
        TestCase.assertEquals(result[0].id, response[0].id)
        TestCase.assertEquals(result[0].name, response[0].name)
        TestCase.assertEquals(result[0].description, response[0].description)
    }
}