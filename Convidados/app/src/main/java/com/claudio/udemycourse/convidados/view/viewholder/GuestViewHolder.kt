package com.claudio.udemycourse.convidados.view.viewholder

import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.claudio.udemycourse.convidados.R
import com.claudio.udemycourse.convidados.databinding.RowGuestBinding
import com.claudio.udemycourse.convidados.model.GuestModel
import com.claudio.udemycourse.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        listener.onDelete(guest.id)
                    }
                })
                .setNegativeButton("Não", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        null
                    }
                })
                .create()
                .show()
            true
        }
    }
}