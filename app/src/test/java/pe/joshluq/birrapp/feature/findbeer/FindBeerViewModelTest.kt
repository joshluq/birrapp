package pe.joshluq.birrapp.feature.findbeer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pe.joshluq.birrapp.CoroutineTestRule
import pe.joshluq.birrapp.domain.ValidationResult
import pe.joshluq.birrapp.domain.model.Beer
import pe.joshluq.birrapp.domain.usecase.FindBeerByNameUseCase
import pe.joshluq.birrapp.getOrAwaitValue
import pe.joshluq.birrapp.testObserver
import pe.joshluq.birrapp.util.resource.ResourceProvider
import java.util.concurrent.TimeoutException

@RunWith(JUnit4::class)
class FindBeerViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()


    // Subject under test
    private lateinit var viewModel: FindBeerViewModel

    //Mock dependencies
    @MockK
    lateinit var mockFindBeerByNameUseCase: FindBeerByNameUseCase

    @MockK
    lateinit var mockResourceProvider: ResourceProvider

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = FindBeerViewModel(
            mockFindBeerByNameUseCase,
            mockResourceProvider
        )
    }


    @Test
    fun testFindBeerByNameWhenNameEmpty() {

        val expectedResult = Result.failure<List<Beer>>(ValidationResult.EmptyField())

        coEvery {
            mockFindBeerByNameUseCase("")
        } returns expectedResult

        // Act
        viewModel.findBeerByName("")

        val value =
            viewModel.isSearchFieldEmpty.getOrAwaitValue().getContentOrNull()

        // Verify
        TestCase.assertTrue(value)
    }

    @Test
    fun testFindBeerByNameWhenFailed() {

        // Assemble pre conditions

        val expectedResult = "error"
        val error = Result.failure<List<Beer>>(TimeoutException())

        coEvery {
            mockFindBeerByNameUseCase("ale")
        } returns error

        coEvery {
            mockResourceProvider.getString(any())
        } returns expectedResult

        // Act
        viewModel.findBeerByName("ale")

        val value = viewModel.errorMessage.getOrAwaitValue().getContentOrNull()

        // Verify
        TestCase.assertEquals(value, expectedResult)

    }


    @Test
    fun testFindBeerByNameWhenSuccessful() {

        // Assemble pre conditions
        val param = "param"
        val list = listOf(
            Beer(0, "foo", "bar", "foobar", 0.0)
        )
        val expectedResult = Result.success(list)

        coEvery {
            mockFindBeerByNameUseCase(param)
        } returns expectedResult

        // Act
        viewModel.findBeerByName(param)

        val value =
            viewModel.beerList.getOrAwaitValue().getContentOrNull()

        // Verify
        TestCase.assertTrue(value.isNotEmpty())

    }

}