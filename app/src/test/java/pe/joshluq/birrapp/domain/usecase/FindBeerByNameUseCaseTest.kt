package pe.joshluq.birrapp.domain.usecase

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import pe.joshluq.birrapp.domain.ValidationResult
import pe.joshluq.birrapp.domain.model.Beer
import pe.joshluq.birrapp.domain.repository.BeerRepository

class FindBeerByNameUseCaseTest {

    // Subject under test
    private lateinit var useCase: FindBeerByNameUseCase

    //Mock dependencies
    @MockK
    lateinit var mockRepository: BeerRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = FindBeerByNameUseCase(mockRepository)
    }

    @Test
    fun testInvokeWhenSuccess() = runBlocking {

        // Assemble pre conditions
        val expectedResult = Result.success(emptyList<Beer>())
        val params = "foo"

        coEvery {
            mockRepository.findBeerByName(params)
        } returns expectedResult


        // Act
        val result = useCase(params)

        // Verify
        TestCase.assertEquals(expectedResult, result)
    }

    @Test
    fun testInvokeWhenNameEmpty() = runBlocking {

        // Assemble pre conditions
        val params = ""
        // Act
        val result = useCase(params)

        // Verify
        TestCase.assertTrue(result.isFailure)
        TestCase.assertTrue(result.exceptionOrNull() is ValidationResult.EmptyField)
    }

    @Test
    fun testInvokeWhenNameMaxSize() = runBlocking {

        // Assemble pre conditions
        val params =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

        // Act
        val result = useCase(params)

        // Verify
        TestCase.assertTrue(result.isFailure)
        TestCase.assertTrue(result.exceptionOrNull() is ValidationResult.FieldSize)
    }

}