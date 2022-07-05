package ft.exomind.testexomind.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import ft.exomind.testexomind.R
import ft.exomind.testexomind.data.model.City
import ft.exomind.testexomind.databinding.ActivityMainBinding
import ft.exomind.testexomind.databinding.FragmentDataBinding
import ft.exomind.testexomind.utils.Utils
import ft.exomind.testexomind.viewmodels.ViewModelProgressBar
import ft.exomind.testexomind.databinding.ItemBinding

class DataFragment : Fragment() {

    lateinit var _binding : FragmentDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataBinding.inflate(
            inflater,
            container,
            false
        )
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewmodel : ViewModelProgressBar by viewModels()
        // launch progressbar
        viewmodel.loading()
        // display message
        viewmodel.displayMessage()

        var percentage = 0
        _binding.progressBar1.progress = percentage
        viewmodel.progress.observe(this){
            // display percentage
            percentage = ((it *100 ) / Utils.max.toInt())
            _binding.progressBar1.progress = percentage
            _binding.percentage.text = "$percentage %"
            // call api every 10s
            if(percentage!=0){
                viewmodel.getdata(Utils.listWeather[(it/Utils.delayTime).toInt() -1 ])
            }
        }

        // add new data
        viewmodel.displayWeather.observe(this){
            if(it.code==null){
                Toast.makeText(activity, "erreur : les données ne sont pas récuprées depuis internet", Toast.LENGTH_SHORT).show()
            }else{
                Utils.listWeather.map { e -> e.city == it.data?.city}
            }
        }

        // display waiting message
        viewmodel.displayMessage.observe(this){
            _binding.displayMessage.text = resources.getString(Utils.list[it % Utils.list.size])
        }

        // construct table
        viewmodel.constructTable.observe(this){
            // creation table
            Utils.listWeather.forEach { city ->
                _binding.tl.addView(createTableView(city))
            }
            toggleVisibleAndShowButtonRecommencer()
        }

        _binding.recommencer.setOnClickListener {
            toggleVisibleAndShowButtonRecommencer()
            // reset for ProgressBar
            viewmodel.reset()
            // launch progressbar
            viewmodel.loading()
            // remove rows
            _binding.tl.removeAllViews()
        }

    }

    /**
     * @param mcity object
     */
    private fun createTableView(mcity : City) : View{
        var viewChild = layoutInflater.inflate(R.layout.item,null,false)
        var tv_weather = viewChild.findViewById<TextView>(R.id.tv_weather)
        var tv_wind = viewChild.findViewById<TextView>(R.id.tv_wind)
        var city = viewChild.findViewById<TextView>(R.id.city)

        city.text = mcity.city
        tv_weather.text = mcity.temperature
        tv_wind.text = mcity.wind
        return viewChild
    }

    /**
     * make progressBar percentage and messages invisible, button visible
     * if it's the case switch to the opposite
     */
    private fun toggleVisibleAndShowButtonRecommencer(){
        if(_binding.recommencer.visibility==View.VISIBLE){
            _binding.progressBar1.visibility = View.VISIBLE
            _binding.percentage.visibility = View.VISIBLE
            _binding.displayMessage.visibility = View.VISIBLE
            _binding.recommencer.visibility = View.INVISIBLE
        }else{
            _binding.progressBar1.visibility = View.INVISIBLE
            _binding.percentage.visibility = View.INVISIBLE
            _binding.displayMessage.visibility = View.INVISIBLE
            _binding.recommencer.visibility = View.VISIBLE
        }

    }

}

