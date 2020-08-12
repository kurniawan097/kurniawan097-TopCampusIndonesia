package com.kurniawan.topcampusindonesia


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var adapter: ArrayAdapter<*>

    private val list = ArrayList<Campus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }

        setContentView(R.layout.activity_main)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "10 Top Campus"


            tv_campus.setHasFixedSize(true)

            list.addAll(getListCampus())
            showRecyclerCardView()



            adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.data_name))




        }


    fun getListCampus(): ArrayList<Campus> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listCampus = ArrayList<Campus>()
        for (position in dataName.indices) {
            val campus = Campus(
                dataName[position],
                dataPhoto[position]
            )
            listCampus.add(campus)
        }
        return listCampus
    }



    private fun showRecyclerGrid() {
        tv_campus.layoutManager = GridLayoutManager(this, 2)
        val gridCampusAdapter = GridAdapter(list)
        tv_campus.adapter = gridCampusAdapter
    }

    private fun showRecyclerCardView() {
        tv_campus.layoutManager = LinearLayoutManager(this)
        val cardViewCampusAdapter = CardAdapter(list)
        tv_campus.adapter = cardViewCampusAdapter
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchView: SearchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        showRecyclerCardView()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_cardview -> {
                showRecyclerCardView()
            }

            R.id.action_grid -> {
                showRecyclerGrid()
            }

            R.id.action_logout -> {
                logout()
            }
        }
    }




}