package com.pcandroiddev.fetchrewardstakehometest.repository

import android.util.Log
import com.pcandroiddev.fetchrewardstakehometest.api.ItemsService
import com.pcandroiddev.fetchrewardstakehometest.data.remote.Item
import com.pcandroiddev.fetchrewardstakehometest.util.APIResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemsService: ItemsService
) : ItemRepository {

    companion object {
        private const val TAG = "ItemRepositoryImpl"
    }

    override suspend fun getAllItems(): Flow<APIResult<List<Item>>> {
        return flow {
            emit(APIResult.Loading())
//            delay(5000L)
            val response = itemsService.getAllItems()
            try {
                if (response.isSuccessful && response.body() != null) {
                    emit(APIResult.Success(data = response.body()!!))
                } else {
                    val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                    emit(APIResult.Error(message = errorObj.getString("message")))
                }
            } catch (exception: JSONException) {
                emit(APIResult.Error(message = "Something Went Wrong!"))
                Log.e(TAG, "getAllItems: ${exception.message}")
            } catch (exception: Exception) {
                emit(APIResult.Error(message = "Something Went Wrong!"))
                Log.e(TAG, "getAllItems: ${exception.message}")
            }
        }
    }
}
