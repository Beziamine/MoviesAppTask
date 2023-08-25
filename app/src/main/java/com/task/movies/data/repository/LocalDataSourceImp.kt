package com.task.movies.data.repository

import com.task.movies.data.local.AppDatabase
import com.task.movies.domain.models.MyList
import com.task.movies.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImp(
    appDatabase: AppDatabase
): LocalDataSource {

    private val dao = appDatabase.myListDao()

    override fun getMyList(): Flow<List<MyList>> {
        return dao.getMyList()
    }

    override fun getSelectedFromMyList(listId: Int): MyList {
        return dao.getSelectedFromMyList(listId = listId)
    }

    override suspend fun addToMyList(myList: MyList) {
        return dao.addToMyList(myList = myList)
    }

    override suspend fun ifExists(listId: Int): Int {
        return dao.ifExists(listId)
    }

    override suspend fun deleteOneFromMyList(myList: Int) {
        return dao.deleteOneFromMyList(myList)
    }

    override suspend fun deleteAllContentFromMyList() {
        return dao.deleteAllContentFromMyList()
    }
}