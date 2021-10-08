package com.example.networkingapp.ui.activities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingapp.R
import com.example.networkingapp.api.RepositoryRetriever
import com.example.networkingapp.databinding.ActivityMainBinding
import com.example.networkingapp.ui.adapters.RepoListAdapter
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_NetworkingApp)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.repoList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.refreshButton.setOnClickListener {
            retrieveRepositories()

        }

        if (isNetworkConnected()){
            retrieveRepositories()

        }else{
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    private fun retrieveRepositories() {
        val mainActivityJob = Job()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage(exception.message)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)

        coroutineScope.launch(errorHandler) {
            val resultList = RepositoryRetriever().getRepositories()
            binding.repoList.adapter = RepoListAdapter(resultList)
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as
                ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

        return networkCapabilities != null && networkCapabilities
            .hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}