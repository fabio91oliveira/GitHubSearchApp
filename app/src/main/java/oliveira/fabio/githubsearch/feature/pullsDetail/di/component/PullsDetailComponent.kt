package oliveira.fabio.githubsearch.feature.pullsDetail.di.component

import com.google.gson.Gson
import oliveira.fabio.githubsearch.feature.di.module.InteractorModule
import oliveira.fabio.githubsearch.feature.pullsDetail.di.module.PullsDetailModule
import oliveira.fabio.githubsearch.feature.pullsDetail.interactor.PullsDetailInteractor
import oliveira.fabio.githubsearch.feature.pullsDetail.ui.activity.PullsDetailActivity
import oliveira.fabio.githubsearch.network.di.module.ApiModule
import dagger.Component

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

@Component(modules = [(PullsDetailModule::class), (ApiModule::class), (InteractorModule::class)])
interface PullsDetailComponent {
    fun gson(): Gson
    fun pullsDetailInteractor(): PullsDetailInteractor
    fun inject(activity: PullsDetailActivity)
}