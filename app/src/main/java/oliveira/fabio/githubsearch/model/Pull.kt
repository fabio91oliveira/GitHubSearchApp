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
data class Pull(
        @SerialName("html_url") val htmlUrl: String,
        @SerialName("user") val user: Owner,
        @SerialName("title") val title: String,
        @SerialName("state") val state: String,
        @SerialName("body") val body: String,
        @SerialName("created_at") val createdAt: String
)