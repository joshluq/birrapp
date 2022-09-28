package pe.joshluq.birrapp.domain

sealed class ValidationResult : Throwable() {
    class EmptyField : ValidationResult()
    class FieldSize : ValidationResult()
}