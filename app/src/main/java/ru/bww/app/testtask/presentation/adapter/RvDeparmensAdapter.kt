package ru.bww.app.testtask.presentation.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_department.view.*
import org.jetbrains.anko.image
import ru.bww.app.testtask.R
import ru.bww.app.testtask.model.json_objects.Department
import ru.bww.app.testtask.ui.frags.Frag2Str

class RvDeparmensAdapter(
    val frag: Frag2Str,
    val departments: List<Department>
) : RecyclerView.Adapter<RvDeparmensAdapter.DepViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, pusition: Int): DepViewHolder {
        return DepViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.rv_department, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return departments.size
    }

    override fun onBindViewHolder(depViewHolder: DepViewHolder, position: Int) {
        depViewHolder.itemBind(frag, departments, position)
    }

    class DepViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun itemBind(frag: Frag2Str, departments: List<Department>, position: Int) {
            val department = departments[position]
            itemView.tvDepName.text = department.Name
            when{
                department.Departments == null && department.Employees == null
                -> {
                    itemView.ivArrow.visibility = View.INVISIBLE
                }
                department.Departments != null
                -> {
                    itemView.setOnClickListener {
                        when{
                            itemView.ivArrow.tag.equals("right")
                            -> {
                                itemView.ivArrow.image = itemView.resources.getDrawable(R.drawable.ic_arrow_down_black_24dp).current
                                itemView.ivArrow.tag = "down"
                                itemView.rvDeparmens.layoutManager = LinearLayoutManager(itemView.context)
                                itemView.rvDeparmens.adapter = RvDeparmensAdapter(
                                    frag,
                                    department.Departments
                                )
                                itemView.rvDeparmens.visibility = View.VISIBLE
                            }
                            itemView.ivArrow.tag.equals("down")
                            -> {
                                itemView.ivArrow.image = itemView.resources.getDrawable(R.drawable.ic_arrow_right_black_24dp).current
                                itemView.ivArrow.tag = "right"
                                itemView.rvDeparmens.visibility = View.GONE
                            }
                        }
                    }
                }
                department.Employees != null
                -> {
                    itemView.setOnClickListener {
                        when{
                            itemView.ivArrow.tag.equals("right")
                            -> {
                                itemView.ivArrow.image = itemView.resources.getDrawable(R.drawable.ic_arrow_down_black_24dp).current
                                itemView.ivArrow.tag = "down"
                                itemView.rvDeparmens.layoutManager = LinearLayoutManager(itemView.context)
                                itemView.rvDeparmens.adapter = RvEmployeesAdapter(frag, department.Employees)
                                itemView.rvDeparmens.visibility = View.VISIBLE
                            }
                            itemView.ivArrow.tag.equals("down")
                            -> {
                                itemView.ivArrow.image = itemView.resources.getDrawable(R.drawable.ic_arrow_right_black_24dp).current
                                itemView.ivArrow.tag = "right"
                                itemView.rvDeparmens.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }

    }

}
