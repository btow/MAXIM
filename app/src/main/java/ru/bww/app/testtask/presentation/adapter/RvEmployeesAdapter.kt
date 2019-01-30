package ru.bww.app.testtask.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag3_per.view.*
import ru.bww.app.testtask.BuildConfig
import ru.bww.app.testtask.R
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.model.json_objects.Employee
import ru.bww.app.testtask.ui.frags.Frag2Str

class RvEmployeesAdapter(
    val frag: Frag2Str
    , val employees: List<Employee>) : RecyclerView.Adapter<RvEmployeesAdapter.EmployViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, pos: Int): EmployViewHolder {
        return EmployViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.rv_employ, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(employViewHolder: EmployViewHolder, pos: Int) {
        employViewHolder.itemBind(frag, employees, pos)
    }


    class EmployViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun itemBind(frag: Frag2Str, employees: List<Employee>, pos: Int) {
            val employee = employees[pos]
            itemView.tvEmployName.text = employee.Name
            itemView.setOnClickListener {
                frag.openEmployCard(employee)
            }

        }

    }

    interface Interface {
        fun openEmployCard(employee : Employee)
    }
}
