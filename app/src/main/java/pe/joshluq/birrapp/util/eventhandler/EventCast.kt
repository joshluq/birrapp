package pe.joshluq.birrapp.util.eventhandler

fun <E> E.toSingleEvent() = SingleEvent(this)
fun <E> List<E>.toSingleEvent() = SingleEvent(this)
