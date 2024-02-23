package com.pcandroiddev.fetchrewardstakehometest.repository

import com.pcandroiddev.fetchrewardstakehometest.data.remote.Item
import com.pcandroiddev.fetchrewardstakehometest.util.APIResult
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun getAllItems(): Flow<APIResult<List<Item>>>
}