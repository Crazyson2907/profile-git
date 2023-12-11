package com.example.profilegit.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.profilegit.data.db.RepoDao
import com.example.profilegit.data.model.Repository
import com.example.profilegit.network.ApiService
import javax.inject.Inject

class DetailUserRepository @Inject constructor(private val apiGithubService: ApiService, private val repoDao: RepoDao)  {
    suspend fun loadListUserRepo(login:String){
        apiGithubService.getRepos(login).map {
            Log.i("#########TAG", "DetailRepository: ${it.name} et ${it.html_url}", )
            repoDao.insertRepo(it)
        }
    }

    fun getUserRepoFromCache() : LiveData<List<Repository>> {
        return repoDao.getRepos()
    }

    companion object{
        private val TAG  = DetailUserRepository::class.java.simpleName
    }
}