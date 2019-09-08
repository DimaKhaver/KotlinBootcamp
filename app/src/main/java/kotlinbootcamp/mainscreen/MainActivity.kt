package kotlinbootcamp.mainscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinbootcamp.R
import kotlinbootcamp.basicappanatomy.BasicAppAnatomyActivity
import kotlinbootcamp.constraintlayout.ColorMyViewsActivity
import kotlinbootcamp.coroutinesandroom.SleepActivity
import kotlinbootcamp.databinding.BindDataActivity
import kotlinbootcamp.devbyteviewer.ui.DevByteActivity
import kotlinbootcamp.gdgfinder.GdgMainActivity
import kotlinbootcamp.imagerscsandcompatibility.ImageResourcesActivity
import kotlinbootcamp.lifecycleandlogging.ClickerActivity
import kotlinbootcamp.marsrealestate.MarsActivity
import kotlinbootcamp.navigationpaths.NavigationMainActivity
import kotlinbootcamp.viewmodelandviewmodelfactory.GuessTheWordActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainAdapter.ClickListener {

    private lateinit var intentOptions: Intent
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: MainAdapter
    private lateinit var rvLayoutManager: LinearLayoutManager
    private val namesInList: Array<String> =
        arrayOf("basic app anatomy", "image rsc & compatibility", "color my views", "data binding",
            "navigation paths", "clicker app", "vm & vm factory", "coroutines basics", "mars real estate",
            "dev byte viewer"/*, "gdg finder"*/)

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
            this.namesInList[0] -> startNewActivity(this, BasicAppAnatomyActivity::class.java)
            this.namesInList[1] -> startNewActivity(this, ImageResourcesActivity::class.java)
            this.namesInList[2] -> startNewActivity(this, ColorMyViewsActivity::class.java)
            this.namesInList[3] -> startNewActivity(this, BindDataActivity::class.java)
            this.namesInList[4] -> startNewActivity(this, NavigationMainActivity::class.java)
            this.namesInList[5] -> startNewActivity(this, ClickerActivity::class.java)
            this.namesInList[6] -> startNewActivity(this, GuessTheWordActivity::class.java)
            this.namesInList[7] -> startNewActivity(this, SleepActivity::class.java)
            this.namesInList[8] -> startNewActivity(this, MarsActivity::class.java)
            this.namesInList[9] -> startNewActivity(this, DevByteActivity::class.java)
          //this.namesInList[10] -> startNewActivity(this, GdgMainActivity::class.java)
        }
    }
}
