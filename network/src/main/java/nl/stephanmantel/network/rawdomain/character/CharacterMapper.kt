package nl.stephanmantel.network.rawdomain.character

import nl.stephanmantel.domain.Character
import nl.stephanmantel.network.common.EssentialParamsMissingException
import nl.stephanmantel.network.common.Mapper

class CharacterMapper: Mapper<CharacterRaw, Character>() {

    override fun map(raw: CharacterRaw): Character {
        val name = raw.name ?: { throw EssentialParamsMissingException(listOf("name"), raw)}()
        return Character(
            name,
            mapBirthYear(raw.birthYear)
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

    private fun mapBirthYear(raw: String?): Float? {
        if (raw.isNullOrEmpty() || raw == "unknown") return null

        val regex = Regex("([0-9]+\\.?)+([A-Z]+)")
        val groups = regex.find(raw)?.groupValues
        return groups?.let {
            if (groups.size != 2) return null
            val year = groups[0].toFloat()
            val era = groups[1]

            val eraMultiplier = if (era == "BBY") -1 else 1
            year * eraMultiplier
        }
    }

}