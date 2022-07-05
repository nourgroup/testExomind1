package ft.exomind.testexomind.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import ft.exomind.testexomind.R
import ft.exomind.testexomind.databinding.FragmentAccueilBinding

class AccueilFragment : Fragment() {

    lateinit var _binding : FragmentAccueilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccueilBinding.inflate(
            inflater,
            container,
            false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.bGotoData.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_accueil_to_data)
        }
    }
}