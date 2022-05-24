package com.odinn.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.odinn.rickandmortyapp.data.room.PersonsRoomDatabase
import com.odinn.rickandmortyapp.data.room.repository.PersonsRepository
import com.odinn.rickandmortyapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    lateinit var navController: NavController
    lateinit var mDatabaseRepository: PersonsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        initDatabase()
        APP = this
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_favorite ->{
                navController.navigate(R.id.personSavedFragment)
                true
            }
            R.id.item_persons ->{
                navController.navigate(R.id.personListFragment)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    fun initDatabase(){
        val dao = PersonsRoomDatabase.getInstance(this).getPersonsDAO()
        mDatabaseRepository = PersonsRepository(dao)
    }

}