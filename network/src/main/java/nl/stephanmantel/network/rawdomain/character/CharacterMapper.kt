package nl.stephanmantel.network.rawdomain.character

import nl.stephanmantel.domain.Character
import nl.stephanmantel.network.common.EssentialParamsMissingException
import nl.stephanmantel.network.common.Mapper

class CharacterMapper: Mapper<CharacterRaw, Character>() {

    override fun map(raw: CharacterRaw): Character {
        val name = raw.name ?: { throw EssentialParamsMissingException(listOf("name"), raw)}()
        val birthYear = raw.birthYear ?: { throw EssentialParamsMissingException(listOf("birthYear"), raw)}()
        return Character(
            name,
            birthYear
        )
    }

    override fun assertEssentialParams(raw: CharacterRaw) {
        var missingParams = emptyList<String>()

        if (raw.name.isNullOrEmpty()) {
            missingParams += "name"
        }

        if (missingParams.isNotEmpty()) {
            throw EssentialParamsMissingException(missingParams, raw)
        }
    }



}