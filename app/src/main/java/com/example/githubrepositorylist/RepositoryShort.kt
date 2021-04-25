package com.example.githubrepositorylist

data class RepositoryShort(
        val name: String,
        val description: String?,
        val language: String?
) {
    override fun toString(): String {
        return "${name} ${description} ${language}"
    }
}