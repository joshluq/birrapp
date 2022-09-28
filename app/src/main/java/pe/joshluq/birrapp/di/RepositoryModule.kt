package pe.joshluq.birrapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.joshluq.birrapp.data.repository.BeerDataRepository
import pe.joshluq.birrapp.domain.repository.BeerRepository
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(repository: BeerDataRepository): BeerRepository

}