package oliveira.fabio.githubsearch.feature.repositorySearch.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import oliveira.fabio.githubsearch.R
import oliveira.fabio.githubsearch.feature.pullsDetail.ui.activity.PullsDetailActivity
import oliveira.fabio.githubsearch.feature.repositorySearch.presenter.RepositorySearchPresenter
import oliveira.fabio.githubsearch.feature.repositorySearch.ui.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_repository_search.*
import kotlinx.android.synthetic.main.content_main.view.*
import javax.inject.Inject
import oliveira.fabio.githubsearch.feature.repositorySearch.di.component.DaggerRepositorySearchComponent
import oliveira.fabio.githubsearch.feature.repositorySearch.di.module.RepositorySearchModule
import oliveira.fabio.githubsearch.feature.repositorySearch.ui.listener.EndlessRecyclerViewScrollListener
import oliveira.fabio.githubsearch.model.Item
import android.view.animation.AnimationUtils

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

class RepositorySearchActivity : AppCompatActivity(), RepositorySearchView {

    @Inject
    lateinit var presenter: RepositorySearchPresenter

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    private lateinit var adapter: RepositoryAdapter

    private lateinit var repositories: List<Item>

    private var lastPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_search)
        setSupportActionBar(toolbar)
        initDagger()
        presenter.onCreate()
    }

    override fun configureToolbarListener() {
        btn_try_again.setOnClickListener {
            presenter.onCreate()
            ll_error_view.visibility = View.GONE
        }
    }

    override fun configureScrollListener() {
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int) {
                presenter.getRepositoriesFromPage(page)
            }
        }
    }

    override fun setScrollListener() {
        ic_content.rv_repositories_list.addOnScrollListener(scrollListener)
    }

    override fun initRecyclerView(repositories: List<Item>) {
        this.repositories = repositories
        linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
        adapter = RepositoryAdapter(this.repositories)
        adapter.setOnItemClickListener( AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, PullsDetailActivity::class.java)
            intent.putExtra("CREATOR", this.repositories[position].owner.login)
            intent.putExtra("REPOSITORY", this.repositories[position].name)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
        })

        ic_content.rv_repositories_list.layoutManager = linearLayoutManager
        ic_content.rv_repositories_list.adapter = adapter
        ic_content.rv_repositories_list.visibility = View.VISIBLE
    }

    override fun refreshList(repositories: List<Item>) {
        adapter.refreshItems(repositories)
        lastPosition = this.repositories.size
        this.repositories += repositories
    }

    override fun showPlaceHolderLoading() {
        ic_content.fl_repositories_list.visibility = View.GONE
        ic_content.shimmer_view_container.visibility = View.VISIBLE
        ic_content.shimmer_view_container.startShimmerAnimation()
    }

    override fun hidePlaceHolderLoading() {
        ic_content.fl_repositories_list.visibility = View.VISIBLE
        ic_content.shimmer_view_container.stopShimmerAnimation()
        ic_content.shimmer_view_container.visibility = View.GONE
    }

    override fun showRecyclerViewLoading() {
        val slide = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        ic_content.progress_bar.visibility = View.VISIBLE
        ic_content.progress_bar.startAnimation(slide)
    }

    override fun hideRecyclerViewLoading() {
        ic_content.progress_bar.visibility = View.GONE
    }

    override fun scrollDown() {
        ic_content.rv_repositories_list.smoothScrollToPosition(lastPosition+1)
    }

    override fun showErrorView() {
        ll_error_view.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun initDagger(){
        DaggerRepositorySearchComponent.builder()
                .repositorySearchModule(RepositorySearchModule(this))
                .build().inject(this)
    }

}
