
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.montamakk.iposamurai.IpoData
import com.montamakk.iposamurai.R

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // 独自に作成したListener
    interface ItemClickListener {
        fun onItemClick(view: View, item: IpoData)
    }

    val itemTextView: TextView = view.findViewById(R.id.itemTextView)
    val itemTextView2: TextView = view.findViewById(R.id.itemTextView2)
    val itemTextView3: TextView = view.findViewById(R.id.itemTextView3)
    val itemTextView4: TextView = view.findViewById(R.id.itemTextView4)
    val itemTextView5: TextView = view.findViewById(R.id.itemTextView5)
    init {
        // layoutの初期設定するときはココ
    }

}