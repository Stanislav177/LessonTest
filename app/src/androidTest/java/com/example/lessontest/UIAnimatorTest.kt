package com.example.lessontest

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class UIAnimatorTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val packageName = context.packageName

    @Before
    fun setup() {
        uiDevice.pressHome()

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)

        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        uiDevice.wait(Until.findObject(By.pkg(packageName)), 7000L)
    }

    @Test
    fun activity_search_edit_text_test() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        assertNotNull(editText)
    }

    @Test
    fun activity_search_edit_text_click_search_test() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))

        editText.text = "uianimator"

        Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
            .perform(ViewActions.pressImeActionButton())

        val chaText =
            uiDevice.wait(Until.findObject(By.res(packageName, "totalCountTextView")), 5000L)

        assertEquals(chaText.text.toString(), "Number of results: 5")
    }

    @Test
    fun activity_main_to_click_details() {

        val btnDetails = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        btnDetails.click()
        val btnIncrem =
            uiDevice.wait(Until.findObject(By.res(packageName, "incrementButton")), 5000L)
        btnIncrem.click()

        val textView =
            uiDevice.wait(Until.findObject(By.res(packageName, "totalCountTextViewDet")), 5000L)

        assertEquals(textView.text.toString(), "Number of results: 1")
    }

    @Test
    fun activity_search_edit_text_click_detail() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "uianimator"
        Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
            .perform(ViewActions.pressImeActionButton())

        val chaText =
            uiDevice.wait(Until.findObject(By.res(packageName, "totalCountTextView")), 5000L)

        val btnDetails =
            uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))

        if (chaText.text.toString() == "Number of results: 5") {
            btnDetails.click()
        }

        val textView =
            uiDevice.wait(Until.findObject(By.res(packageName, "totalCountTextViewDet")), 5000L)

        assertEquals(textView.text.toString(), "Number of results: 5")
    }

    @Test
    fun activity_search_edit_text_clickBtn_search_detail() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "uianimator"
        val btnSearch = uiDevice.findObject(By.res(packageName, "btnSearch"))
        btnSearch.click()

        val chaText =
            uiDevice.wait(Until.findObject(By.res(packageName, "totalCountTextView")), 5000L)

        val btnDetails =
            uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))

        if (chaText.text.toString() == "Number of results: 5") {
            btnDetails.click()
        }

        val textView =
            uiDevice.wait(Until.findObject(By.res(packageName, "totalCountTextViewDet")), 5000L)

        assertEquals(textView.text.toString(), "Number of results: 5")
    }
}