package kotlinbootcamp.mainscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinbootcamp.R
import kotlinbootcamp.basicappanatomy.BasicAppAnatomyActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainAdapter.ClickListener {

    private lateinit var intentOptions: Intent
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: MainAdapter
    private lateinit var rvLayoutManager: LinearLayoutManager
    private val namesInList: Array<String> = arrayOf("basic app anatomy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        recyclerView = recycler_view
        recyclerView.apply {
            rvAdapter = MainAdapter(namesInList, this@MainActivity)
            rvLayoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = rvAdapter
            recyclerView.layoutManager = rvLayoutManager
        }
    }

    private fun startNewActivity(packageContext: Context, newClass: Class<*>) {
        intentOptions = Intent(packageContext, newClass)
        startActivity(intentOptions)
    }

    override fun onClick(textVal: String) {
        when (textVal) {
            "basic app anatomy" -> startNewActivity(this, BasicAppAnatomyActivity::class.java)

        }
    }
}
