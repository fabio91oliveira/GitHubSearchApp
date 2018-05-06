package oliveira.fabio.githubsearch.feature.pullsDetail.presenter.impl

import oliveira.fabio.githubsearch.feature.pullsDetail.interactor.PullsDetailInteractor
import oliveira.fabio.githubsearch.feature.pullsDetail.presenter.PullsDetailPresenter
import oliveira.fabio.githubsearch.feature.pullsDetail.ui.activity.PullsDetailView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

class PullsDetailPresenterImpl(private val view: PullsDetailView, private val interactor: PullsDetailInteractor): PullsDetailPresenter {

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(creator: String, repository: String) {
        view.configureListeners()

        compositeDisposable.add(interactor.findPulls(creator, repository)
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .subscribe ({
                    view.initRecyclerView(it)
                    view.loadCounters(it)
                }, {onError()}))
    }

    override fun onDestroy() {
        if(!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun onError() {
        view.showErrorView()
    }
}