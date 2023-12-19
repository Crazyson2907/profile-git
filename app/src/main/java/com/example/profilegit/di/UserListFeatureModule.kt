package com.example.profilegit.di

import android.content.Context
import androidx.room.Room
import com.example.profilegit.data.local.GitUsersDao
import com.example.profilegit.data.local.GitUsersDatabase
import com.example.profilegit.data.local.UserCacheRepositoryImpl
import com.example.profilegit.data.network.RepositoryImpl
import com.example.profilegit.domain.cache.core.UserCacheRepository
import com.example.profilegit.domain.cache.usecase.GetUserDetailsFromCacheUseCase
import com.example.profilegit.domain.cache.usecase.GetUsersFromCacheUseCase
import com.example.profilegit.domain.cache.usecase.SaveUserDetailsToCacheUseCase
import com.example.profilegit.domain.cache.usecase.SaveUsersToCacheUseCase
import com.example.profilegit.domain.core.use_case.GetDetailsUseCase
import com.example.profilegit.domain.core.use_case.GetUsersUseCase
import com.example.profilegit.domain.network.ApiService
import com.example.profilegit.domain.network.repository.Repository
import com.example.profilegit.domain.network.use_case.FetchUserDetailsFromApiUseCase
import com.example.profilegit.domain.network.use_case.FetchUserListFromApiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ActivityRetainedComponent::class)
class UserListFeatureModule {

    @Provides
    fun provideGitUserDao(gitUsersDatabase: GitUsersDatabase): GitUsersDao {
        return gitUsersDatabase.gitUsersDao()
    }

    @Provides
    fun provideRepository(apiService: ApiService): Repository {
        return RepositoryImpl(apiService)
    }

    @Provides
    fun provideGitUsersDatabase(@ApplicationContext application: Context): GitUsersDatabase {
        return Room.databaseBuilder(
            application,
            GitUsersDatabase::class.java,
            "GitUsersDataBase"
        ).build()
    }

    @Provides
    fun provideUserCacheRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        dao: GitUsersDao
    ): UserCacheRepository =
        UserCacheRepositoryImpl(
            dao = dao,
            ioDispatcher = dispatcher
        )

    @Provides
    fun provideFetchUserListFromApiUseCase(
        repository: Repository,
        saveUsersToCacheUseCase: SaveUsersToCacheUseCase
    ): FetchUserListFromApiUseCase =
        FetchUserListFromApiUseCase.Base(
            repository = repository,
            saveUsersToCacheUseCase = saveUsersToCacheUseCase
        )

    @Provides
    fun provideFetchUserDetailsFromApiUseCase(
        repository: Repository,
        detailsToCacheUseCase: SaveUserDetailsToCacheUseCase
    ): FetchUserDetailsFromApiUseCase =
        FetchUserDetailsFromApiUseCase.Base(
            repository = repository,
            detailsToCacheUseCase = detailsToCacheUseCase
        )

    @Provides
    fun provideGetUsersUseCase(
        usersFromApiUseCase: FetchUserListFromApiUseCase,
        getUsersFromCacheUseCase: GetUsersFromCacheUseCase
    ): GetUsersUseCase =
        GetUsersUseCase.Base(
            usersFromApiUseCase = usersFromApiUseCase,
            getUsersFromCacheUseCase = getUsersFromCacheUseCase
        )

    @Provides
    fun provideGetDetailsUseCase(
        fetchUserDetailsFromApiUseCase: FetchUserDetailsFromApiUseCase,
        getUserDetailsFromCacheUseCase: GetUserDetailsFromCacheUseCase
    ): GetDetailsUseCase =
        GetDetailsUseCase.Base(
            fetchUserDetailsFromApiUseCase = fetchUserDetailsFromApiUseCase,
            getUserDetailsFromCacheUseCase = getUserDetailsFromCacheUseCase
        )

    @Provides
    fun provideSaveUsersToCacheUseCase(
        repository: UserCacheRepository
    ): SaveUsersToCacheUseCase =
        SaveUsersToCacheUseCase.Base(
            repository = repository
        )

    @Provides
    fun provideSaveUserDetailsToCacheUseCase(
        repository: UserCacheRepository
    ): SaveUserDetailsToCacheUseCase =
        SaveUserDetailsToCacheUseCase.Base(
            repository = repository
        )

    @Provides
    fun provideGetUsersFromCacheUseCase(
        repository: UserCacheRepository
    ): GetUsersFromCacheUseCase =
        GetUsersFromCacheUseCase.Base(
            repository = repository
        )

    @Provides
    fun provideGetUserDetailsFromCacheUseCase(
        repository: UserCacheRepository
    ): GetUserDetailsFromCacheUseCase =
        GetUserDetailsFromCacheUseCase.Base(
            repository = repository
        )
}