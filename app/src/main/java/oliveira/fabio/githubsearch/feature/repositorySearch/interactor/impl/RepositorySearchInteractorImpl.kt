package oliveira.fabio.githubsearch.feature.repositorySearch.interactor.impl

import oliveira.fabio.githubsearch.feature.repositorySearch.interactor.RepositorySearchInteractor
import oliveira.fabio.githubsearch.model.Repository
import oliveira.fabio.githubsearch.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositorySearchInteractorImpl(private val api: Api): RepositorySearchInteractor {

    override fun findRepository(page: Int): Observable<Repository> {
        return api.findRepository(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}