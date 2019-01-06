package nl.stephanmantel.network.common

/**
 * Created by Stephan on 31/01/2018.
 * Abstract mapper to map from raw objects to their app side counterparts
 * This way we can spot backend data issues early and verify objects.
 * The mapper is responsible for mapping from a string status to en enum, for example,
 * or convert stings to integers, etc.
 */
abstract class Mapper<Raw, Refined> {

    /**
     * Access point for client classes to use the mapper.
     */
    fun apply(raw: Raw): Refined {
        assertEssentialParams(raw)
        return map(raw)
    }

    /**
     * Apply the map to a list of the raw items to return a list of refined items
     */
    fun apply(listOfRaw: List<Raw>): List<Refined> {
        return listOfRaw.map {
            apply(it)
        }
    }

    /**
     * Apply the map to a list of the raw items to return a list of refined items
     */
    fun applyNullable(listOfRaw: List<Raw>?): List<Refined>? {
        return listOfRaw?.map {
            apply(it)
        }
    }

    /**
     * execute the actual mapping
     */
    protected abstract fun map(raw: Raw): Refined

    /**
     * Assert that the essential parameters for the raw object are present. If somehow the received
     * Json from the backend is malformed,
     * we immediately spot that this was the issue in stead of debugging app code down the line.
     * If the conditions are not meat, a EssentialParamsMissingException is thrown
     */
    @Throws(EssentialParamsMissingException::class)
    protected abstract fun assertEssentialParams(raw: Raw)
}