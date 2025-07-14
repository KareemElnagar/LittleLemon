package com.kareem.littlelemon.di

import androidx.room.Room
import com.kareem.littlelemon.data.AppDatabase
import com.kareem.littlelemon.data.repository.CartRepository
import com.kareem.littlelemon.data.repository.MenuRepository
import com.kareem.littlelemon.util.AppConstants
import com.kareem.littlelemon.util.LittleLemonApplication

object DependencyInjection {
    
    // Database
    private val database: AppDatabase by lazy {
        Room.databaseBuilder(
            LittleLemonApplication.getApplicationContext(),
            AppDatabase::class.java,
            AppConstants.Database.DATABASE_NAME
        ).build()
    }
    
    // Repositories
    val menuRepository: MenuRepository by lazy {
        MenuRepository(database)
    }
    
    val cartRepository: CartRepository by lazy {
        CartRepository()
    }
} 