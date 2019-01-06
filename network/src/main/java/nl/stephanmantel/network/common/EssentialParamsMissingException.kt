package nl.stephanmantel.network.common

/**
 * Created by Stephan on 31/01/2018.
 * Custom Exception to indicate the issue that occurred had to do with essential parameters not
 * being present in the raw object.
 */
class EssentialParamsMissingException(missingParams: List<String>, rawObject: Any) :
    Throwable("The params: ${missingParams.joinToString()} are missing in received object: $rawObject")
