package oliveira.fabio.githubsearch.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

@Serializable
data class Repository(@SerialName("items") val items: Array<Item>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Repository

        if (!Arrays.equals(items, other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(items)
    }
}