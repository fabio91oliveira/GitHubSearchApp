package oliveira.fabio.githubsearch.feature.repositorySearch

import oliveira.fabio.githubsearch.feature.repositorySearch.interactor.RepositorySearchInteractor
import oliveira.fabio.githubsearch.feature.repositorySearch.presenter.RepositorySearchPresenter
import oliveira.fabio.githubsearch.feature.repositorySearch.presenter.impl.RepositorySearchPresenterImpl
import oliveira.fabio.githubsearch.feature.repositorySearch.ui.activity.RepositorySearchView
import oliveira.fabio.githubsearch.model.Item
import oliveira.fabio.githubsearch.model.Owner
import oliveira.fabio.githubsearch.model.Repository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */
@RunWith(MockitoJUnitRunner::class)
class RepositorySearchPresenterTest {

    @Mock private
    lateinit var view: RepositorySearchView

    @Mock private
    lateinit var interactor: RepositorySearchInteractor

    private lateinit var presenter: RepositorySearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = RepositorySearchPresenterImpl(view, interactor)
    }

    @Test
    fun should_execute_OnCreate() {
        val owner = Owner("login", "avatarUrl")
        val item = Item(owner, "fullName", "name", "description", 1, 1)
        val items = Array(2, {item})
        val repository = Repository(items)

        val responseObservable = Observable.just(repository)
        Mockito.`when`(interactor.findRepository(Mockito.anyInt())).thenReturn(responseObservable)

        presenter.onCreate()

        Mockito.verify(view).configureToolbarListener()
        Mockito.verify(view).showPlaceHolderLoading()
        Mockito.verify(view).initRecyclerView(repository.items.toMutableList())
        Mockito.verify(view).configureScrollListener()
        Mockito.verify(view).setScrollListener()
        Mockito.verify(view).hidePlaceHolderLoading()
    }

    @Test
    fun should_getRepositoriesFromPage_Successfully(){
        val owner = Owner("login", "avatarUrl")
        val item = Item(owner, "fullName", "name", "description", 1, 1)
        val items = Array(2, {item})
        val repository = Repository(items)

        val responseObservable = Observable.just(repository)
        Mockito.`when`(interactor.findRepository(Mockito.anyInt())).thenReturn(responseObservable)

        presenter.getRepositoriesFromPage(Mockito.anyInt())

        Mockito.verify(view).showRecyclerViewLoading()
        Mockito.verify(view).refreshList(repository.items.toMutableList())
        Mockito.verify(view).hideRecyclerViewLoading()
        Mockito.verify(view).scrollDown()
    }

    @Test
    fun should_getRepositoriesFromPage_Error_Http_Not_Showing_View_Error(){
        val errorString = "Error"
        val response = Response.error<Repository>(403, ResponseBody.create(MediaType.parse("application/json"), errorString))
        val responseObservable = Observable.error<Repository> { HttpException(response) }
        Mockito.`when`(interactor.findRepository(Mockito.anyInt())).thenReturn(responseObservable)

        presenter.getRepositoriesFromPage(Mockito.anyInt())
    }

    @Test
    fun should_getRepositoriesFromPage_Error_Http_Showing_View_Error(){
        val errorString = "Error"
        val response = Response.error<Repository>(502, ResponseBody.create(MediaType.parse("application/json"), errorString))
        val responseObservable = Observable.error<Repository> { HttpException(response) }
        Mockito.`when`(interactor.findRepository(Mockito.anyInt())).thenReturn(responseObservable)

        presenter.getRepositoriesFromPage(Mockito.anyInt())
        Mockito.verify(view).showErrorView()
    }

    @Test
    fun should_getRepositoriesFromPage_Generic_Error(){
        val responseObservable = Observable.error<Repository> { Throwable() }
        Mockito.`when`(interactor.findRepository(Mockito.anyInt())).thenReturn(responseObservable)

        presenter.getRepositoriesFromPage(Mockito.anyInt())
        Mockito.verify(view).showErrorView()
    }

    @Test
    fun should_Execute_onDestroy(){
        presenter.onDestroy()
    }
}