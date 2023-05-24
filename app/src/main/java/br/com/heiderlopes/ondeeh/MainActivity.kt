package br.com.heiderlopes.ondeeh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import br.com.heiderlopes.ondeeh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpListener()

        Toast.makeText(this, BuildConfig.BASE_URL_VIA_CEP, Toast.LENGTH_LONG).show()
    }


    private fun setUpListener() {
        binding.btSearch.setOnClickListener {
            val searchIntent = Intent(this, AddressDetailActivity::class.java)
            searchIntent.putExtra(AddressDetailActivity.EXTRA_CEP, binding.etCep.text.toString())
            searchResult.launch(searchIntent)
        }
    }

    private val searchResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == RESULT_OK) {
            //result.data?.getStringExtra("CHAVE")?.let { result -> }
            binding.etCep.setText("")
        } else {
            //No momento não teremos nenhuma interação caso resultCode seja diferente de RESULT_OK
        }
    }

}
