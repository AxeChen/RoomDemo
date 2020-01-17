package com.axe.roomdome

import android.os.Bundle
import android.text.TextUtils
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.axe.roomdome.bean.User
import com.axe.roomdome.db.AppDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        initClickListener()
    }

    private fun initClickListener() {
        tvBack.setOnClickListener { finish() }
        tvAdd.setOnClickListener {
            addUser()
        }
        rgGender.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                if(checkedId==R.id.rbMan){
                    gender = 0
                }else{
                    gender = 1
                }
            }

        })
    }

    var gender = -1

    private fun addUser() {
        var name = edName.text.toString()
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show()
            return
        }

        if (gender == -1) {
            Toast.makeText(this, "请选择姓名", Toast.LENGTH_SHORT).show()
            return
        }


        var user = User(0, name, gender)
        try {
            AppDataBase.getInstance(this).userDao().insertUser(user)
            finish()
        } catch (e: Exception) {

        }
    }
}