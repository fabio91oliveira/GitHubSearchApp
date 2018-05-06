package oliveira.fabio.githubsearch.feature.pullsDetail.di.module

import oliveira.fabio.githubsearch.feature.pullsDetail.interactor.PullsDetailInteractor
import oliveira.fabio.githubsearch.feature.pullsDetail.presenter.PullsDetailPresenter
import oliveira.fabio.githubsearch.feature.pullsDetail.presenter.impl.PullsDetailPresenterImpl
import oliveira.fabio.githubsearch.feature.pullsDetail.ui.activity.PullsDetailView
import dagger.Module
import dagger.Provides

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

@Module
class PullsDetailModule(private val view: PullsDetailView) {
    @Provides
    fun provideView() : PullsDetailView {
        return this.view
    }
    @Provides
    fun providePresenter(interactor: PullsDetailInteractor) : PullsDetailPresenter {
        return PullsDetailPresenterImpl(view, interactor)
    }
}