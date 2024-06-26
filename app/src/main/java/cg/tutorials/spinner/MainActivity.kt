package cg.tutorials.spinner

import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cg.tutorials.spinner.databinding.ActivityMainBinding
import cg.tutorials.spinner.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity() {
    private var array = mutableListOf<String>("C","C++")
    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.staticSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val selectedItem = binding.staticSpinner.selectedItem as String
                Toast.makeText(this@MainActivity, "you selected position->$position and item-> $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                nothing for now
            }
        }
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,array)
        binding.dySpinner.adapter = arrayAdapter

        binding.floatingBtn.setOnClickListener{
            val dialogBinding=CustomDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(this).apply {
                setContentView(dialogBinding.root)
                show()
                window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
            }
            dialogBinding.btnSubmit.setOnClickListener{
                if(dialogBinding.etDialog.text.trim().toString().isBlank()){
                    dialogBinding.etDialog.error ="Enter Name"
                }
                else{
                    array.add(dialogBinding.etDialog.text.trim().toString())
                    dialog.dismiss()
                }
            }
        }
    }
}