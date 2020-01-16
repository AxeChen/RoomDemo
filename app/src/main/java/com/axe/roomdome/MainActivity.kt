package com.axe.roomdome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.axe.roomdome.bean.User
import com.axe.roomdome.db.AppDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var userList: MutableList<User> = mutableListOf()
    private var mDisposables: CompositeDisposable? = null
    private val listAdapter: ListAdapter by lazy {
        ListAdapter(this, userList, object : ClickListener {
            override fun deleteClick() {
                getUserList()
            }

            override fun updateClick(user: User) {
                var intent = Intent(this@MainActivity, UpdateUserActivity::class.java)
                intent.putExtra(UpdateUserActivity.INTENT_TAG_USER, user)
                startActivity(intent)
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDisposables = CompositeDisposable()
        initClickListener()
        initRecycler()
        getUserList()
    }

    private fun initClickListener() {
        tvAdd.setOnClickListener {
            startActivity(Intent(this, AddUserActivity::class.java))
        }
    }

    private fun initRecycler() {
        rvList?.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }
    }

    private fun getUserList() {
        var disposable =
            AppDataBase.getInstance(this).userDao().getUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userList.clear()
                    userList.addAll(it)
                    listAdapter.notifyDataSetChanged()
                }, {

                })
        mDisposables?.add(disposable)

    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposables?.clear()
    }

    override fun onResume() {
        super.onResume()
        try {
            getUserList()
        } catch (e: Exception) {
        }
    }
}
