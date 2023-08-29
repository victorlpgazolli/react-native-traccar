/*
 * Copyright 2015 - 2021 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.victorlpgazolli.rntraccar.android


import android.content.Context





import android.os.Handler
import android.os.Looper
import androidx.preference.PreferenceManager
import android.util.Log
import dev.victorlpgazolli.rntraccar.android.ProtocolFormatter.formatRequest
import dev.victorlpgazolli.rntraccar.android.RequestManager.sendRequestAsync


class TrackingController(private val context: Context) : PositionProvider.PositionListener,
  NetworkManager.NetworkHandler {

    private val handler = Handler(Looper.getMainLooper())
    private val preferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)
    private val positionProvider = PositionProviderFactory.create(context, this)
    private val databaseHelper = DatabaseHelper(context)
    private val networkManager = NetworkManager(context, this)

    private val url: String = preferences.getString(Constants.KEY_URL, "http://localhost:5055")!!
    private val buffer: Boolean = preferences.getBoolean(Constants.KEY_BUFFER, true)

    private var isOnline = networkManager.isOnline
    private var isWaiting = false

    fun start() {
        if (isOnline) {
            read()
        }
        try {
            positionProvider.startUpdates()
        } catch (e: SecurityException) {
            Log.w(TAG, e)
        }
        networkManager.start()
    }

    fun stop() {
        networkManager.stop()
        try {
            positionProvider.stopUpdates()
        } catch (e: SecurityException) {
            Log.w(TAG, e)
        }
        handler.removeCallbacksAndMessages(null)
    }

    override fun onPositionUpdate(position: Position) {
        Log.i(TAG, "position update!")
        if (buffer) {
            write(position)
        } else {
            send(position)
        }
    }

    override fun onPositionError(error: Throwable) {
        Log.i(TAG, "on position error!" + error.message)
    }
    override fun onNetworkUpdate(isOnline: Boolean) {
        val message = if (isOnline) "Network online" else "Network offline"
        Log.i(TAG, "network update!")
        if (!this.isOnline && isOnline) {
            read()
        }
        this.isOnline = isOnline
    }

    //
    // State transition examples:
    //
    // write -> read -> send -> delete -> read
    //
    // read -> send -> retry -> read -> send
    //

    private fun log(action: String, position: Position?) {
        if (position != null) {
          Log.d(TAG, action +": "+ position.toString())
        }
    }

    private fun write(position: Position) {
        log("write", position)
        databaseHelper.insertPositionAsync(position, object :
          DatabaseHelper.DatabaseHandler<Unit?> {
            override fun onComplete(success: Boolean, result: Unit?) {
                if (success) {
                    if (isOnline && isWaiting) {
                        read()
                        isWaiting = false
                    }
                }
            }
        })
    }

    private fun read() {
        log("read", null)
        databaseHelper.selectPositionAsync(object : DatabaseHelper.DatabaseHandler<Position?> {
            override fun onComplete(success: Boolean, result: Position?) {
                if (success) {
                    if (result != null) {
                        Log.i(TAG, "read result deviceId: " + result.deviceId)
                         if (result.deviceId == preferences.getString(Constants.KEY_DEVICE, null)) {
                        send(result)
                         } else {
                             delete(result)
                         }
                    } else {
                        isWaiting = true
                    }
                } else {
                    retry()
                }
            }
        })
    }

    private fun delete(position: Position) {
        log("delete", position)
        databaseHelper.deletePositionAsync(position.id, object :
          DatabaseHelper.DatabaseHandler<Unit?> {
            override fun onComplete(success: Boolean, result: Unit?) {
                if (success) {
                    read()
                } else {
                    retry()
                }
            }
        })
    }

    private fun send(position: Position) {
        log(TAG + "Send",position)
        Log.i(TAG, "Send:"+url)
        val request = formatRequest(url, position)
        sendRequestAsync(request, object : RequestManager.RequestHandler {
            override fun onComplete(success: Boolean) {
                if (success) {
                    if (buffer) {
                        delete(position)
                    }
                } else {
                    if (buffer) {
                        retry()
                    }
                }
            }
        })
    }

    private fun retry() {
        log("retry", null)
        handler.postDelayed({
            if (isOnline) {
                read()
            }
        }, RETRY_DELAY.toLong())
    }

    companion object {
private val TAG = "Traccar" + TrackingController::class.java.simpleName

        private const val RETRY_DELAY = 30 * 1000
    }

}
