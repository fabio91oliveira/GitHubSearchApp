package oliveira.fabio.githubsearch.feature.repositorySearch.ui.adapter

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
import oliveira.fabio.githubsearch.model.Item
import kotlinx.android.synthetic.main.view_repository_item.view.*

/**
 * Created by Fabio Oliveira
 * Email: fabio91oliveira@gmail.com
 * Mobile: +55 (21) 98191-4951
 * LinkedIn: https://www.linkedin.com/in/fabio91oliveira
 */

class RepositoryAdapter(private var repositories: List<Item>):
        RecyclerView.Adapter<RepositoryAdapter.CustomHolder>() {

    private var mOnItemClickListener: AdapterView.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_repository_item, parent, false)

        val layoutParams = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        itemView.layoutParams = layoutParams

        return CustomHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        holder.bindItems(repositories[position], this)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    fun refreshItems(repositories: List<Item>) {
        this.repositories += repositories
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: AdapterView.OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    private fun onItemTextHolderClick(itemHolder: CustomHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener!!.onItemClick(null, itemHolder.itemView,
                    itemHolder.adapterPosition, itemHolder.itemId)
        }
    }

    inner class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(item: Item, adapter: RepositoryAdapter) = with(itemView) {
            Glide.with(context).load(item.owner.avatarUrl).apply(RequestOptions.centerCropTransform()).transition(DrawableTransitionOptions.withCrossFade()).into(itemView.iv_avatar)
            itemView.tv_repository_name.text = item.fullName
            itemView.tv_repository_description.text = item.description
            itemView.tv_user_name.text = item.owner.login
            itemView.tv_repository_fork.text = item.forksCount.toString()
            itemView.tv_repository_watcher.text = item.watchersCount.toString()
            itemView.setOnClickListener { adapter.onItemTextHolderClick( this@CustomHolder) }

        }
    }
}
