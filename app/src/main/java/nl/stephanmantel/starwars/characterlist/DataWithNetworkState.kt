package nl.stephanmantel.starwars.characterlist

data class DataWithNetworkState<DataType> (
    val isFetching: Boolean,
    val data: DataType
)