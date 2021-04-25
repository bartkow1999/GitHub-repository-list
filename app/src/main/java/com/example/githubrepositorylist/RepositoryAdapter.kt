package com.example.githubrepositorylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RepositoryAdapter(val repositoryList: ArrayList<RepositoryShort>, val itemClickHandler: (Int) -> Unit) :
        RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val repositoryNameTextView: TextView = itemView.findViewById(R.id.repositoryNameTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val languageTextView: TextView = itemView.findViewById(R.id.languageTextView)

        fun bind(name: String, description: String?, language: String?) {
            repositoryNameTextView.text = name
            descriptionTextView.text = description
            languageTextView.text = language
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val headerView = LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        val headerViewHolder = RepositoryViewHolder(headerView)
        headerView.setOnClickListener {
            itemClickHandler.invoke(headerViewHolder.adapterPosition)
        }
        return headerViewHolder
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(
                repositoryList[position].name,
                repositoryList[position].description,
                repositoryList[position].language
        )
    }
}