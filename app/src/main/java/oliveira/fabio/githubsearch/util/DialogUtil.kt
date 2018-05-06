package oliveira.fabio.githubsearch.util

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

object DialogUtil {

    @JvmStatic
    fun showErrorConnectionDialog(context: Context, message: String, listener: DialogInterface.OnClickListener){
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.setPositiveButton("Ok", listener)
        builder.show()
    }

}