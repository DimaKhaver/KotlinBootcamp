package kotlinbootcamp.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlinbootcamp.R
import kotlinx.android.synthetic.main.main_list_of_tasks.view.*

class MainAdapter(private val taskArray: Array<String>, private val clickListener: ClickListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var onClickListener: ClickListener? = null

    init {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context).inflate(R.layout.main_list_of_tasks, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return this.taskArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = taskArray[position]
        holder.textView.setOnClickListener { onClickListener?.onClick(holder.textView.text as String) }
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.item_name
      //var rootView: View = itemView.rootView
    }

    public interface ClickListener {
        public fun onClick(textVal: String)
    }

}