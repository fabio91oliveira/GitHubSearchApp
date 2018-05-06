package oliveira.fabio.githubsearch.feature.repositorySearch.ui.activity

import oliveira.fabio.githubsearch.model.Item

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

interface RepositorySearchView {
    fun configureToolbarListener()
    fun configureScrollListener()
    fun setScrollListener()
    fun initRecyclerView(repositories: List<Item>)
    fun refreshList(repositories: List<Item>)
    fun showPlaceHolderLoading()
    fun hidePlaceHolderLoading()
    fun showRecyclerViewLoading()
    fun hideRecyclerViewLoading()
    fun scrollDown()
    fun showErrorView()
}