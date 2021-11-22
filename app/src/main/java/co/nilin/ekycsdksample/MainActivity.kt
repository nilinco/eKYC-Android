package co.nilin.ekycsdksample

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings.Secure
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.nilin.sdk.ekyc.EkycSdk

class MainActivity : AppCompatActivity() {

    private val ekycSdk by lazy {
        EkycSdk.getClient(SERVER, API_KEY, getDeviceId())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startKyc(view: View) {
        val phoneNumber = "" // ex: 09129876543
        startActivityForResult(ekycSdk.intent(this, phoneNumber), 100)
    }

    @SuppressLint("HardwareIds")
    private fun getDeviceId(): String {
        return Secure.getString(contentResolver, Secure.ANDROID_ID)
    }

    companion object {
        private const val API_KEY = ""
        private const val SERVER = "https://ekycuat.izbank.ir"
    }

}