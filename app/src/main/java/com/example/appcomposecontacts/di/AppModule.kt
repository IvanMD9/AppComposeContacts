package com.example.appcomposecontacts.di

import android.app.Application
import androidx.room.Room
import com.example.appcomposecontacts.data.data_source.ContactDatabase
import com.example.appcomposecontacts.data.repository.ContactRepositoryImpl
import com.example.appcomposecontacts.domain.repository.RepositoryContact
import com.example.appcomposecontacts.domain.use_case.*
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
    fun provideContactsDatabase(app: Application): ContactDatabase {
        return Room.databaseBuilder(
            app,
            ContactDatabase::class.java,
            ContactDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactsRepository(db: ContactDatabase): RepositoryContact {
        return ContactRepositoryImpl(db.getDao())
    }

    @Provides
    @Singleton
    fun provideContactsUseCase(repositoryContact: RepositoryContact): UseCaseContact {
        return UseCaseContact(
            getContactsUseCase = GetContactsUseCase(repositoryContact),
            deleteContactUseCase = DeleteContactUseCase(repositoryContact),
            getContactItemUseCase = GetContactItemUseCase(repositoryContact),
            addContactUseCase = AddContactUseCase(repositoryContact),
            searchContactsUseCase = SearchContactsUseCase(repositoryContact),
            getListFavouritesContactsUseCase = GetListFavouritesContactsUseCase(repositoryContact),
            addFavouritesContactUseCase = AddFavouritesContactUseCase(repositoryContact),
            deleteFavouritesContactUseCase = DeleteFavouritesContactUseCase(repositoryContact)
        )
    }
}