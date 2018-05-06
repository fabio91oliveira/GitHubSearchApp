package oliveira.fabio.githubsearch.feature.pullsDetail.presenter

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

interface PullsDetailPresenter {
    fun onCreate(creator: String, repository: String)
    fun onDestroy()
}