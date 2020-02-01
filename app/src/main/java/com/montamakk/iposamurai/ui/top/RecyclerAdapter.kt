
import RecyclerViewHolder.ItemClickListener
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.montamakk.iposamurai.R
import com.montamakk.iposamurai.model.IpoItem
import java.text.SimpleDateFormat
import java.util.*

class RecyclerAdapter(private val context: Context, private val itemClickListener: ItemClickListener, private val itemList: MutableList<IpoItem>?) : RecyclerView.Adapter<RecyclerViewHolder>() {

    private var mRecyclerView : RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var item = itemList!![position]
        val date = Date()
        val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val today = format.format(date)
        val isBbPeriod = SimpleDateFormat("yyyy/MM/dd").let{
            val range = it.parse(item.applicationStart)..it.parse(item.applicationEnd)
            // 含まれているかを確認する
            range.contains(it.parse(today))
        }
        var df  = SimpleDateFormat("yyyy/MM/dd")
        if(isBbPeriod) {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.getContext(),
                    R.color.colorLightRed
                )
            )
        }
        else if(df.parse(today) > df.parse(item.applicationEnd)){
          holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorLightGray))
        }else{
          holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorLightWhite))
        }
        var assessment = 0
        when(item.assessment) {
            "A" -> assessment = R.drawable.a
            "B" -> assessment = R.drawable.b
            "C" -> assessment = R.drawable.c
            "D" -> assessment = R.drawable.d
            else -> assessment = R.drawable.question
        }


        holder.let {
            it.companyName.text = item.companyName.replace("株式会社", "").trim()
            it.minPrice.text = item.minPrice
            it.maxPrice.text = item.maxPrice
            it.applicationStart.text = item.applicationStart
            it.applicationEnd.text = item.applicationEnd
            it.assessment.setImageResource(assessment)
        }
    }

    override fun getItemCount(): Int {
       return itemList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.list_item, parent, false)
        mView.setOnClickListener { view ->
            mRecyclerView?.let {
                var i = it.getChildAdapterPosition(view)
                itemClickListener.onItemClick(view, itemList!![i])
            }
        }
        return RecyclerViewHolder(mView)
    }
}
