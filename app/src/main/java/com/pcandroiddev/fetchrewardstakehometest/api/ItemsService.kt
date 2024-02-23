package com.pcandroiddev.fetchrewardstakehometest.api

import com.pcandroiddev.fetchrewardstakehometest.data.remote.Item
import com.pcandroiddev.fetchrewardstakehometest.util.Endpoints
import retrofit2.Response
import retrofit2.http.GET


interface ItemsService {

    @GET(Endpoints.HIRING)
    suspend fun getAllItems(): Response<List<Item>>

}