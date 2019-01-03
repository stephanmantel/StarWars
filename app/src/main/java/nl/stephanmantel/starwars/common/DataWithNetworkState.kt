package nl.stephanmantel.starwars.common

data class DataWithNetworkState<DataType> (
    val isFetching: Boolean,
    val data: DataType
)