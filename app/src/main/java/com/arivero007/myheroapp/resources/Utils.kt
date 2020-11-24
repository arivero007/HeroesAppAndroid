package com.arivero007.myheroapp.resources

import java.security.MessageDigest

class Utils {

    companion object{
        fun getMD5(md5: String): String {
            val bytes = MessageDigest.getInstance("MD5").digest(md5.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }
    }
}