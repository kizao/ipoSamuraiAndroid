
import android.view.View
import android.widget.ImageView
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
    val proPrice: TextView = view.findViewById(R.id.proPrice)
    val applicationDate: TextView = view.findViewById(R.id.applicationDate)
    val assessment: ImageView = view.findViewById(R.id.assessment)
    init {
        // layoutの初期設定するときはココ
    }

}