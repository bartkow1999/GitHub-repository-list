package com.example.githubrepositorylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.beust.klaxon.Klaxon
import java.net.URL

class DetailedRepositoryActivity : AppCompatActivity() {

    private lateinit var repositoryDetails: RepositoryLong

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_repository)

        val getRepositoryDetailsThread = Thread() {
            run {
                val username = intent.getStringExtra("username")
                val repositoryName = intent.getStringExtra("repo_name")

                val url = "https://api.github.com/repos/${username}/${repositoryName}"
                val body = URL(url).readText()
                repositoryDetails = Klaxon().parse<RepositoryLong>(body)!!
            }

            runOnUiThread() {
                findViewById<TextView>(R.id.repositoryNameDetailedTextView).text = repositoryDetails.name
                findViewById<TextView>(R.id.descriptionDetailedTextView).text = repositoryDetails.description
                findViewById<TextView>(R.id.languageDetailedTextView).text = repositoryDetails.language
                findViewById<TextView>(R.id.defaultBranchDetailedTextView).text = repositoryDetails.default_branch
                findViewById<TextView>(R.id.subscriberCountDetailedTextView).text = repositoryDetails.subscribers_count.toString()
                findViewById<TextView>(R.id.createdAtDetailedTextView).text = repositoryDetails.created_at
                findViewById<TextView>(R.id.updatedAtDetailedTextView).text = repositoryDetails.updated_at
                findViewById<TextView>(R.id.pushedAtDetailedTextView).text = repositoryDetails.pushed_at
                findViewById<TextView>(R.id.sizeDetailedTextView).text = repositoryDetails.size.toString()
                findViewById<TextView>(R.id.hasIssuesDetailedTextView).text = repositoryDetails.has_issues.toString()
                findViewById<TextView>(R.id.hasWikiDetailedTextView).text = repositoryDetails.has_wiki.toString()
                findViewById<TextView>(R.id.hasPagesDetailedTextView).text = repositoryDetails.has_pages.toString()
                findViewById<TextView>(R.id.forksCountDetailedTextView).text = repositoryDetails.forks_count.toString()
                findViewById<TextView>(R.id.privateDetailedTextView).text = repositoryDetails.private.toString()
                findViewById<TextView>(R.id.archivedDetailedTextView).text = repositoryDetails.archived.toString()
            }
        }

        getRepositoryDetailsThread.start()

    }
}