package pe.joshluq.birrapp.data.mapper

import junit.framework.TestCase
import org.junit.Test
import pe.joshluq.birrapp.data.datasource.remote.rest.response.BeerResponse

class BeerResponseMapperKtTest {

    @Test
    fun testTransformList() {
        // Assemble pre conditions
        val response = listOf(
            BeerResponse(0, "foo", "bar", "foobar")
        )

        // Act
        val result = transformList(response)

        // Verify
        TestCase.assertEquals(result.first().id, response.first().id)
        TestCase.assertEquals(result.first().name, response.first().name)
        TestCase.assertEquals(result.first().description, response.first().description)
        TestCase.assertEquals(result.first().imageUrl, response.first().imageUrl)
    }
}