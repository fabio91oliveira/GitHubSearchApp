package oliveira.fabio.githubsearch.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

@Serializable
data class Item(
        @SerialName("owner") val owner: Owner,
        @SerialName("full_name") val fullName: String,
        @SerialName("name") val name: String,
        @SerialName("description") val description: String,
        @SerialName("forks_count") val forksCount: Long,
        @SerialName("watchers_count") val watchersCount: Long
)