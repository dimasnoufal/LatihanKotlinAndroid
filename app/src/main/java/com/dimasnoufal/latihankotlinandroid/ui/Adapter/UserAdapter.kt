package com.dimasnoufal.latihankotlinandroid.ui.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimasnoufal.latihankotlinandroid.databinding.ItemRowListBinding
import com.dimasnoufal.latihankotlinandroid.model.DataUserItem
import com.dimasnoufal.latihankotlinandroid.ui.UserDetailActivity

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewModel>() {

    val userList: MutableList<DataUserItem> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun addDataUser(list: List<DataUserItem>) {
        userList.clear()
        list.let { userList.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewModel {
        val binding = ItemRowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewModel(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewModel, position: Int) {
        holder.bindView(userList[position])

    }

    class UserViewModel(private val binding: ItemRowListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(userList: DataUserItem) = binding.run {
            tvNama.text = userList.namaLengkap
            itemView.setOnClickListener{
                onClickItemUser(userList)
            }
        }

        private fun onClickItemUser(userList: DataUserItem) {
            val activity = itemView.context
            val intent = Intent(activity, UserDetailActivity::class.java)
            intent.putExtra("id_user", userList.idUser.toString())
            activity.startActivity(intent)
        }
    }
}