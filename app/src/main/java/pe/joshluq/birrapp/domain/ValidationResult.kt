package pe.joshluq.birrapp.domain

sealed class ValidationResult : Throwable() {
    class EmptyField : ValidationResult()
    class FieldSize : ValidationResult()
    class InvalidFormat : ValidationResult()
}

inline fun ValidationResult.onEmptyFieldError(result: (ValidationResult) -> Unit) = when (this) {
    is ValidationResult.EmptyField -> {
        result(this)
        this
    }
    else -> this
}

inline fun ValidationResult.onSizeError(result: (ValidationResult) -> Unit) = when (this) {
    is ValidationResult.FieldSize -> {
        result(this)
        this
    }
    else -> this
}

inline fun ValidationResult.onInvalidFormatError(result: (ValidationResult) -> Unit) = when (this) {
    is ValidationResult.InvalidFormat -> {
        result(this)
        this
    }
    else -> this
}