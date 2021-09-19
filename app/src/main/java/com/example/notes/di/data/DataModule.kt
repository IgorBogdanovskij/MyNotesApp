package com.example.notes.di.data

import android.content.Context
import androidx.room.Room
import com.example.notes.data.locale.dao.NoteDaoKotlin
import com.example.notes.data.locale.database.DataBaseKotlin
import com.example.notes.data.repository.NotesRepositoryImp
import com.example.notes.domain.repository.INotesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {


    // TODO: 15/09/2021 не хочет работать с rxJava обновление и добавление
    @Singleton
    @Provides
    fun provideDataBase(context: Context): DataBaseKotlin {
        return Room.databaseBuilder(
            context,
            DataBaseKotlin::class.java,
            DataBaseKotlin.DATA_BASE_NAME
        )
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteDaoKotlin(dataBase: DataBaseKotlin): NoteDaoKotlin {
        return dataBase.noteDaoKotlin()
    }

    @Singleton
    @Provides
    fun provideNoteRepository(noteDaoKotlin: NoteDaoKotlin): INotesRepository {
        return NotesRepositoryImp(noteDao = noteDaoKotlin)
    }
}