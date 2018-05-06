package oliveira.fabio.githubsearch.feature.repositorySearch.di.component

import com.google.gson.Gson
import oliveira.fabio.githubsearch.feature.di.module.InteractorModule
import oliveira.fabio.githubsearch.feature.repositorySearch.di.module.RepositorySearchModule
import oliveira.fabio.githubsearch.feature.repositorySearch.interactor.RepositorySearchInteractor
import oliveira.fabio.githubsearch.feature.repositorySearch.ui.activity.RepositorySearchActivity
import oliveira.fabio.githubsearch.network.di.module.ApiModule
import dagger.Component

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

@Component(modules = [(RepositorySearchModule::class), (ApiModule::class), (InteractorModule::class)])
interface RepositorySearchComponent {
    fun gson(): Gson
    fun repositorySearchInteractor(): RepositorySearchInteractor
    fun inject(activity: RepositorySearchActivity)
}