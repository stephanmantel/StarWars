package nl.stephanmantel.starwars.common

/**
 * Created by Stephan on 31/01/2018.
 * Class to group data with status for viewmodel to send data to view with
 */
data class Resource<T>(val status: Status, val data: T? = null, val error: Throwable? = null) {
    companion object {
        private val loading = Resource<Any>(Status.LOADING)

        @Suppress("UNCHECKED_CAST")
        fun <T> loading() = loading as Resource<T>
        fun <T> loading(data: T?) = Resource(Status.LOADING, data)
        fun <T> success(data: T) = Resource(Status.SUCCESS, data)
        fun <T> error(error: Throwable, data: T? = null) = Resource(Status.ERROR, data, error)

    }
}