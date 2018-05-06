package oliveira.fabio.githubsearch.feature.di.module

import oliveira.fabio.githubsearch.feature.pullsDetail.interactor.PullsDetailInteractor
import oliveira.fabio.githubsearch.feature.pullsDetail.interactor.impl.PullsDetailInteractorImpl
import oliveira.fabio.githubsearch.feature.repositorySearch.interactor.RepositorySearchInteractor
import oliveira.fabio.githubsearch.feature.repositorySearch.interactor.impl.RepositorySearchInteractorImpl
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
class InteractorModule {

    @Provides
    fun provideRepositorySearchInteractor(api: Api): RepositorySearchInteractor {
        return RepositorySearchInteractorImpl(api)
    }

    @Provides
    fun providePullsDetailInteractor(api: Api): PullsDetailInteractor {
        return PullsDetailInteractorImpl(api)
    }

}