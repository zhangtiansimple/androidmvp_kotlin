package net

import bean.UserBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("/users/{user}")
    abstract fun getUser(@Path("user") user: String): Observable<UserBean>
}