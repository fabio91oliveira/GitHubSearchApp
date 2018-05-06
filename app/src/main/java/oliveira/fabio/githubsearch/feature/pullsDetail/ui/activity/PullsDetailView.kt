package oliveira.fabio.githubsearch.feature.pullsDetail.ui.activity

import oliveira.fabio.githubsearch.model.Pull

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

interface PullsDetailView {
    fun configureListeners()
    fun initRecyclerView(pulls: List<Pull>)
    fun loadCounters(pulls: List<Pull>)
    fun showLoading()
    fun hideLoading()
    fun showErrorView()
}