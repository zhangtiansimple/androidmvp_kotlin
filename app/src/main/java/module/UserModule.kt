package module

import bean.UserBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.BaseSubscriber
import net.SourceFactory

class UserModule {

    interface CallBackListener {
        fun onSuccess(userBean: UserBean)

        fun onFailure(t: Throwable)
    }

    fun getUser(userName: String, listener: CallBackListener?) {
        SourceFactory.instance!!.create()
            .getUser(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseSubscriber<UserBean>() {

                override fun onSuccess(userBean: UserBean) {
                    listener?.onSuccess(userBean)
                }

                override fun onFailure(t: Throwable) {
                    listener?.onFailure(t)
                }
            })
    }
}