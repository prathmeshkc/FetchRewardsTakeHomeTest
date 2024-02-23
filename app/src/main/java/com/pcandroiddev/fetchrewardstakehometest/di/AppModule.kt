package com.pcandroiddev.fetchrewardstakehometest.di

import com.pcandroiddev.fetchrewardstakehometest.api.ItemsService
import com.pcandroiddev.fetchrewardstakehometest.repository.ItemRepository
import com.pcandroiddev.fetchrewardstakehometest.repository.ItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesItemRepository(itemsService: ItemsService): ItemRepository {
        return ItemRepositoryImpl(itemsService)
    }

}