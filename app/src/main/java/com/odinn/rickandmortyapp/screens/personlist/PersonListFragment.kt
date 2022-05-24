package com.odinn.rickandmortyapp.screens.personlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.odinn.rickandmortyapp.APP
import com.odinn.rickandmortyapp.CURRENT_API_PAGE
import com.odinn.rickandmortyapp.DETAIL
import com.odinn.rickandmortyapp.R
import com.odinn.rickandmortyapp.databinding.FragmentPersonListBinding
import com.odinn.rickandmortyapp.model.Result

class PersonListFragment : Fragment() {
    private var mBinding:FragmentPersonListBinding ? = null
    private val binding get() = mBinding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var currentPerson:Result
    private val adapter by lazy {
        PersonListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentPersonListBinding.inflate(layoutInflater, container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(PersonListViewModel::class.java)
        viewModel.getAllCharacters(CURRENT_API_PAGE)
        recyclerView = binding.rvPersonsList
        recyclerView.adapter = adapter
        viewModel.mCharacter.observe(viewLifecycleOwner, { list->
            adapter.setList(list.results)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    companion object{
        fun clickPerson(person:Result){
            val bundle = Bundle()
            bundle.putSerializable(DETAIL, person)
            APP.navController.navigate(R.id.personDetailFragment,bundle)
        }
    }

}