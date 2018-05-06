package oliveira.fabio.githubsearch.feature.pullsDetail

import oliveira.fabio.githubsearch.feature.pullsDetail.interactor.PullsDetailInteractor
import oliveira.fabio.githubsearch.feature.pullsDetail.presenter.PullsDetailPresenter
import oliveira.fabio.githubsearch.feature.pullsDetail.presenter.impl.PullsDetailPresenterImpl
import oliveira.fabio.githubsearch.feature.pullsDetail.ui.activity.PullsDetailView
import oliveira.fabio.githubsearch.model.Pull
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

@RunWith(MockitoJUnitRunner::class)
class PullsDetailPresenterTest {

    @Mock private
    lateinit var view: PullsDetailView

    @Mock private
    lateinit var interactor: PullsDetailInteractor

    private lateinit var presenter: PullsDetailPresenter

    private val creator = "creator"
    private val repository = "repository"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PullsDetailPresenterImpl(view, interactor)
    }

    @Test
    fun should_execute_OnCreate() {
        val pullsList = ArrayList<Pull>()
        val responseObservable = Observable.just(pullsList.toList())

        Mockito.`when`(interactor.findPulls(creator, repository)).thenReturn(responseObservable)
        presenter.onCreate(creator, repository)

        Mockito.verify(view).configureListeners()
    }

    @Test
    fun should_execute_OnCreate_Error() {
        val responseObservable = Observable.error<List<Pull>> { Throwable() }

        Mockito.`when`(interactor.findPulls(creator, repository)).thenReturn(responseObservable)
        presenter.onCreate(creator, repository)

        Mockito.verify(view).showErrorView()
    }

    @Test
    fun should_Execute_onDestroy(){
        presenter.onDestroy()
    }

}