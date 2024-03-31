package net.aotter.repository

import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import net.aotter.model.Ad
import org.jboss.logging.Logger
import java.net.URL
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class AdRepository {

    @Inject
    lateinit var logger: Logger

    private val SHEET_URL =
            "https://docs.google.com/spreadsheets/d/e/2PACX-1vTFSxyakG_CmwMV-mxmVuBft46P6ut897mIvRPn37HPZbunSe9wIg-pcAA5rwIUSuCjqSQoJEle-PL0/pub?output=csv"

    fun getAds(): List<Ad>? = readRemoteCSVMKII(SHEET_URL)
            ?.map {
                Ad(
                        it["id"],
                        it["title"],
                        it["star"],
                        it["desc"],
                        it["imgSrc"]
                )
            }

    fun getAdTitles(): List<String> {
        val ads = getAds()
        return ads?.mapNotNull { it.title } ?: emptyList()
    }

    fun getAdPhotos(): MutableList<Map<String, String?>>? = readRemoteCSVMKII(SHEET_URL)

    fun findAdById(id: String) = getAds()?.find { it.id == id }

    private fun readRemoteCSVMKII(url: String): MutableList<Map<String, String?>>? {
        return try {
            val objectMapper: ObjectMapper = CsvMapper()
            val schema: CsvSchema = CsvSchema.emptySchema().withHeader()
            val iterator: MappingIterator<Map<String, String?>> =
                    objectMapper.readerFor(Map::class.java).with(schema).readValues(URL(url))
            iterator.readAll()
        } catch (e: Exception) {
            logger.warn("readRemoteCSVMKII  failed", e)
            null
        }
    }
}