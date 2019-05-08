package view

import android.os.Bundle
import base.BaseMvpActivity
import bean.UserBean
import com.lucky.kotlinmvp.R
import kotlinx.android.synthetic.main.activity_main.*
import presenter.UserPresenter

class UserActivity : BaseMvpActivity<UserView, UserPresenter>(), UserView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter!!.getUser("JakeWharton")
    }

    override fun createPresenter(): UserPresenter {
        return UserPresenter()
    }

    override fun createView(): UserView {
        return this
    }

    override fun onGetUser(userBean: UserBean) {
        tv.text = userBean.login
    }
}

