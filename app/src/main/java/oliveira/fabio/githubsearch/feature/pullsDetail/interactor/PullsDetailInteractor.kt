package oliveira.fabio.githubsearch.feature.pullsDetail.interactor

import oliveira.fabio.githubsearch.model.Pull
import io.reactivex.Observable

interface PullsDetailInteractor {
    fun findPulls(creator: String, repository: String): Observable<List<Pull>>
}