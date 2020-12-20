package com.arivero007.myheroapp.resources

import com.arivero007.myheroapp.resources.Utils.Companion.addApiKeys
import junit.framework.TestCase
import org.junit.Test

class UtilsTest : TestCase(){

    @Test
    fun testAddApiKeys_checkIsCorrect(){

        val timeSecondsString = (1608488903L).toString()
        val hash = timeSecondsString.addApiKeys()

        assertEquals(timeSecondsString + Constants.apiKeyPri + Constants.apiKeyPu, hash)

    }

}