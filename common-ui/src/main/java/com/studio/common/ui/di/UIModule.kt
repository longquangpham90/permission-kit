package com.studio.common.ui.di

import android.content.Context
import com.studio.common.ui.utils.CompassSensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UIModule {
    @Provides
    @Singleton
    fun provideCompassSenor(
        @ApplicationContext context: Context
    ): CompassSensor {
        return CompassSensor(context)
    }
}
