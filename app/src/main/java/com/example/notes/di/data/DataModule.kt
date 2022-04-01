package com.example.notes.di.data

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.NoteDao
import com.example.data.local.database.DataBase
import com.example.data.repository.NotesRepositoryImp
import com.example.domainn.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule.InnerDataModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            DataBase.DATA_BASE_NAME
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(dataBase: DataBase): NoteDao {
        return dataBase.noteDao()
    }

    @Module
    interface InnerDataModule{

        @Binds
        @Singleton
        fun bindNoteRepository(notesRepositoryImp: NotesRepositoryImp): NotesRepository
    }
}
