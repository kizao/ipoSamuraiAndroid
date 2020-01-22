
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.montamakk.iposamurai.R
import com.montamakk.iposamurai.model.IpoItem

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // 独自に作成したListener
    interface ItemClickListener {
        fun onItemClick(view: View, item: IpoItem)
    }

    val companyName: TextView = view.findViewById(R.id.companyName)
    val minPrice: TextView = view.findViewById(R.id.minPrice)
    val maxPrice: TextView = view.findViewById(R.id.maxPrice)
    val applicationStart: TextView = view.findViewById(R.id.applicationStart)
    val applicationEnd: TextView = view.findViewById(R.id.applicationEnd)
    init {
        // layoutの初期設定するときはココ
    }

}