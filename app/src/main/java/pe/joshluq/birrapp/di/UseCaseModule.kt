package pe.joshluq.birrapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.joshluq.birrapp.domain.model.Beer
import pe.joshluq.birrapp.domain.usecase.FindBeerByNameUseCase
import pe.joshluq.birrapp.domain.usecase.UseCase
import javax.inject.Named

import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    @Named("findBeerByNameUseCase")
    abstract fun bindFindBeerByNameUseCase(useCase: FindBeerByNameUseCase): UseCase<String, List<Beer>>
}