import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.simplesurance.dogbreed.R

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController()
                .navigate(R.id.action_splashFragment_to_dogBreedsFragment)
        }, 2000)
    }
}