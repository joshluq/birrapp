package pe.joshluq.birrapp.data.datasource.remote.rest

import kotlinx.coroutines.Deferred
import retrofit2.Response

suspend fun <T> handleResponse(
    function: () -> Deferred<Response<T>>,
) = try {

    val result = function().await()

    when {
        result.isSuccessful.not() -> {
            Result.failure(Throwable(""))
        }
        result.body() == null -> {
            Result.failure(Throwable(""))
        }
        else -> {
            Result.success(result.body())
        }
    }
} catch (e: Exception) {
    Result.failure<T>(e)
}