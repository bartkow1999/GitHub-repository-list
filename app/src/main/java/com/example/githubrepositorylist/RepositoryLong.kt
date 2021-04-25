package com.example.githubrepositorylist

data class RepositoryLong(
    val name: String,
    val description: String?,
    val language: String?,
    val default_branch: String,
    val subscribers_count: Int?,

    val created_at: String?,
    val updated_at: String?,
    val pushed_at: String?,
    val size: Int?,

    val has_issues: Boolean?,
    val has_wiki: Boolean?,
    val has_pages: Boolean?,

    val forks_count: Int?,
    val private: Boolean?,
    val archived: Boolean?
)