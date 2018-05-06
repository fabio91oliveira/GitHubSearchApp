package oliveira.fabio.githubsearch.network

import oliveira.fabio.githubsearch.model.Pull
import oliveira.fabio.githubsearch.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

interface Api {

    @GET("/search/repositories?q=language:Java&sort=stars")
    fun findRepository(@Query("page") pageNumber: Int): Observable<Repository>

    @GET("/repos/{creator}/{repository}/pulls")
    fun findPulls(@Path("creator") creator: String, @Path("repository") repository: String): Observable<List<Pull>>
}