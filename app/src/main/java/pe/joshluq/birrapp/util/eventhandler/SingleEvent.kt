package pe.joshluq.birrapp.util.eventhandler

class SingleEvent<T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(callback: (T) -> Unit) {
        if (hasBeenHandled.not()) {
            hasBeenHandled = true
            callback(content)
        }
    }

    fun getContentOrNull() = content
}