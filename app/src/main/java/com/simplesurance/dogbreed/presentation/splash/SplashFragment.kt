import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.simplesurance.dogbreed.R
import kotlinx.coroutines.launch


class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            view?.post {
                lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                        if (isAdded) {
                            findNavController().navigate(R.id.action_splashFragment_to_dogBreedsFragment)
                        }
                    }
                }
            }
        }, 2000)
    }
}
