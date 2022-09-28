package pe.joshluq.birrapp.util.eventhandler

fun interface EventHandler<T> {

    fun onEventUnHandled(any: T)
}