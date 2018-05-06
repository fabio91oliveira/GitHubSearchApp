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
data class Owner (
        @SerialName("login") val login: String,
        @SerialName("avatar_url") val avatarUrl: String
)