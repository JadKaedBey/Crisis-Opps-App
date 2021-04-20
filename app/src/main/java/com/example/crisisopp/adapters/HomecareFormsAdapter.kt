package com.example.crisisopp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crisisopp.R
import com.example.crisisopp.home.models.HomecareForm
import com.example.crisisopp.home.viewmodel.HomeViewModel
import com.example.testingthings.history.FirestoreAdapter
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject


class HomecareFormsAdapter(private val homeViewModel: HomeViewModel) :
    FirestoreAdapter<HomecareFormsAdapter.HomeCareFormViewHolder>(homeViewModel.homecareQuerySelector()!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCareFormViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.homecare_recycler_items, parent, false)
        return HomeCareFormViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: HomeCareFormViewHolder, position: Int) {
        return holder.bind(getSnapshot(position))
    }
    inner class HomeCareFormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val personName: TextView = itemView.findViewById(R.id.full_name)
        val phoneNumber: TextView = itemView.findViewById(R.id.phone_number_holder)
        val placeOfResidency: TextView = itemView.findViewById(R.id.place_of_residency_home_care)
        val lastPcDate: TextView = itemView.findViewById(R.id.last_pcr_date)
        val approvedState: TextView = itemView.findViewById(R.id.approved_state)
        val requestedState: TextView = itemView.findViewById(R.id.requested_state)
        val rejectedState: TextView = itemView.findViewById(R.id.rejected_state)

        init {
            itemView.setOnClickListener(this)
        }
        fun bind(snapshot: DocumentSnapshot) {
            val form = snapshot.toObject<HomecareForm>()
            personName.text = form?.fullName
            phoneNumber.text = form?.phoneNumber
            placeOfResidency.text = form?.placeOfResidence
            lastPcDate.text = form?.lastPcrDate
            when(homeViewModel.getUserType())
            {
                "local" -> {
                    if(form?.farahApproval == 0){
                        requestedState.visibility = VISIBLE
                        rejectedState.visibility = GONE
                        approvedState.visibility = GONE
                    }else if(form?.farahApproval == -1) {
                        rejectedState.visibility = VISIBLE
                        approvedState.visibility = GONE
                        requestedState.visibility = GONE
                    }else if(form?.farahApproval == 1){
                        approvedState.visibility = VISIBLE
                        rejectedState.visibility = GONE
                        requestedState.visibility = GONE
                    }
                }
                "ainwzein" -> {
                    if(form?.ainWzeinApproval == 0){
                        requestedState.visibility = VISIBLE
                        rejectedState.visibility = GONE
                        approvedState.visibility = GONE
                    }else if(form?.ainWzeinApproval == -1) {
                        rejectedState.visibility = VISIBLE
                        approvedState.visibility = GONE
                        requestedState.visibility = GONE
                    }else if(form?.ainWzeinApproval == 1){
                        approvedState.visibility = VISIBLE
                        rejectedState.visibility = GONE
                        requestedState.visibility = GONE
                    }
                }
                "main" -> {
                    if(form?.mainApproval == 0){
                        requestedState.visibility = VISIBLE
                        rejectedState.visibility = GONE
                        approvedState.visibility = GONE
                    }else if(form?.mainApproval == -1) {
                        rejectedState.visibility = VISIBLE
                        approvedState.visibility = GONE
                        requestedState.visibility = GONE
                    }else if(form?.mainApproval == 1){
                        approvedState.visibility = VISIBLE
                        rejectedState.visibility = GONE
                        requestedState.visibility = GONE
                    }
                }
                "farah" ->{
                    if(form?.farahApproval == 0){
                        requestedState.visibility = VISIBLE
                        rejectedState.visibility = GONE
                        approvedState.visibility = GONE
                    }else if(form?.farahApproval == -1) {
                        rejectedState.visibility = VISIBLE
                        approvedState.visibility = GONE
                        requestedState.visibility = GONE
                    }else if(form?.farahApproval == 1){
                        approvedState.visibility = VISIBLE
                        rejectedState.visibility = GONE
                        requestedState.visibility = GONE
                    }
                }

            }
        }
        override fun onClick(v: View?) {
            getSnapshot(adapterPosition).toObject<HomecareForm>()?.let {
                homeViewModel.setSelectedHomeCareForm(it)
            }
        }
    }
}




