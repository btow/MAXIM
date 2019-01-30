package ru.bww.app.testtask.presentation.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_department.view.*
import org.jetbrains.anko.image
import ru.bww.app.testtask.R
import ru.bww.app.testtask.model.response_json.ResponseJSONStructure
import ru.bww.app.testtask.ui.frags.Frag2Str

class RvStructureAdapter(
    val frag: Frag2Str,
    val response: ResponseJSONStructure
)
    : RecyclerView.Adapter<RvStructureAdapter.CompanyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): CompanyViewHolder {
        return CompanyViewHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.rv_department, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(companyViewHolder: CompanyViewHolder, position: Int) {
        companyViewHolder.itemBind(frag, response)
    }

    class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun itemBind(
            frag: Frag2Str,
            response: ResponseJSONStructure
        ) {
            if (response.Departments.isEmpty()){
                itemView.ivArrow.visibility = View.INVISIBLE
            } else {
                itemView.setOnClickListener {
                    when{
                        (it.ivArrow.tag).equals("right")
                        -> {
                            it.ivArrow.image = itemView.resources.getDrawable(R.drawable.ic_arrow_down_black_24dp)
                            it.ivArrow.tag = "down"
                            itemView.rvDeparmens.layoutManager = LinearLayoutManager(itemView.context)
                            itemView.rvDeparmens.adapter = RvDeparmensAdapter(frag, response.Departments)
                            itemView.rvDeparmens.visibility = View.VISIBLE
                        }
                        (it.ivArrow.tag).equals("down")
                        -> {
                            it.ivArrow.image = itemView.resources.getDrawable(R.drawable.ic_arrow_right_black_24dp)
                            it.ivArrow.tag = "right"
                            itemView.rvDeparmens.visibility = View.GONE
                        }
                    }
                }
            }
            itemView.tvDepName.text = response.Name
        }

    }

}
