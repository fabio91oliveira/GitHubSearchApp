package oliveira.fabio.githubsearch.util

import java.text.SimpleDateFormat

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

object DateUtil {
    @JvmStatic
    fun convertDateFormat(date: String) : String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date = inputFormat.parse(date)

            val format = SimpleDateFormat("dd/MM/yyyy")
            format.format(date)
        } catch (e: Exception) {
            "--/--/----"
        }
    }
}