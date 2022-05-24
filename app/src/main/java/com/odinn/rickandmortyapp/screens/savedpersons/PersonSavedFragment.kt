package com.odinn.rickandmortyapp.screens.savedpersons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.odinn.rickandmortyapp.APP
import com.odinn.rickandmortyapp.DETAIL
import com.odinn.rickandmortyapp.R
import com.odinn.rickandmortyapp.databinding.FragmentPersonSavedBinding
import com.odinn.rickandmortyapp.model.Result


class PersonSavedFragment : Fragment() {
    private var mBinding: FragmentPersonSavedBinding? = null
    private val binding get() = mBinding!!
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        PersonListAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentPersonSavedBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(PersonSavedViewModel::class.java)
        recyclerView = binding.rvPersonsList
        recyclerView.adapter = adapter
        viewModel.getAllSavedPersons().observe(viewLifecycleOwner) { list ->
            adapter.setList(list.asReversed())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    companion object{
        fun clickPerson(person: Result){
            val bundle = Bundle()
            bundle.putSerializable(DETAIL, person)
            APP.navController.navigate(R.id.personDetailFragment,bundle)
        }
    }

}