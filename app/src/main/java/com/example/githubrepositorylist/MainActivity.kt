package com.example.githubrepositorylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            val threadGetRepositories = Thread() {
                run {
                    val url = "https://api.github.com/users/${username}/repos"
                    val body = URL(url).readText()
                    repositoryList = ArrayList(Klaxon().parseArray<RepositoryShort>(body))
                }
                runOnUiThread() {
                    val repositoriesRecyclerView: RecyclerView = findViewById(R.id.reposRecyclerView)
                    repositoriesRecyclerView.adapter = RepositoryAdapter(
                        repositoryList,
                        this::onItemClickHandler
                    )
                }
            }
            threadGetRepositories.start()
        }

    }

    private fun onItemClickHandler(position: Int) {
        Log.d("***", "$position")
    }

}