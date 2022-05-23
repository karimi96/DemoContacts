package com.karimi.contacts.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Filter.FilterResults
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.karimi.contacts.R
import com.karimi.contacts.databinding.ListItemContactsBinding
import com.karimi.contacts.model.User
import java.util.*
import kotlin.collections.ArrayList

class ContactAdapter(var list: ArrayList<User>, var context: Context) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() , Filterable {

    var rawIndex : Int = -1

    lateinit var listAlfabet : ArrayList<String>
    lateinit var pos : ArrayList<Int>

    var last_position = -1

    var ls: ArrayList<User> =list
    var ll: ArrayList<User> = ls

    var listColor = arrayOf(R.color.black)


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
                if (last_position == position){
                    if (rawIndex == -1) rawIndex = position
                    else rawIndex = -1
                }else{
                    rawIndex = position
                }
                notifyItemChanged(last_position)
                last_position = position
                notifyItemChanged(position)
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
        holder.name.text = list[position].firstName + " " + list[position].lastName
        holder.image.setImageDrawable(textDrawable(position))

        showMoreInfo(holder , position)
        getFirstIndex()
        setAlfabet(holder,position)

    }


    override fun getItemCount(): Int {
        return list.size
    }



    override fun getFilter(): Filter {
        return newsFilter
    }


    val newsFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterdNewList: MutableList<User> = java.util.ArrayList<User>()

            if (constraint == null || constraint.length == 0) {
                filterdNewList.addAll(ls)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for ( uu :User in ls) {
                    if (uu.firstName.lowercase().contains(filterPattern)) filterdNewList.add(uu)
                }
            }
            val results = FilterResults()
            results.values = filterdNewList
            results.count = filterdNewList.size
            return results
        }


        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            ll.clear()
            ll.addAll(results.values as java.util.ArrayList<User>)
            notifyDataSetChanged()
        }

    }




    private fun textDrawable(pos : Int): TextDrawable? {
        val generator = ColorGenerator.MATERIAL // or use DEFAULT
        val color1 = generator.randomColor
        val color2 = generator.getColor("user@gmail.com")
        val builder = TextDrawable.builder()
            .beginConfig()
            .fontSize(40)
            .bold()
            .textColor(Color.WHITE)
            .useFont(Typeface.createFromAsset(context.assets , "font/inter.ttf" ))
            .endConfig()
            .round()
        val ic1 = builder.build(list[pos].firstName[0] + "", color1)

        return ic1
    }


   private fun showMoreInfo(holder: MyViewHolder, position: Int){
        if (rawIndex == position){
            holder.linear1.background = null
            holder.box_cmi.visibility = View.VISIBLE
            holder.line.visibility = View.VISIBLE
//            holder.linear.background = getDrawable(context,R.color.toi)
            holder.linear.background = getDrawable(context, R.drawable.back_call)
        }else{
            holder.box_cmi.visibility = View.GONE
            holder.line.visibility = View.GONE
            holder.linear.background = null
        }

    }


    fun getFirstIndex(){
        pos = ArrayList();
        pos.add(0)

        listAlfabet = ArrayList()
        listAlfabet.add(0 ,list[0].firstName[0].uppercase() )

        var i = 1
        while (i < list.size){
            if(list[i].firstName[0].uppercase() in listAlfabet){
                i++
                continue
            } else {
                listAlfabet.add(list[i].firstName[0].uppercase() + "")
                pos.add(i)
                i++
            }
        }

        Log.e("qqq", "getFirstIndex:  $listAlfabet" )
    }



    fun setAlfabet(holder: MyViewHolder, position: Int){
        var i = 0
        while (i < pos.size){
            if (position == pos[i]) holder.alefba.text= listAlfabet[i]
            i++
        }
    }






//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//                if (charSearch.isEmpty()) {
//                    countryFilterList = list
//                } else {
//                    val resultList = ArrayList<User>()
//                    for (row in list) {
//                        if (row.firstName.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
//                            resultList.add(row)
//                        }
//                    }
//                    countryFilterList = resultList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = countryFilterList
//                return filterResults
//            }
//
//            @Suppress("UNCHECKED_CAST")
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                countryFilterList = results?.values as ArrayList<User>
//                notifyDataSetChanged()
//            }
//
//        }
//    }







//    override fun getFilter(): Filter? {
//        return newsFilter
//    }
//
//    private val newsFilter: Filter = object : Filter() {
//        override fun performFiltering(constraint: CharSequence): FilterResults {
//            val filterdNewList: MutableList<User> = java.util.ArrayList<User>()
//            if (constraint == null || constraint.length == 0) {
//                filterdNewList.addAll(list)
//            } else {
//                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
//                for (userr : User in list) {
//                    if (userr.firstName.toLowerCase().contains(filterPattern)) filterdNewList.add(userr)
//                }
//            }
//            val results = FilterResults()
//            results.values = filterdNewList
//            results.count = filterdNewList.size
//            if (results.count == 0) Toast.makeText(context, "موردی یافت نشد", Toast.LENGTH_SHORT)
//                .show()
//            return results
//        }
//
//        override fun publishResults(constraint: CharSequence, results: FilterResults) {
////            list.clear()
//            countryFilterList = results?.values as ArrayList<User>
//            notifyDataSetChanged()
//        }
//    }





//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//
//
//
//                val charString = constraint?.toString() ?: ""
//                if (charString.isEmpty()) photosListFiltered = photosList else {
//                    val filteredList = ArrayList<User>()
//                    photosList
//                        .filter {
//                            (it.firstName.contains(constraint!!)) or
//                                    (it.lastName.contains(constraint))
//
//                        }
//                        .forEach { filteredList.add(it) }
//                    photosListFiltered = filteredList
//
//                }
//                return FilterResults().apply { values = photosListFiltered }
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//
//                photosListFiltered = if (results?.values == null)
//                    ArrayList()
//                else
//                    results.values as ArrayList<User>
//                notifyDataSetChanged()
//            }
//        }
//    }




}
