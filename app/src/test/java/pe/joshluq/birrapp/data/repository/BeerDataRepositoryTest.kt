package pe.joshluq.birrapp.data.repository

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import pe.joshluq.birrapp.data.datasource.remote.rest.ApiService
import pe.joshluq.birrapp.data.datasource.remote.rest.response.BeerResponse
import retrofit2.Response
import java.util.concurrent.TimeoutException

class BeerDataRepositoryTest {

    // Subject under test
    private lateinit var repository: BeerDataRepository

    //Mock dependencies
    @MockK
    lateinit var mockApi: ApiService


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = BeerDataRepository(mockApi)
    }

    @Test
    fun testAuthenticateWhenResponseSuccess() = runBlocking {

        // Assemble pre conditions
        val name = "foo"
        val mockResponse = mockk<Response<List<BeerResponse>>>()

        coEvery {
            mockResponse.isSuccessful
        } returns true

        coEvery {
            mockResponse.body()
        } returns listOf()

        coEvery {
            mockApi.getBeersAsync(name)
        } returns CompletableDeferred(mockResponse)

        // Act
        val result = repository.findBeerByName(name)

        // Verify
        TestCase.assertTrue(result.isSuccess)
    }

    @Test
    fun testAuthenticateWhenResponseFailed() = runBlocking {

        // Assemble pre conditions
        val name = "foo"
        val mockResponse = mockk<Response<List<BeerResponse>>>()

        coEvery {
            mockResponse.isSuccessful
        } returns false

        coEvery {
            mockApi.getBeersAsync(name)
        } returns CompletableDeferred(mockResponse)

        // Act
        val result = repository.findBeerByName(name)

        // Verify
        TestCase.assertTrue(result.isFailure)
    }

    @Test
    fun testAuthenticateWhenResponseBodyNull() = runBlocking {

        // Assemble pre conditions
        val name = "foo"
        val mockResponse = mockk<Response<List<BeerResponse>>>()

        coEvery {
            mockResponse.isSuccessful
        } returns true

        coEvery {
            mockResponse.body()
        } returns null

        coEvery {
            mockApi.getBeersAsync(name)
        } returns CompletableDeferred(mockResponse)

        // Act
        val result = repository.findBeerByName(name)

        // Verify
        TestCase.assertTrue(result.isFailure)
    }

    @Test
    fun testAuthenticateWhenResponseTimeOutException() = runBlocking {

        // Assemble pre conditions
        val name = "foo"
        val mockResponse = mockk<Response<List<BeerResponse>>>()

        coEvery {
            mockResponse.body()
        }.throws(TimeoutException())

        coEvery {
            mockApi.getBeersAsync(name)
        } returns CompletableDeferred(mockResponse)

        // Act
        val result = repository.findBeerByName(name)

        // Verify
        TestCase.assertTrue(result.isFailure)
    }


}