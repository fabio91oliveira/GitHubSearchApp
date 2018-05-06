package oliveira.fabio.githubsearch.feature.pullsDetail.ui.adapter

import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import oliveira.fabio.githubsearch.R
import oliveira.fabio.githubsearch.model.Pull
import oliveira.fabio.githubsearch.util.DateUtil
import kotlinx.android.synthetic.main.view_pull_item.view.*

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

class PullAdapter(private var pulls: List<Pull>):
        RecyclerView.Adapter<PullAdapter.CustomHolder>() {

    private var mOnItemClickListener: AdapterView.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_pull_item, parent, false)

        val layoutParams = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        itemView.layoutParams = layoutParams

        return CustomHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        holder.bindItems(pulls[position], this)
    }

    override fun getItemCount(): Int {
        return pulls.size
    }

    private fun onItemTextHolderClick(itemHolder: CustomHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener!!.onItemClick(null, itemHolder.itemView,
                    itemHolder.adapterPosition, itemHolder.itemId)
        }
    }

    fun setOnItemClickListener(onItemClickListener: AdapterView.OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    inner class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(pull: Pull, adapter: PullAdapter) = with(itemView) {
            Glide.with(context).load(pull.user.avatarUrl).apply(RequestOptions.centerCropTransform()).transition(DrawableTransitionOptions.withCrossFade()).into(itemView.iv_avatar)
            itemView.tv_created_at.text = DateUtil.convertDateFormat(pull.createdAt)
            itemView.tv_pull_name.text = pull.title
            itemView.tv_user_name.text = pull.user.login
            itemView.tv_pull_description.text = pull.body
            itemView.setOnClickListener { adapter.onItemTextHolderClick( this@CustomHolder) }
        }
    }
}
