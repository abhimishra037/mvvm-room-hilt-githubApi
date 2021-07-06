package c.com.githubrepolist.ui

import android.app.Service
import android.content.Intent
import android.icu.util.TimeUnit
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import c.com.githubrepolist.R
import c.com.githubrepolist.model.GithubRepo
import c.com.githubrepolist.utility.DataState
import c.com.githubrepolist.worker.LoopApi
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), GithubRepoListAdapter.ItemClickListener {

    private val TAG = "MainActivity"
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GithubRepoListAdapter

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.rv_view)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        initWorker()

        subscribeObserver()
        viewModel.setStateEvent(MainStateEvent.GetGitHubEvents)
    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<GithubRepo>> -> {
                    Log.d(TAG, "dataStateSucc")

                    displayProgressBar(false)
                    setName(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }

            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            //  findViewById<TextView>(R.id.txt).text = message
        } else {
            // findViewById<TextView>(R.id.txt).text = "Unknown error"
        }
    }

    private fun displayProgressBar(isDisplay: Boolean) {
        Log.d(TAG, "working")
        findViewById<ProgressBar>(R.id.progressBar).visibility =
            if (isDisplay) View.VISIBLE else View.GONE
        Log.d(TAG, "notworking")

    }

    private fun setName(githubRepo: List<GithubRepo>) {
        Log.d(TAG, "setname")
        adapter = GithubRepoListAdapter(githubRepo, this)
        recyclerView.adapter = adapter
    }

    private fun initWorker() {
        val request =
            PeriodicWorkRequestBuilder<LoopApi>(15, java.util.concurrent.TimeUnit.MINUTES).build()
        WorkManager.getInstance(applicationContext).enqueue(request)
    }

    override fun onItemClick(name: String, url: String) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}