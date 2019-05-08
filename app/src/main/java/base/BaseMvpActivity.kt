package base

import android.os.Bundle

//不需要Mvp的Activity的直接继承BaseActivity
abstract class BaseMvpActivity<V : IBaseView, P : BasePresenter<V>> : BaseActivity() {

    var presenter: P? = null
        private set
    private var view: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (presenter == null) {
            presenter = createPresenter()
        }

        if (view == null) {
            view = createView()
        }

        if (presenter != null && view != null) {
            presenter!!.attachView(view!!)
        }
    }

    abstract fun createPresenter(): P

    abstract fun createView(): V

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null && view != null) {
            presenter!!.detachView()
        }
    }
}