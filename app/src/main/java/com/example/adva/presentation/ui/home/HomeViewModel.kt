package com.example.adva.presentation.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.adva.data.model.ImageItem
import com.example.adva.data.utils.Resource
import com.example.adva.domain.useCase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val imagesUseCase: GetImagesUseCase) : ViewModel() {
    private var _imagesList: MutableLiveData<Resource<Flow<PagingData<ImageItem>>>> =
        MutableLiveData()
    val imagesList: LiveData<Resource<Flow<PagingData<ImageItem>>>>
        get() = _imagesList

    init {
        getAllImages()

    }

    private fun getAllImages() {
        _imagesList.postValue(Resource.Loading())
        Log.d("Ahmed", ":init ")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Ahmed", ":IO ")

            try {
                val images = imagesUseCase.execute().data!!.cachedIn(viewModelScope)




                _imagesList.postValue(Resource.Success(images))


            } catch (e: Exception) {

                Log.e(TAG, "errorLoading: ${e.message}")
                _imagesList.postValue(Resource.Error("${e.message}"))
            }

            Log.d("Ahmed", "_imagesList: ${_imagesList.value}")
        }
    }


}