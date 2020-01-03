
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iposamurai.R

class RecyclerAdapter(private val context: Context, private val itemClickListener: RecyclerViewHolder.ItemClickListener, private val itemList:MutableList<Map<String,Any?>>) : RecyclerView.Adapter<RecyclerViewHolder>() {

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

            var item = itemList.get(position)
            println(item)
            it.itemTextView.text = item["companyNameAssessment"] as CharSequence?
            it.itemTextView2.text = item["minPrice"] as CharSequence?
            it.itemTextView3.text = item["maxPrice"] as CharSequence?
            it.itemTextView4.text = item["applicationStart"] as CharSequence?
            it.itemTextView5.text = item["applicationEnd"] as CharSequence?
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.list_item, parent, false)

        mView.setOnClickListener { view ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, itemList.get(it.getChildAdapterPosition(view)))
            }
        }

        return RecyclerViewHolder(mView)
    }

}