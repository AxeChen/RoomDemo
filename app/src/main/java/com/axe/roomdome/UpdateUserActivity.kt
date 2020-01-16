package com.axe.roomdome

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.axe.roomdome.bean.User
import com.axe.roomdome.db.AppDataBase
import kotlinx.android.synthetic.main.activity_update_user.*

class UpdateUserActivity : AppCompatActivity() {

    companion object {
        const val INTENT_TAG_USER = "INTENT_USER"
    }

    private var user: User? = null

    private var gender: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)
        rgGender.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rbMan) {
                gender = 0
            } else {
                gender = 1
            }
        }
        tvBack.setOnClickListener { finish() }
        tvAdd.setOnClickListener {
            updateUser()
        }
        intent?.run {
            user = getParcelableExtra(INTENT_TAG_USER)
        }
        gender = user!!.gender
        if (gender == 0) {
            rbMan.isChecked = true
        } else {
            rbWoman.isChecked = true

        }
        setUserData()
    }

    private fun updateUser() {
        var name = edName.text.toString()
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show()
            return
        }

        if (gender == -1) {
            Toast.makeText(this, "请选择姓名", Toast.LENGTH_SHORT).show()
            return
        }


        user!!.name = name
        user!!.gender = gender

        try {
            AppDataBase.getInstance(this).userDao().updateUser(user!!)
            finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setUserData() {
        user?.run {
            tvNum.text = id.toString()
            edName.setText(name)
            if (gender == 0) {
                rbMan.isChecked = true
            } else {
                rbMan.isChecked = false
            }
        }
    }
}