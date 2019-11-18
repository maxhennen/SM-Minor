package com.example.tune_kotlin

import android.view.WindowManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.tune_kotlin.activities.RegisterActivity
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationActivityTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<RegisterActivity> = ActivityTestRule(RegisterActivity::class.java)

    @Test
    fun user_can_enter_email(){
        onView(withId(R.id.etEmailRegister)).perform(typeText("test@test.nl"))
    }

    @Test
    fun user_can_enter_password(){
        onView(withId(R.id.etPasswordRegister)).perform(typeText("Test12345"))
    }

    @Test
    fun user_can_enter_password_confirm(){
        onView(withId(R.id.etConfirm)).perform(typeText("Test12345"))
    }

    @Test
    fun check_fields_when_user_clicks_register_button(){
        onView(withId(R.id.etEmailRegister)).perform(typeText("test@test.nl"), closeSoftKeyboard())
        onView(withId(R.id.etConfirm)).perform(typeText("Test12345"), closeSoftKeyboard())
        onView(withId(R.id.etPasswordRegister)).perform(typeText("Test12345"), closeSoftKeyboard())
        onView(withId(R.id.btnRegister)).perform(click())
    }

    @Test
    fun check_fields_when_user_clicks_register_button_wrong_email(){
        onView(withId(R.id.etEmailRegister)).perform(typeText("test"), closeSoftKeyboard())
        onView(withId(R.id.etConfirm)).perform(typeText("Test12345"), closeSoftKeyboard())
        onView(withId(R.id.etPasswordRegister)).perform(typeText("Test12345"), closeSoftKeyboard())
        onView(withId(R.id.btnRegister)).perform(click())
        onView(withText("Invalid email!")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun check_fields_when_user_clicks_register_button_empty_email(){
        onView(withId(R.id.etEmailRegister)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.etConfirm)).perform(typeText("Test12345"), closeSoftKeyboard())
        onView(withId(R.id.etPasswordRegister)).perform(typeText("Test12345"), closeSoftKeyboard())
        onView(withId(R.id.btnRegister)).perform(click())
        onView(withText("Not all fields are filled in!")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun check_fields_when_user_clicks_register_button_empty_password(){
        onView(withId(R.id.etEmailRegister)).perform(typeText("test@test.nl"), closeSoftKeyboard())
        onView(withId(R.id.etConfirm)).perform(typeText("Test12345"), closeSoftKeyboard())
        onView(withId(R.id.etPasswordRegister)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.btnRegister)).perform(click())
        onView(withText("Not all fields are filled in!")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun check_fields_when_user_clicks_register_button_no_capital_password(){
        onView(withId(R.id.etEmailRegister)).perform(typeText("test@test.nl"), closeSoftKeyboard())
        onView(withId(R.id.etConfirm)).perform(typeText("Test12345"), closeSoftKeyboard())
        onView(withId(R.id.etPasswordRegister)).perform(typeText("test12345"), closeSoftKeyboard())
        onView(withId(R.id.btnRegister)).perform(click())
        onView(withText("Password doesn't match requirements!")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun check_fields_when_user_clicks_register_button_no_lower_password(){
        onView(withId(R.id.etEmailRegister)).perform(typeText("test@test.nl"), closeSoftKeyboard())
        onView(withId(R.id.etConfirm)).perform(typeText("TEST12345"), closeSoftKeyboard())
        onView(withId(R.id.etPasswordRegister)).perform(typeText("TEST12345"), closeSoftKeyboard())
        onView(withId(R.id.btnRegister)).perform(click())
        onView(withText("Password doesn't match requirements!")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun check_fields_when_user_clicks_register_button_no_number_password(){
        onView(withId(R.id.etEmailRegister)).perform(typeText("test@test.nl"), closeSoftKeyboard())
        onView(withId(R.id.etConfirm)).perform(typeText("Test12345"), closeSoftKeyboard())
        onView(withId(R.id.etPasswordRegister)).perform(typeText("test12345"), closeSoftKeyboard())
        onView(withId(R.id.btnRegister)).perform(click())
        onView(withText("Password doesn't match requirements!")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun check_fields_when_user_clicks_register_button_not_long_enough_password(){
        onView(withId(R.id.etEmailRegister)).perform(typeText("test@test.nl"), closeSoftKeyboard())
        onView(withId(R.id.etConfirm)).perform(typeText("Test12345"), closeSoftKeyboard())
        onView(withId(R.id.etPasswordRegister)).perform(typeText("Test1"), closeSoftKeyboard())
        onView(withId(R.id.btnRegister)).perform(click())
        onView(withText("Password doesn't match requirements!")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun check_fields_when_user_clicks_register_button_passwordfields_dont_match(){
        onView(withId(R.id.etEmailRegister)).perform(typeText("test@test.nl"), closeSoftKeyboard())
        onView(withId(R.id.etConfirm)).perform(typeText("Test12345!"), closeSoftKeyboard())
        onView(withId(R.id.etPasswordRegister)).perform(typeText("Test123456!"), closeSoftKeyboard())
        onView(withId(R.id.btnRegister)).perform(click())
        onView(withText("Passwordfields don't match!")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }


}

class ToastMatcher : TypeSafeMatcher<Root>() {
    override fun matchesSafely(root: Root?): Boolean {
        val type = root?.windowLayoutParams?.get()?.type
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            val windowToken = root.decorView.windowToken
            val appToken = root.decorView.applicationWindowToken
            if (windowToken === appToken) {
                return true
                //means this window isn't contained by any other windows.
            }
        }
        return false
    }

    override fun describeTo(description: Description?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}