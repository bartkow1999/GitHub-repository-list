package com.example.githubrepositorylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var repositoryList: ArrayList<RepositoryShort>
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getReposButton: Button = findViewById(R.id.getReposButton)
        val usernameEditText: EditText = findViewById(R.id.usernameEditText)

        getReposButton.setOnClickListener() {
            username = usernameEditText.text.toString()
            val getRepositoriesThread = Thread() {
                run {
                    val url =
                        "https://api.github.com/users/${username}/repos?per_page=100?sort=full_name"
                    val body = URL(url).readText()
                    repositoryList = ArrayList(Klaxon().parseArray<RepositoryShort>(body))
                }
                runOnUiThread() {
                    val repositoriesRecyclerView: RecyclerView =
                        findViewById(R.id.reposRecyclerView)
                    repositoriesRecyclerView.adapter = RepositoryAdapter(
                        repositoryList,
                        this::onItemClickHandler
                    )
                }
            }
            getRepositoriesThread.start()
        }

    }

    private fun onItemClickHandler(position: Int) {
        val intent = Intent(this, DetailedRepositoryActivity()::class.java)
        intent.putExtra("username", username)
        intent.putExtra("repo_name", repositoryList[position].name)
        startActivity(intent)
    }

}