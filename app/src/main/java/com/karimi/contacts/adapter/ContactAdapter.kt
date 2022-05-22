package com.karimi.contacts.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.karimi.contacts.R
import com.karimi.contacts.databinding.ListItemContactsBinding

class ContactAdapter(var list: ArrayList<String>, var context: Context) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    var rawIndex : Int = -1


    inner class MyViewHolder(binding: ListItemContactsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name: TextView = binding.textItem1
        var alefba: TextView = binding.textAlfabet
        var image: ImageView = binding.picContact
        var linear: LinearLayout = binding.linearListItem
        var line: View = binding.line
        var box_cmi: ConstraintLayout = binding.boxCmi.constraintCmi

        var linear1: View = binding.mmmmmmmm

        init {
            itemView.setOnClickListener {
                rawIndex = position
                notifyDataSetChanged()
                Toast.makeText(context, rawIndex.toString() , Toast.LENGTH_SHORT).show()
            }

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemContactsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_contacts, parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list[position]
        holder.image.setImageDrawable(textDrawable(position))
        holder.alefba.text = "A"

        showMoreInfo(holder , position)

    }


    override fun getItemCount(): Int {
        return list.size
    }


//
//    override fun getFilter(): Filter {
//        return newsFilter
//    }
//
//
//
//    fun getFilter(): Filter? {
//        return newsFilter
//    }
//
//    val newsFilter: Filter = object : Filter() {
//        override fun performFiltering(constraint: CharSequence): FilterResults {
//            val filterdNewList: MutableList<String> = java.util.ArrayList<String>()
//            if (constraint == null || constraint.length == 0) {
//                filterdNewList.addAll(list)
//            } else {
//                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
//                for (customer in list) {
//                    if (list.contains(filterPattern)) filterdNewList.add(
//
//                    )
//                   filterdNewList.add(list.)
//                }
//            }
//            val results = FilterResults()
//            results.values = filterdNewList
//            results.count = filterdNewList.size
//            return results
//        }
//
//        override fun publishResults(constraint: CharSequence, results: FilterResults) {
//            list.clear()
//            list.addAll(results.values as java.util.ArrayList<*>)
//            notifyDataSetChanged()
//        }
//    }




    private fun textDrawable(pos : Int): TextDrawable? {
        val generator = ColorGenerator.MATERIAL // or use DEFAULT
        val color1 = generator.randomColor
//        val color2 = generator.getColor("user@gmail.com")
        val builder = TextDrawable.builder()
            .beginConfig()
            .fontSize(24)
            .textColor(Color.WHITE)
            .useFont(Typeface.DEFAULT)
            .endConfig()
            .round()
        val ic1 = builder.build(list[pos][0] + "", color1)

        return ic1
    }


   private fun showMoreInfo(holder: MyViewHolder, position: Int){
        if (rawIndex == position){
            holder.box_cmi.visibility = View.VISIBLE
            holder.line.visibility = View.VISIBLE
            holder.linear.background = getDrawable(context, R.drawable.back_call)
            holder.linear1.background = null
        }else{
            holder.box_cmi.visibility = View.GONE
            holder.line.visibility = View.GONE
            holder.linear.background = null
        }

    }

}
