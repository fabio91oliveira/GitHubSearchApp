package oliveira.fabio.githubsearch.feature.repositorySearch.di.module

import oliveira.fabio.githubsearch.feature.repositorySearch.interactor.RepositorySearchInteractor
import oliveira.fabio.githubsearch.feature.repositorySearch.presenter.RepositorySearchPresenter
import oliveira.fabio.githubsearch.feature.repositorySearch.presenter.impl.RepositorySearchPresenterImpl
import oliveira.fabio.githubsearch.feature.repositorySearch.ui.activity.RepositorySearchView
import oliveira.fabio.githubsearch.network.Api
import dagger.Module
import dagger.Provides

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

@Module
class RepositorySearchModule(private val view: RepositorySearchView) {
    @Provides
    fun provideView() : RepositorySearchView {
        return this.view
    }
    @Provides
    fun providePresenter(interactor: RepositorySearchInteractor) : RepositorySearchPresenter {
        return RepositorySearchPresenterImpl(view, interactor)
    }
}