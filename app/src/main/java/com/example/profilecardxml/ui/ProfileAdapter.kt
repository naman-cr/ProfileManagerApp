package com.example.profilecardxml.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.profilecardxml.R
import com.example.profilecardxml.data.profileListItem

class ProfileAdapter(
    private val profileList: List<profileListItem>,
    private val context: Context,
    val fragmentManager: FragmentManager
) : Adapter<ProfileAdapter.ProfileViewHolder>() {
    class ProfileViewHolder(itemView: View) : ViewHolder(itemView) {
        val btn: Button
        val textName: TextView
        val textRole: TextView
        val imageView: ImageView
        init {
            btn = itemView.findViewById(R.id.profilebutton)
            textName = itemView.findViewById(R.id.tvName)
            textRole = itemView.findViewById(R.id.tvRole)
            imageView = itemView.findViewById(R.id.imgProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_card, parent, false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return profileList.count()
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {

        holder.textName.text = profileList[position].name
        holder.textRole.text = profileList[position].role

        Glide.with(context)
            .load(profileList[position].imgSrc) // URL or image source
            .placeholder(R.drawable.ic_launcher_foreground) // Optional placeholder
            .error(R.drawable.ic_launcher_background) // Optional error image
            .transform(CircleCrop())
            .into(holder.imageView)

        val bundle = Bundle().apply {
            putString("name", profileList[position].name)
            putString("role", profileList[position].role)
            putString("companyName", profileList[position].companyName)
            putString("about", profileList[position].about)
            putString("imgSrc", profileList[position].imgSrc)
        }
        val personFragment = PersonFragment()
        personFragment.arguments = bundle
        holder.btn.setOnClickListener {
            fragmentManager.beginTransaction().replace(
                R.id.framelayout, personFragment).
            addToBackStack(null).commit()
        }
    }
}