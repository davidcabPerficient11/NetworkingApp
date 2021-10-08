package com.example.networkingapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networkingapp.data.Item
import com.example.networkingapp.data.RepoResult
import com.example.networkingapp.databinding.ItemRepoBinding
import com.squareup.picasso.Picasso


class RepoListAdapter(private val repoList: RepoResult) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val binding = ItemRepoBinding
                        .inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(repoList.items[position])
    }

    override fun getItemCount(): Int = repoList.items.size

    class ViewHolder(val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindRepo(repo: Item) {
            binding.apply {
                username.text = repo.owner.login.orEmpty()
                repoName.text = repo.fullName.orEmpty()
                repoDescription.text = repo.description.orEmpty()
                Picasso.get().load(repo.owner.avatarUrl).into(icon)
            }

        }
    }
}