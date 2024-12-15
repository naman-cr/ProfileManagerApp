package com.example.profilecardxml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.profilecardxml.R
import com.example.profilecardxml.data.profileListItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PersonFragment: Fragment(R.layout.fragment_person) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val user_name = requireArguments().getString("name")
        val user_role = requireArguments().getString("role")
        val user_company = requireArguments().getString("companyName")
        val user_about = requireArguments().getString("about")
        val user_image = requireArguments().getString("imgSrc")



        val imageView: ImageView = view.findViewById(R.id.user_image)
        val textNameView: TextView = view.findViewById(R.id.user_name)
        val textRoleView: TextView = view.findViewById(R.id.user_role)
        val textCompanyView: TextView = view.findViewById(R.id.company_name)
        val textAboutView: TextView = view.findViewById(R.id.user_about)
        val btn: Button = view.findViewById(R.id.btn_share_profile)

        textNameView.text = user_name?.toUpperCase()
        textRoleView.text = user_role?.toUpperCase()
        textCompanyView.text = "Company Name - $user_company"
        textAboutView.text = "About - $user_about"

        Glide.with(requireContext())
            .load(user_image) // URL or image source
            .placeholder(R.drawable.ic_launcher_foreground) // Optional placeholder
            .error(R.drawable.ic_launcher_background) // Optional error image
            .transform(CircleCrop())
            .into(imageView)
        val shareProfileFragment = ShareProfileFragment()
        btn.setOnClickListener {
            fragmentManager.beginTransaction().replace(R.id.framelayout, shareProfileFragment)
                .addToBackStack(null).commit()
        }
    }
}