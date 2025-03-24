package com.capgemini.architectcoders.ui.detail

import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.capgemini.architectcoders.sampleMovie
import com.capgemini.architectcoders.ui.common.Result
import org.junit.Rule
import org.junit.Test
import com.capgemini.architectcoders.ui.common.R
import org.junit.Assert.assertTrue


class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {
            DetailScreen(
                state = Result.Error(RuntimeException("An error occurred")),
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithText("An error occurred").assertExists()
    }

    @Test
    fun whenSuccessState_movieIsShown(): Unit = with(composeTestRule) {
        setContent {
            DetailScreen(
                state = Result.Success(sampleMovie(2)),
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithText("Title 2").assertExists()
    }

    @Test
    fun whenFavoriteClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {
            DetailScreen(
                state = Result.Success(sampleMovie(2)),
                onBack = {},
                onFavoriteClicked = { clicked = true }
            )
        }
        onNodeWithContentDescription(getStringResource(R.string.favorite)).performClick()
        assertTrue(clicked)
    }

    @Test
    fun whenBackClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {
            DetailScreen(
                state = Result.Success(sampleMovie(2)),
                onBack = { clicked = true },
                onFavoriteClicked = {}
            )
        }

        onNodeWithContentDescription(getStringResource(R.string.back)).performClick()
        assertTrue(clicked)
    }

}

private fun getStringResource(@StringRes id: Int): String {
    val ctx = InstrumentationRegistry.getInstrumentation().targetContext
    return ctx.getString(id)
}