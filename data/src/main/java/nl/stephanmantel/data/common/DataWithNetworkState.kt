package nl.stephanmantel.data.common

data class DataWithNetworkState<DataType> (
    val isFetching: Boolean,
    val data: DataType
)