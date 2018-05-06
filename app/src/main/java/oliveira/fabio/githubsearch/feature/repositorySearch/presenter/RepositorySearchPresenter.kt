package oliveira.fabio.githubsearch.feature.repositorySearch.presenter

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

interface RepositorySearchPresenter {
    fun onCreate()
    fun getRepositoriesFromPage(page: Int)
    fun onDestroy()
}