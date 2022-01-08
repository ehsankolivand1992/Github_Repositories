package com.ehsankolivand.githubrepositories.ui

import androidx.lifecycle.*
import com.ehsankolivand.githubrepositories.data_source.GithubRepository
import com.ehsankolivand.githubrepositories.data_source.models.RepoSearchResponse
import com.ehsankolivand.githubrepositories.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val githubRepository: GithubRepository):ViewModel() {

    private var _repo = MutableLiveData<List<RepoSearchResponse>>().apply { value = emptyList() }
    val repo: LiveData<List<RepoSearchResponse>> = _repo


     suspend fun fetchRep()
         = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = githubRepository.fatchData()))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }

    }


}