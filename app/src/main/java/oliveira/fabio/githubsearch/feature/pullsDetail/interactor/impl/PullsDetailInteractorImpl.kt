package oliveira.fabio.githubsearch.feature.pullsDetail.interactor.impl

import oliveira.fabio.githubsearch.feature.pullsDetail.interactor.PullsDetailInteractor
import oliveira.fabio.githubsearch.model.Pull
import oliveira.fabio.githubsearch.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PullsDetailInteractorImpl(private val api: Api): PullsDetailInteractor {

    override fun findPulls(creator: String, repository: String): Observable<List<Pull>> {
        return api.findPulls(creator, repository)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}