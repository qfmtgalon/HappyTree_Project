import android.graphics.Outline
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.happytree.R
import com.example.happytree.database.FarmDatabase.Farm

class FarmAdapter : RecyclerView.Adapter<FarmAdapter.MyViewHolder>() {

    private var itemList = emptyList<Farm>()
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(farm: Farm)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cv)
        val diseaseTextView: TextView = itemView.findViewById(R.id.txtDisease)
        val dateTextView: TextView = itemView.findViewById(R.id.txtDate)
        val numTreeTextView: TextView = itemView.findViewById(R.id.txtNumTree)

        init {
            // Set the outline provider for the CardView
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cardView.clipToOutline = true
                cardView.outlineProvider = createOutlineProvider()
            }
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        private fun createOutlineProvider(): ViewOutlineProvider {
            return object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    val cornerRadius = view.resources.getDimension(R.dimen.card_corner_radius)
                    outline.setRoundRect(0, 0, view.width, view.height, cornerRadius)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclelayout, parent, false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.diseaseTextView.text = currentItem.disease
        holder.dateTextView.text = currentItem.dateTime
        holder.numTreeTextView.text = currentItem.numberOfTrees.toString()
        holder.cardView.setOnClickListener {
            onItemClickListener?.onItemClick(currentItem)
        }
    }

    override fun getItemCount() = itemList.size

    fun setData(item: List<Farm>) {
        this.itemList = item
        notifyDataSetChanged()
    }
}
