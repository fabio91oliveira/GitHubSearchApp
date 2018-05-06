package oliveira.fabio.githubsearch.feature.repositorySearch.interactor

import oliveira.fabio.githubsearch.model.Repository
import io.reactivex.Observable

interface RepositorySearchInteractor {
    fun findRepository(page: Int): Observable<Repository>
}