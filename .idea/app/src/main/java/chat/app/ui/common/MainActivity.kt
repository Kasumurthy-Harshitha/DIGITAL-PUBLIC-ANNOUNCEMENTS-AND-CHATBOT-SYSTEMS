package chat.app.ui.common

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import chat.app.R
import chat.app.databinding.ActivityMainBinding
import chat.app.ui.viewModels.MainViewModels
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel by viewModels<MainViewModels>()

    private val dialog by lazy {
        Dialog(this).apply {
            setContentView(R.layout.card_point)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        mainViewModel.toast.onEach {
            it?.let {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            }
            lifecycleScope.async {
                    delay(2000)
                    mainViewModel.toastPoint(null)
            }.start()

        }.launchIn(lifecycleScope)


        mainViewModel.dialog.onEach {
            if (it) dialog.show() else dialog.dismiss()
        }.launchIn(lifecycleScope)


    }
}