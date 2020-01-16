package com.axe.roomdome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axe.roomdome.bean.User
import com.axe.roomdome.db.AppDataBase

class ListAdapter(
    private var context: Context,
    private var list: MutableList<User>,
    clickListener: ClickListener
) :
    RecyclerView.Adapter<ListViewHolder>() {

    private var clickListener: ClickListener? = null

    private var layoutInflater: LayoutInflater? = null

    init {
        layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        var view = layoutInflater?.inflate(R.layout.item_user, parent, false)!!



        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var user = list[position]
        holder.tvNum!!.setText(user.id.toString())
        holder.tvName!!.setText(user.name.toString())
        if (user.gender == 0) {
            holder.tvSex!!.setText("男")
        } else {
            holder.tvSex!!.setText("女")
        }
        holder.deleteTv!!.setOnClickListener {
            AppDataBase.getInstance(context).userDao().deleteUser(user)
            clickListener?.deleteClick()
        }
        holder.updateTv!!.setOnClickListener {
            clickListener?.updateClick(user)
        }
    }

}

interface ClickListener {
    fun deleteClick()
    fun updateClick(user:User)
}

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    public var tvNum: TextView? = null
    public var tvName: TextView? = null
    public var tvSex: TextView? = null
    public var updateTv: TextView? = null
    public var deleteTv: TextView? = null

    init {
        tvNum = view.findViewById(R.id.tvNum)
        tvName = view.findViewById(R.id.tvName)
        tvSex = view.findViewById(R.id.tvSex)
        updateTv = view.findViewById(R.id.tvUpdate)
        deleteTv = view.findViewById(R.id.tvDelete)
    }

}