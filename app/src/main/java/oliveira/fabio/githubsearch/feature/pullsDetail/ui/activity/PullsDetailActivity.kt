package oliveira.fabio.githubsearch.feature.pullsDetail.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import oliveira.fabio.githubsearch.R
import oliveira.fabio.githubsearch.feature.pullsDetail.di.component.DaggerPullsDetailComponent
import oliveira.fabio.githubsearch.feature.pullsDetail.di.module.PullsDetailModule
import oliveira.fabio.githubsearch.feature.pullsDetail.presenter.PullsDetailPresenter
import oliveira.fabio.githubsearch.feature.pullsDetail.ui.adapter.PullAdapter
import oliveira.fabio.githubsearch.model.Pull
import oliveira.fabio.githubsearch.util.DialogUtil
import kotlinx.android.synthetic.main.activity_pulls_detail.*
import kotlinx.android.synthetic.main.content_pulls.*
import kotlinx.android.synthetic.main.content_pulls.view.*
import javax.inject.Inject

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

class PullsDetailActivity: AppCompatActivity(), PullsDetailView {

    @Inject
    lateinit var presenter: PullsDetailPresenter

    private lateinit var creator: String
    private lateinit var repository: String

    private var openedCount = 0
    private var closedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulls_detail)
        setSupportActionBar(toolbar)
        initDagger()

        creator = intent.extras.getString("CREATOR")
        repository = intent.extras.getString("REPOSITORY")
        presenter.onCreate(creator, repository)
        loadRepositoryTitle(repository)
    }

    override fun configureListeners() {
        toolbar.setNavigationOnClickListener {
            this.finish()
        }
        btn_try_again.setOnClickListener {
            presenter.onCreate(creator, repository)
            ll_error_view.visibility = View.GONE
        }
    }

    override fun initRecyclerView(pulls: List<Pull>) {
        val adapter = PullAdapter(pulls)

        ic_content.rv_pulls_list.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
        ic_content.rv_pulls_list.adapter = adapter
        ic_content.rv_pulls_list.visibility = View.VISIBLE
        adapter.setOnItemClickListener( AdapterView.OnItemClickListener { _, _, position, _ ->
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pulls[position].htmlUrl))
                startActivity(browserIntent)
            } catch (e: Exception) {
                DialogUtil.showErrorConnectionDialog(this@PullsDetailActivity,
                        resources.getString(R.string.internet_error_pull_open),
                        DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })
            }
        })
    }

    override fun loadCounters(pulls: List<Pull>) {
        pulls.forEach {
            if(it.state == STATUS_OPEN) {
                openedCount++
            } else {
                closedCount++
            }
        }

        ic_content.tv_opened.text = this.getString(R.string.pulls_detail_opened, openedCount.toString())
        ic_content.tv_closed.text = this.getString(R.string.pulls_detail_closed, closedCount.toString())
    }

    override fun showLoading() {
        ll_principal.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        ll_principal.visibility = View.VISIBLE
    }

    override fun showErrorView() {
        ll_error_view.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun initDagger(){
        DaggerPullsDetailComponent.builder()
                .pullsDetailModule(PullsDetailModule(this))
                .build()
                .inject(this)
    }

    private fun loadRepositoryTitle(title: String) {
        toolbar.title = title
    }

    companion object {
        const val STATUS_OPEN = "open"
    }

}