package oliveira.fabio.githubsearch.feature.splashScreen

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*
import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.WindowManager
import oliveira.fabio.githubsearch.R
import oliveira.fabio.githubsearch.feature.repositorySearch.ui.activity.RepositorySearchActivity
import android.support.v7.app.AlertDialog
import oliveira.fabio.githubsearch.util.NetworkUtil
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import oliveira.fabio.githubsearch.util.DialogUtil

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

class SplashScreenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val window = window
        val winParams = window.attributes
        winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        window.attributes = winParams
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val animation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump)
        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(anim: Animation) {}
            override fun onAnimationRepeat(anim: Animation) {}
            override fun onAnimationEnd(anim: Animation) {
                when(NetworkUtil.isConnected()) {
                    true -> proceedToHome()
                    false -> DialogUtil.showErrorConnectionDialog(this@SplashScreenActivity,
                            resources.getString(R.string.internet_error_home),
                            DialogInterface.OnClickListener { _, _ -> this@SplashScreenActivity.finish() }) }
            }
        })
        iv_splash.startAnimation(animation)
    }

    private fun proceedToHome() {
        Handler().postDelayed({
            val i = Intent(this, RepositorySearchActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {
        const val SPLASH_TIME_OUT = 1000
    }

}