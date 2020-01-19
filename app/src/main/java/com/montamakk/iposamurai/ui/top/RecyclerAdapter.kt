
import RecyclerViewHolder.ItemClickListener
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.montamakk.iposamurai.R
import com.montamakk.iposamurai.model.IpoItem

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
        holder.let {
            var item = itemList!![position]
            it.itemTextView.text = item.companyName
            it.itemTextView2.text = item.minPrice
            it.itemTextView3.text = item.maxPrice
            it.itemTextView4.text = item.applicationStart
            it.itemTextView5.text = item.applicationEnd
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