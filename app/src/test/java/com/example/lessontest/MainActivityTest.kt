package com.example.lessontest

import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lessontest.view.search.MainActivity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O])
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun activity_Assert_NotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activity_EditText() {
        scenario.onActivity {
            val et = it.findViewById<EditText>(R.id.searchEditText)
            assertNotNull(et)
        }
    }

    @Test
    fun activity_EditText_Text() {
        scenario.onActivity {
            val et = it.findViewById<EditText>(R.id.searchEditText)
            et.setText("ONE", TextView.BufferType.EDITABLE)
            et.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
            assertEquals("ONE", et.text.toString())
        }
    }

    @Test
    fun activity_ProgressBar() {
        scenario.onActivity {
            val pb = it.findViewById<ProgressBar>(R.id.progressBar)
            assertEquals(View.GONE, pb.visibility)
        }
    }

    @Test
    fun name_app(){
        scenario.onActivity {
            val nameTitle = it.title
            assertEquals(nameTitle, "LessonTest")
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}