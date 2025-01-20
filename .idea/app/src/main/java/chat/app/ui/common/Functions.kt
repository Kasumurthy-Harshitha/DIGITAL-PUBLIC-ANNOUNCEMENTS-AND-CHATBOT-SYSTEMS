package chat.app.ui.common

import androidx.activity.OnBackPressedCallback
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.backPoint() {
    requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().navigateUp()
        }

    })
}

fun Fragment.toPauseTheApp() {
    requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().moveTaskToBack(true)
        }

    })
}
fun spanned(string: String)=HtmlCompat.fromHtml(string,HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS)