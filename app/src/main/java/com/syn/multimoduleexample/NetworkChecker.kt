package com.syn.multimoduleexample

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress

class NetworkChecker {

    companion object {
        @Volatile
        private var instance: NetworkChecker? = null

        fun getInstance(): NetworkChecker {
            return instance ?: synchronized(this) {
                instance ?: NetworkChecker().also { instance = it }
            }
        }
    }

    suspend fun pingHost(host: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val inetAddress = InetAddress.getByName(host)
                Log.d("NetworkChecker", "pingUsingCommand: ${inetAddress.hostAddress}")
                inetAddress.isReachable(100) // Timeout set to 100ms

            } catch (e: Exception) {
                false
            }
        }
    }


    suspend fun pingUsingCommand(host: String? = null): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val process = ProcessBuilder("ping", "-c", "1", "-W", "1", host ?: "google.com")
                    .redirectErrorStream(true)
                    .start()


                val result = process.waitFor()
                Log.d(
                    "NetworkChecker",
                    "pingUsingCommand: ${
                        process.inputStream.bufferedReader().readText()
                    } Result: $result"
                )
                result == 0 // Returns true if the ping was successful
            } catch (e: Exception) {
                false
            }
        }
    }


    suspend fun resolveHostname(host: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val process = ProcessBuilder("nslookup", host)
                    .redirectErrorStream(true)
                    .start()

                val reader = process.inputStream.bufferedReader()
                val output = reader.readText()
                process.waitFor()
                Log.d(
                    "NetworkChecker",
                    "resolveHostname: ${
                        process.inputStream.bufferedReader().readText()
                    } Result: $output"
                )

                output.contains("Address") // If the output contains an IP, DNS works
            } catch (e: Exception) {
                false
            }
        }
    }


}