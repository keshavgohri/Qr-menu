package com.vms.qrmenu.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vms.qrmenu.R
import com.vms.qrmenu.databinding.ActivityHomeBinding
import com.vms.qrmenu.login.LoginActivity
import com.vms.qrmenu.order_history.OrderHistoryActivity
import com.vms.qrmenu.privacy_policy.PrivacyPolicyActivity
import com.vms.qrmenu.profile.ProfileActivity
import com.vms.qrmenu.terms_and_conditions.TermsAndConditionsActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var recyclerOffers: RecyclerView
    private lateinit var adapter: OfferAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val handler = Handler(Looper.getMainLooper())
    private var currentPosition = 0
    private lateinit var recyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var orderList: MutableList<Order>

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var menuIcon: ImageView
    private lateinit var newOrders: TextView
    private lateinit var inProcessOrders: TextView
    private lateinit var navHome: LinearLayout
    private lateinit var navOrderHistory: LinearLayout
    private lateinit var navProfile: LinearLayout
    private lateinit var navPrivacy: LinearLayout
    private lateinit var navTerms: LinearLayout
    private lateinit var navLogout: LinearLayout
    private lateinit var navItems: List<LinearLayout>
    private lateinit var managerLayoutCards: LinearLayout
    val isManager = 1

    private val scrollRunnable = object : Runnable {
        override fun run() {
            if (adapter.itemCount == 0) return

            // Move to next item
            currentPosition++

            if (currentPosition >= adapter.itemCount) {
                currentPosition = 0 // loop back to first
            }

            recyclerOffers.smoothScrollToPosition(currentPosition)

            // Repeat after 1 second
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerOffers = binding.recyclerOfferDiscout
        recyclerView = binding.recyclerOrders
        drawerLayout = binding.drawerLayout
        menuIcon = binding.menuIcon
        newOrders = binding.newOrdersTab
        inProcessOrders = binding.inProcessOrdersTab

        navHome = binding.navHome
        navOrderHistory = binding.navOrderHistory
        navProfile = binding.navProfile
        navPrivacy = binding.navPrivacy
        navTerms = binding.navTerms
        navLogout = binding.navLogout
        managerLayoutCards = binding.layoutManager

        navLogout.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            val dialogView = layoutInflater.inflate(R.layout.dialog_logout, null)

            val dialog = AlertDialog.Builder(this, R.style.TransparentAlertDialog)
                .setView(dialogView)
                .setCancelable(false)
                .create()

            dialogView.findViewById<TextView>(R.id.btn_logout).setOnClickListener {
                dialog.dismiss()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

            dialogView.findViewById<ImageView>(R.id.img_cross).setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()

        }

        navItems = listOf(navHome, navOrderHistory, navProfile, navPrivacy, navTerms)

        navItems.forEach { it.setOnClickListener { view -> onNavItemClicked(view.id) } }
        setSelected(navHome)

        if (isManager == 0) {
            managerLayoutCards.visibility = View.GONE
        } else {
            managerLayoutCards.visibility = View.VISIBLE
        }

        newOrders.setOnClickListener {
            newOrders.setBackgroundResource(R.drawable.tab_selected_background)
            newOrders.setTextColor(ContextCompat.getColor(this, R.color.purple_txt))
            inProcessOrders.setBackgroundResource(R.drawable.tab_unselected_background)
            inProcessOrders.setTextColor(ContextCompat.getColor(this, R.color.txt_grey))
        }
        inProcessOrders.setOnClickListener {
            newOrders.setBackgroundResource(R.drawable.tab_unselected_background)
            newOrders.setTextColor(ContextCompat.getColor(this, R.color.txt_grey))
            inProcessOrders.setBackgroundResource(R.drawable.tab_selected_background)
            inProcessOrders.setTextColor(ContextCompat.getColor(this, R.color.purple_txt))

        }

        menuIcon.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        val offersList = listOf(
            OfferItem(
                R.drawable.ic_discount,
                "Grab 50% OFF Deals",
                "Above 200 Offer Only On Selected Items",
                "1/3"
            ),
            OfferItem(R.drawable.ic_discount, "Save 20%", "Applicable on Desserts", "2/3"),
            OfferItem(
                R.drawable.ic_discount,
                "Flat ₹100 Cashback",
                "On orders above ₹500",
                "3/3"
            )
        )

        adapter = OfferAdapter(offersList)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerOffers.layoutManager = layoutManager
        recyclerOffers.adapter = adapter


        orderList = mutableListOf(
            Order("Table 1", "4 items", "12:00 PM", "₹500", "Rohan Singh", "+91 9876543210"),
            Order("Table 2", "2 items", "12:10 PM", "₹250", "Priya Singh", "+91 9876543211"),
            Order("Table 3", "3 items", "12:20 PM", "₹350", "Aman Gupta", "+91 9876543212"),
            Order("Table 4", "4 items", "12:00 PM", "₹500", "Rohan Singh", "+91 9876543210"),
            Order("Table 5", "2 items", "12:10 PM", "₹250", "Priya Gupta", "+91 9876543211"),
            Order("Table 6", "3 items", "12:20 PM", "₹350", "Aman Gupta", "+91 9876543212"),
            Order("Table 7", "4 items", "12:00 PM", "₹500", "Rohan Singh", "+91 9876543210"),
            Order("Table 8", "2 items", "12:10 PM", "₹250", "Priya Gupta", "+91 9876543211"),
            Order("Table 9", "3 items", "12:20 PM", "₹350", "Aman Gupta", "+91 9876543212"),
            Order("Table 10", "4 items", "12:00 PM", "₹500", "Rohan Singh", "+91 9876543210"),
            Order("Table 11", "2 items", "12:10 PM", "₹250", "Priya Singh", "+91 9876543211"),
            Order("Table 12", "3 items", "12:20 PM", "₹350", "Aman Gupta", "+91 9876543212")
        )

        orderAdapter = OrderAdapter(
            orderList,
            onAcceptClick = { order ->
                val dialogView = layoutInflater.inflate(R.layout.dialog_accept_order, null)

                val dialog = AlertDialog.Builder(this, R.style.TransparentAlertDialog)
                    .setView(dialogView)
                    .setCancelable(false)
                    .create()

                dialogView.findViewById<TextView>(R.id.btn_accept).setOnClickListener {
                    dialog.dismiss()
                }

                dialogView.findViewById<ImageView>(R.id.img_cross).setOnClickListener {
                    dialog.dismiss()
                }

                dialog.show()
            },
            onRejectClick = { order ->
                val dialogView = layoutInflater.inflate(R.layout.dialog_decline_order, null)

                val dialog = AlertDialog.Builder(this, R.style.TransparentAlertDialog)
                    .setView(dialogView)
                    .setCancelable(false)
                    .create()

                dialogView.findViewById<TextView>(R.id.btn_decline).setOnClickListener {
                    dialog.dismiss()
                }

                dialogView.findViewById<ImageView>(R.id.img_cross).setOnClickListener {
                    dialog.dismiss()
                }

                dialog.show()
            })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = orderAdapter

    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(scrollRunnable, 1000) // start auto-scroll
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(scrollRunnable) // stop auto-scroll
    }

    private fun onNavItemClicked(id: Int) {
        when (id) {
            R.id.nav_home -> {
                setSelected(navHome)
                drawerLayout.closeDrawer(GravityCompat.START)
                // Don't restart HomeActivity if you're already in it
            }

            R.id.nav_order_history -> {
                setSelected(navOrderHistory)
                drawerLayout.closeDrawer(GravityCompat.START)
                startActivity(Intent(this, OrderHistoryActivity::class.java))
            }

            R.id.nav_profile -> {
                setSelected(navProfile)
                drawerLayout.closeDrawer(GravityCompat.START)
                startActivity(Intent(this, ProfileActivity::class.java))
            }

            R.id.nav_privacy -> {
                setSelected(navPrivacy)
                drawerLayout.closeDrawer(GravityCompat.START)
                startActivity(Intent(this, PrivacyPolicyActivity::class.java))
                finish()
            }

            R.id.nav_terms -> {
                setSelected(navTerms)
                drawerLayout.closeDrawer(GravityCompat.START)
                startActivity(Intent(this, TermsAndConditionsActivity::class.java))
                finish()
            }

        }
    }


    private fun setSelected(selectedLayout: LinearLayout) {
        navItems.forEach { layout ->
            val isSelected = (layout == selectedLayout)

            val textView = layout.findViewWithTag<TextView>("drawer_text")
            val imageView = layout.findViewWithTag<ImageView>("drawer_icon")

            textView?.setTextColor(
                if (isSelected) ContextCompat.getColor(this, R.color.purple_txt)
                else ContextCompat.getColor(this, R.color.hint_color)
            )

            imageView?.setColorFilter(
                if (isSelected) ContextCompat.getColor(this, R.color.purple_txt)
                else ContextCompat.getColor(this, R.color.hint_color),
                android.graphics.PorterDuff.Mode.SRC_IN
            )

            layout.setBackgroundResource(
                if (isSelected) R.drawable.bg_drawer_item_purple
                else R.drawable.bg_drawer_item_grey
            )
        }
    }

}
