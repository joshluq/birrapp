package pe.joshluq.birrapp.domain.usecase

interface UseCase<I, O> {

    suspend operator fun invoke(params: I): Result<O>

}