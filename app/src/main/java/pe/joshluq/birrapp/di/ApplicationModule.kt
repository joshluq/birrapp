package pe.joshluq.birrapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pe.joshluq.birrapp.util.resource.ResourceProvider
import pe.joshluq.birrapp.util.resource.ResourceProviderImpl
import java.lang.ref.WeakReference
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideWeekContext(@ApplicationContext appContext: Context): WeakReference<Context> =
        WeakReference(appContext)

    @Singleton
    @Provides
    fun provideResourceProvider(resourceProvider: ResourceProviderImpl): ResourceProvider =
        resourceProvider
}