package com.odinn.rickandmortyapp.screens.persondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.bumptech.glide.Glide
import com.odinn.rickandmortyapp.DETAIL
import com.odinn.rickandmortyapp.R
import com.odinn.rickandmortyapp.databinding.FragmentPersonDetailBinding
import com.odinn.rickandmortyapp.model.Result
import kotlinx.android.synthetic.main.fragment_person_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonDetailFragment : Fragment() {
    private var mBinding: FragmentPersonDetailBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mViewModel:PersonDetailViewModel
    lateinit var currentPerson: Result
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentPersonDetailBinding.inflate(layoutInflater,container,false)
        mViewModel = ViewModelProvider(this).get(PersonDetailViewModel::class.java)
        currentPerson = arguments?.getSerializable(DETAIL) as Result
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        mViewModel.getPerson(currentPerson)
        mViewModel.isFavorite.observe(viewLifecycleOwner){
            isFavorite = it
            if(isFavorite){
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite_aktiv)
            }else{
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite_deaktiv)
            }
        }
        with(binding){
            Glide.with(this@PersonDetailFragment)
                .load(currentPerson.image)
                .centerCrop()
                .placeholder(R.drawable.ic_foto)
                .into(imgPerson)
            txt_person_name.text = currentPerson.name
            txt_person_status.text = "${currentPerson.status} - ${currentPerson.species}"
            if (currentPerson.status == "Alive"){
                img_status_light.setImageResource(R.drawable.ic_circle_green)
            } else{
                img_status_light.setImageResource(R.drawable.ic_circle_red)
            }
            txt_last_known_loc.text = currentPerson.location?.name
            imgFavorite.setOnClickListener {
                isFavorite = if(!isFavorite){    //false
                    imgFavorite.setImageResource(R.drawable.ic_favorite_aktiv)
                    mViewModel.saveCharacter(currentPerson){}
                    true
                }else{
                    imgFavorite.setImageResource(R.drawable.ic_favorite_deaktiv)
                    mViewModel.deleteCharacter(currentPerson){}
                    false
                }
                mViewModel.isFavorite.value = isFavorite
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

}