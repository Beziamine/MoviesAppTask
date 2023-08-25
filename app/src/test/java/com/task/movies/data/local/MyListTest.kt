package com.task.movies.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.task.movies.data.local.dao.MyListDao
import com.task.movies.data.resource.fakeList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MyListTest {
    private lateinit var dao: MyListDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()

        dao = database.myListDao()
    }

    @Test
    fun `add movie to my list`()= runTest {
        dao.addToMyList(fakeList)
        val list = dao.getMyList().first().first()
        Truth.assertThat(list).isEqualTo(fakeList)
    }

    @Test
    fun `delete one movie from my list`() = runTest{
        dao.addToMyList(fakeList)
        dao.deleteOneFromMyList(1)
        val list = dao.getMyList().first()
        Truth.assertThat(list).isEmpty()
    }

    @Test
    fun `delete all movies from my list`() = runTest{
        dao.addToMyList(fakeList)
        dao.deleteAllContentFromMyList()
        val list = dao.getMyList().first()
        Truth.assertThat(list).isEmpty()
    }

    @Throws(IOException::class)
    fun tearDown(){
        database.close()
    }
}