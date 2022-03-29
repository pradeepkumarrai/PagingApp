package com.pradeep.navigation.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pradeep.navigation.DataClass.CharacterData
import com.pradeep.navigation.DataClass.CharaterPagingSource
import com.pradeep.navigation.DataClass.RickAndMorty
import com.pradeep.navigation.Network.APIInterface
import com.pradeep.navigation.Network.RetrofitServices
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StackViewModel : ViewModel() {

    var apiInterface: APIInterface

    init {
        apiInterface = RetrofitServices.getRetrofitServices().create(APIInterface::class.java)

    }

    fun getDataList(): Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(pageSize = 34, maxSize = 200),
            pagingSourceFactory = { CharaterPagingSource(apiInterface) }).flow.cachedIn(viewModelScope)
    }
}