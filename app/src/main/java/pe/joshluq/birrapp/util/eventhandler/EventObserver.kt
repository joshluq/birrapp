package pe.joshluq.birrapp.util.eventhandler

import androidx.lifecycle.Observer

class EventObserver<T> private constructor(
    private val eventUnhandled: EventHandler<T>,
) : Observer<SingleEvent<T>> {

    override fun onChanged(event: SingleEvent<T>) =
        event.getContentIfNotHandled(eventUnhandled::onEventUnHandled)

    companion object {
        fun <T> create(eventUnhandled: EventHandler<T>) = EventObserver(eventUnhandled)
    }
}