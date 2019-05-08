package presenter

import base.BasePresenter
import bean.UserBean
import module.UserModule
import view.UserView

class UserPresenter : BasePresenter<UserView>() {
    private val userModule: UserModule

    init {
        this.userModule = UserModule()
    }

    fun getUser(userName: String) {
        userModule.getUser(userName, object : UserModule.CallBackListener {
            override fun onSuccess(userBean: UserBean) {
                if (view != null) {
                    view!!.onGetUser(userBean)
                }
            }

            override fun onFailure(t: Throwable) {

            }
        })
    }
}