package com.pradeep.navigation.DataClass

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pradeep.navigation.Network.APIInterface

class CharaterPagingSource(val retrofit: APIInterface): PagingSource<Int, CharacterData>(){

    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage=params.key ?: FIRST_PAGE_INTEX
            val response=retrofit.getDataFromAPI(nextPage)
            var nextPageNumber: Int? =null
            if(response.info.next != null){
                val uri=Uri.parse(response.info.next)
                val nextpageQuery=uri.getQueryParameter("page")
                nextPageNumber= nextpageQuery?.toInt()
            }
            LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPageNumber)

        }catch (exception: Exception){
            LoadResult.Error(exception)

        }
    }

    companion object{
        private const val FIRST_PAGE_INTEX=1
    }

}