package com.example.profilecardxml.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.profilecardxml.R
import com.example.profilecardxml.data.profileListItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var profileList: List<profileListItem> = emptyList()
        val jsonFileString = this.resources.openRawResource(R.raw.profile).bufferedReader().use { it.readText() }
        val gson = Gson()
        val listProductType = object : TypeToken<List<profileListItem>>() {}.type
        profileList = gson.fromJson(jsonFileString, listProductType)

        val adapter = ProfileAdapter(profileList, view.context, requireActivity().supportFragmentManager)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }
}