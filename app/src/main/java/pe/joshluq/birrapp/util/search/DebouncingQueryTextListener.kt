package pe.joshluq.birrapp.util.search

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DebouncingQueryTextListener(
    lifecycle: Lifecycle,
    private val onDebouncingQueryTextChange: (String) -> Unit
) : SearchView.OnQueryTextListener {
    private val debouncePeriod: Long = 700

    private val coroutineScope = lifecycle.coroutineScope

    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            if (newText == null) return@launch
            if (newText.isNotBlank()) delay(debouncePeriod)
            onDebouncingQueryTextChange(newText)
        }
        return false
    }
}