package com.claudio.udemycourse.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.claudio.udemycourse.convidados.constants.DataBaseConstants
import com.claudio.udemycourse.convidados.databinding.FragmentAbsentBinding
import com.claudio.udemycourse.convidados.view.adapter.GuestsAdapter
import com.claudio.udemycourse.convidados.view.listener.OnGuestListener
import com.claudio.udemycourse.convidados.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private val adapter: GuestsAdapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentAbsentBinding.inflate(inflater, container, false)

        // Layout
        binding.recyclerAllGuests.layoutManager  = LinearLayoutManager(context)

        // Adapter
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                Toast.makeText(context, "Convidado removido.", Toast.LENGTH_SHORT).show()
                viewModel.getAbsent()
            }

        }

        adapter.attachListener(listener)

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAbsent()
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}