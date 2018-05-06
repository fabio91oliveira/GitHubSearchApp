package oliveira.fabio.githubsearch.feature.repositorySearch.presenter.impl

import oliveira.fabio.githubsearch.feature.repositorySearch.interactor.RepositorySearchInteractor
import oliveira.fabio.githubsearch.feature.repositorySearch.presenter.RepositorySearchPresenter
import oliveira.fabio.githubsearch.feature.repositorySearch.ui.activity.RepositorySearchView
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

class RepositorySearchPresenterImpl(private val view: RepositorySearchView, private val interactor: RepositorySearchInteractor): RepositorySearchPresenter {

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        view.configureToolbarListener()

        compositeDisposable.add(interactor.findRepository(INITIAL_PAGE)
                .doOnSubscribe { view.showPlaceHolderLoading() }
                .doOnTerminate { view.hidePlaceHolderLoading() }
                .subscribe ({
                    view.initRecyclerView(it.items.toMutableList())
                    view.configureScrollListener()
                    view.setScrollListener()
                }, {onError(it)}))
    }

    override fun getRepositoriesFromPage(page: Int) {
        compositeDisposable.add(interactor.findRepository(page)
                .doOnSubscribe { view.showRecyclerViewLoading() }
                .doOnTerminate { view.hideRecyclerViewLoading() }
                .doOnComplete { view.scrollDown() }
                .subscribe ({
                    view.refreshList(it.items.toMutableList())
                }, {onError(it)}))
    }

    override fun onDestroy() {
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }

    private fun onError(throwable: Throwable) {
        if(throwable is HttpException) {
            if(throwable.code() != BAD_REQUEST_CODE && throwable.code() != RESPONSE_ERROR_CODE) {
                view.showErrorView()
            }
        } else {
            view.showErrorView()
        }
    }

    companion object {
        const val INITIAL_PAGE = 1
        const val BAD_REQUEST_CODE = 403
        const val RESPONSE_ERROR_CODE = 422
    }
}