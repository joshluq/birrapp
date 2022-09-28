package pe.joshluq.birrapp.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.birrapp.domain.ValidationResult
import pe.joshluq.birrapp.domain.model.Beer
import pe.joshluq.birrapp.domain.repository.BeerRepository
import javax.inject.Inject

class FindBeerByNameUseCase @Inject constructor(
    private val userRepository: BeerRepository
) : UseCase<String, List<@JvmSuppressWildcards Beer>> {

    override suspend fun invoke(params: String) = withContext(Dispatchers.IO) {
        return@withContext try {
            validateBeerName(params)
            userRepository.findBeerByName(params)
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }

    @Throws(ValidationResult::class)
    private fun validateBeerName(name: String) {
        if (name.isBlank()) throw ValidationResult.EmptyField()
        if (name.length > 30) throw ValidationResult.FieldSize()
    }
}