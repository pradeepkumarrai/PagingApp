package com.pradeep.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradeep.navigation.ViewModel.StackViewModel
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.coroutines.flow.collectLatest

class PagingActivity : AppCompatActivity() {

    lateinit var adapterRickMorty: AdapterRickMorty
    lateinit var stackViewModel: StackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        stackViewModel=ViewModelProvider(this).get(StackViewModel::class.java)


        adapterRickMorty= AdapterRickMorty()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewList.layoutManager = layoutManager
        recyclerViewList.itemAnimator = DefaultItemAnimator()
        recyclerViewList.adapter = adapterRickMorty

        lifecycleScope.launchWhenCreated {
            stackViewModel.getDataList().collectLatest {
                adapterRickMorty.submitData(it)
            }
        }
    }
}