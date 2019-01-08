package nl.stephanmantel.network.rawdomain.film

import nl.stephanmantel.domain.Film
import nl.stephanmantel.network.common.EssentialParamsMissingException
import nl.stephanmantel.network.common.Mapper

class FilmMapper: Mapper<FilmRaw, Film>() {

    override fun map(raw: FilmRaw): Film {
        val episodeId = raw.episodeId ?: { throw EssentialParamsMissingException(listOf("episodeId"), raw)}()
        val title = raw.title ?: { throw EssentialParamsMissingException(listOf("title"), raw)}()
        return Film(
            episodeId,
            title
        )
    }

    override fun assertEssentialParams(raw: FilmRaw) {
        var missingParams = emptyList<String>()

        if (raw.episodeId == null) {
            missingParams += "episodeId"
        }

        if (raw.title.isNullOrEmpty()) {
            missingParams += "title"
        }

        if (missingParams.isNotEmpty()) {
            throw EssentialParamsMissingException(missingParams, raw)
        }
    }



}