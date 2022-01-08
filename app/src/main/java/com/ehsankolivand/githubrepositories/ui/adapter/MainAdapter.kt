package com.ehsankolivand.githubrepositories.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ehsankolivand.githubrepositories.base.BaseViewHolder
import com.ehsankolivand.githubrepositories.data_source.models.Repo
import com.ehsankolivand.githubrepositories.data_source.models.RepoSearchResponse
import com.ehsankolivand.githubrepositories.databinding.RepoRowBinding
import com.ehsankolivand.githubrepositories.ui.MainActivity


class MainAdapter(
    private val context: Context,
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var repoList = listOf<Repo>()

    interface OnTragoClickListener {

        fun onCocktailClick(cocktail: RepoSearchResponse, position: Int)
    }

    fun setRepoList(repoList: List<Repo>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = RepoRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(repoList[position])
        }
    }

    private inner class MainViewHolder(
        val binding: RepoRowBinding
    ) : BaseViewHolder<Repo>(binding.root) {

        override fun bind(item: Repo) = with(binding) {
            if (item.has_wiki)
            {
                this.root.setBackgroundColor(Color.BLUE)
            }else {
                this.root.setBackgroundColor(Color.TRANSPARENT)

            }


            txtRepo.text = item.fullName
            txtDescripcion.text = item.description
            txtOwner.text = item.owner.login
            txtSize.text = "${item.size}"

        }
    }
}