package com.esaldivia.urbandictionary.viewmodels

import android.content.Context
import android.os.Build
import android.os.Looper
import androidx.lifecycle.liveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.esaldivia.urbandictionary.getOrAwaitValue
import com.esaldivia.urbandictionary.models.SearchResponse
import com.esaldivia.urbandictionary.models.Word
import com.esaldivia.urbandictionary.network.DictionaryApi
import com.esaldivia.urbandictionary.network.Resource
import com.esaldivia.urbandictionary.network.Status
import com.esaldivia.urbandictionary.repositories.WordRepository

import org.junit.Before
import org.junit.Test

import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.runner.RunWith
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DictionaryViewModelTest {

    // Region constants
    val word = "word"
    val errorMessage = "errorMessage"
    // EndRegion constants

    // Region helper fields
    private val apiFake: ApiFake = ApiFake()
    private val repositoryFake: RepositoryFake = RepositoryFake(apiFake)
    // EndRegion helper fields
    lateinit var SUT: DictionaryViewModel

    @Before
    fun setup() {
        SUT = DictionaryViewModel(repositoryFake, ApplicationProvider.getApplicationContext())

    }

    @Test
    fun searchTerm_SUCCESS_fetchedSearchResponse() {
        val result = SUT.searchTerm(word = word).getOrAwaitValue()

        Shadows.shadowOf(Looper.getMainLooper()).idle()

        assertThat(result.status, `is`(Status.SUCCESS))
        assertThat(result.data!!.definitions.size, Matchers.`is`(1))
        assertThat(result.message, Matchers.nullValue())
    }

    @Test
    fun searchTerm_ERROR_retrieveError() {
        ERROR()
        val result = SUT.searchTerm(word = word).getOrAwaitValue()

        Shadows.shadowOf(Looper.getMainLooper()).idle()

        assertThat(result.status, `is`(Status.ERROR))
        assertThat(result.message, `is`(errorMessage))
        assertThat(result.data, Matchers.nullValue())
    }

    @Test
    fun searchTerm_LOADING() {
        LOADING()
        val result = SUT.searchTerm(word = word).getOrAwaitValue()

        Shadows.shadowOf(Looper.getMainLooper()).idle()

        assertThat(result.status, `is`(Status.LOADING))
        assertThat(result.data, Matchers.nullValue())
    }

    @Test
    fun orderByThumbsUp() {
        val definitions = arrayListOf(
            Word(1, word, "definition1", "author1", 10, 10),
            Word(1, word, "definition1", "author1", 100, 10),
            Word(1, word, "definition1", "author1", 1000, 10)
        )
        SUT.orderByThumbsUp(definitions)

        assertThat(SUT.definitionsOrder.value!![0].thumbsUp, `is`(1000))
        assertThat(SUT.definitionsOrder.value!![2].thumbsUp, `is`(10))
        assertThat(SUT.isOrderedByThumbsUp.value, `is`(true))
    }

    @Test
    fun orderByThumbsDown() {
        val definitions = arrayListOf(
            Word(1, word, "definition1", "author1", 10, 10),
            Word(1, word, "definition1", "author1", 100, 100),
            Word(1, word, "definition1", "author1", 1000, 1000)
        )
        SUT.orderByThumbsDown(definitions)

        assertThat(SUT.definitionsOrder.value!![0].thumbsDown, `is`(1000))
        assertThat(SUT.definitionsOrder.value!![2].thumbsDown, `is`(10))
        assertThat(SUT.isOrderedByThumbsUp.value, `is`(false))
    }

    // Region helper methods
    private fun LOADING() {
        repositoryFake.isLoading = true
    }

    private fun ERROR() {
        repositoryFake.isError = true
    }
    // Endregion helper methods

    // Region helper classes
    class ApiFake : DictionaryApi {
        override suspend fun searchWord(word: String): SearchResponse {
            val words: ArrayList<Word> = arrayListOf(
                Word(1, word, "definition1", "author1", 10,10))
            return SearchResponse(words)
        }

    }

    class RepositoryFake(private val apiFake: DictionaryApi): WordRepository(apiFake){
        var isError = false
        var isLoading = false
        override fun handleSearch(word: String, context: Context) = liveData {
            when {
                isLoading -> {
                    emit(Resource.loading(null))
                }
                isError -> {
                    emit(Resource.error(null, "errorMessage"))
                }
                else -> {
                    emit(Resource.succces(apiFake.searchWord(word)))
                }
            }
        }

    }
    // Endregion helper classes
}