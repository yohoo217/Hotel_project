package net.aotter

import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import net.aotter.repository.AdRepository
import org.jboss.logging.Logger
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.NotFoundException
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class GreetingResource {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var adRepository: AdRepository

    @CheckedTemplate(requireTypeSafeExpressions = false)  //  設定模板，server 在呼叫時會把全部的 template 都掃過，把符合的呼叫符合的網址
    object Templates {
        @JvmStatic
        external fun hello(): TemplateInstance  //  建立  hello  的模板
        @JvmStatic
        external fun ad(): TemplateInstance  //  建立  ad  的模板 像是做了一個
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_HTML)
    fun hello(): TemplateInstance {
        val rooms = adRepository.getAds()
        val titles: List<String> = adRepository.getAdTitles()
        val photos = adRepository.getAdPhotos()
        logger.info("$photos")
        return Templates.hello()
                .data("id", "")
                .data("title", titles.joinToString(", "))
                .data("description", "")
                .data("star", "")
                .data("imgSrc", "")
                .data("photos", photos)
    }

    @GET
    @Path("/ad/{id}")
    fun showAdById(@PathParam("id") id: String): TemplateInstance? {
        val titles: List<String> = adRepository.getAdTitles()
        val photos = adRepository.getAdPhotos()
        val photo = photos?.find { it["id"] == id }
        logger.info("$photos")

        adRepository.findAdById(id)

        return if (photo != null) {
            Templates.ad()
                .data("id", "")
                .data("title", titles.joinToString(", "))
                .data("description", "")
                .data("star", "")
                .data("imgSrc", "")
                .data("photos", photos)
        } else {
            throw NotFoundException()
        }
    }
}