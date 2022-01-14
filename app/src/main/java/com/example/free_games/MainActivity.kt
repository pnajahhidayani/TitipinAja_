package com.example.free_games

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.free_games.R
import com.example.free_games.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var BottomNavigationMenu: BottomNavigationView
    private lateinit var viewPager: ViewPager
    private lateinit var prevMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BottomNavigationMenu = findViewById(R.id.bnv)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.setOffscreenPageLimit(2)
        viewPager.setCurrentItem(0)
        prevMenuItem = BottomNavigationMenu.getMenu().getItem(0)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) { }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    BottomNavigationMenu.getMenu().getItem(0).setChecked(false);

                BottomNavigationMenu.getMenu().getItem(position).setChecked(true);
                prevMenuItem = BottomNavigationMenu.getMenu().getItem(position);
            }
        })




        BottomNavigationMenu.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.id_home -> {
                        viewPager.setCurrentItem(0)
//                        for (menuitem in BottomNavigationMenu.menu.iterator()) {
//                            menuitem.isEnabled = true
//                        }
//                        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
////                        val menu = BottomNavigationMenu.menu.findItem(R.id.id_home)
////                        menu?.isEnabled = false
//
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.content_area, RecyclerView())
//                            .commit()

                    }
                    R.id.id_feed -> {
                        viewPager.setCurrentItem(1)
//                        for (menuitem in BottomNavigationMenu.menu.iterator()) {
//                            menuitem.isEnabled = true
//                        }
//                        val menu = BottomNavigationMenu.menu.findItem(R.id.id_feed)
//                        menu?.isEnabled = false
//
//
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.content_area, RSSFragment())
//                            .addToBackStack(null)
//                            .commit()
                    }
                    R.id.id_discussion -> {
                        viewPager.setCurrentItem(2)
//                        for (menuitem in BottomNavigationMenu.menu.iterator()) {
//                            menuitem.isEnabled = true
//                        }
//                        val menu = BottomNavigationMenu.menu.findItem(R.id.id_discussion)
//                        menu?.isEnabled = false
//
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.content_area, Forum())
//                            .addToBackStack(null)
//                            .commit()
                    }
                }
                true
            })

//        supportFragmentManager.addOnBackStackChangedListener {
//            if (supportFragmentManager.backStackEntryCount == 0) {
//                for (menuitem in BottomNavigationMenu.menu.iterator()) {
//                    menuitem.isEnabled = true
//                }
    }
}





























//        BottomNavigationMenu.setOnNavigationItemReselectedListener { it ->
//            when (it.itemId) {
//                R.id.id_home -> {
//                    for(menuitem in BottomNavigationMenu.menu.iterator())
//                    {
//                        menuitem.isEnabled = true
//                    }
//                    val menu = BottomNavigationMenu.menu.findItem(R.id.id_home)
//                    menu?.isEnabled = false
//
//                    CurrentFragment = RecyclerView()
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.content_area, CurrentFragment)
//                        .commit()
//
//                }
//                R.id.id_profile -> {
//                    for(menuitem in BottomNavigationMenu.menu.iterator())
//                    {
//                        menuitem.isEnabled = true
//                    }
//                    val menu = BottomNavigationMenu.menu.findItem(R.id.id_profile)
//                    menu?.isEnabled = false
//                    CurrentFragment = LoginFragment()
//                    supportFragmentManager.beginTransaction()
//                        .add(R.id.content_area, CurrentFragment)
//                        .addToBackStack(null)
//                        .commit()
//                }
//
//                R.id.id_list -> {
//                    val intent = Intent(this@MainActivity, Forum::class.java)
//                    startActivity(intent)
//                }
//            }
//        }
//
//        //BottomNavigationMenu.setOnNavigationItemSelectedListener(navListener)
//    }
//}

//    val navListener = BottomNavigationView.OnNavigationItemReselectedListener {
//        when(it.itemId){
//            R.id.id_home -> {
//                CurrentFragment = RecyclerView()
//            }
//            R.id.id_profile -> {
//                CurrentFragment = LoginFragment()
//            }
//        }
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.content_area, CurrentFragment)
//            .commit()

