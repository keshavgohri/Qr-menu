package com.vms.qrmenu.order_history

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vms.qrmenu.R
import com.vms.qrmenu.databinding.ActivityOrderHistoryBinding
import com.vms.qrmenu.home.HomeActivity
import com.vms.qrmenu.login.LoginActivity

class OrderHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderHistoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var filter: TextView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var menuIcon: ImageView
    private lateinit var navHome: LinearLayout
    private lateinit var navOrderHistory: LinearLayout
    private lateinit var navProfile: LinearLayout
    private lateinit var navPrivacy: LinearLayout
    private lateinit var navTerms: LinearLayout
    private lateinit var navItems: List<LinearLayout>
    private lateinit var navLogout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        menuIcon = binding.menuIcon
        navLogout = binding.navLogout
        navHome = binding.navHome
        navOrderHistory = binding.navOrderHistory
        navProfile = binding.navProfile
        navPrivacy = binding.navPrivacy
        navTerms = binding.navTerms
        filter = binding.txtFilter
        recyclerView = binding.recyclerOrderHistory
        recyclerView.layoutManager = LinearLayoutManager(this)

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

        menuIcon.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        navItems = listOf(navHome, navOrderHistory, navProfile, navPrivacy, navTerms)

        navItems.forEach { it.setOnClickListener { view -> onNavItemClicked(view.id) } }
        setSelected(navOrderHistory)

        filter.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_filter, null)
            bottomSheetDialog.setContentView(view)

            // Example: Access views from bottom sheet
            val btnSave = view.findViewById<Button>(R.id.btn_apply)
            btnSave.setOnClickListener {
                // Handle save action
                Toast.makeText(this, "Filters Applied!", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.show()
        }

        val sampleSections = listOf(
            OrderSection(
                "01 Oct 2025",
                listOf(
                    OrderHistory(
                        "Table 1",
                        "3 items",
                        "12:00 PM",
                        "₹450",
                        "Rohan Singh",
                        "+91 987654321",
                        "Accepted"
                    ),
                    OrderHistory(
                        "Table 2",
                        "2 items",
                        "12:15 PM",
                        "₹250",
                        "Anita Sharma",
                        "+91 987650123",
                        "Accepted"
                    ),
                    OrderHistory(
                        "Table 3",
                        "5 items",
                        "12:30 PM",
                        "₹600",
                        "Rahul Kumar",
                        "+91 987651234",
                        "Accepted"
                    )
                )
            ),
            OrderSection(
                "02 Oct 2025",
                listOf(
                    OrderHistory(
                        "Table 4",
                        "1 item",
                        "01:00 PM",
                        "₹150",
                        "Priya Patel",
                        "+91 987652345",
                        "Accepted"
                    ),
                    OrderHistory(
                        "Table 5",
                        "4 items",
                        "01:30 PM",
                        "₹500",
                        "Amit Verma",
                        "+91 987653456",
                        "Accepted"
                    )
                )
            ),
            OrderSection(
                "03 Oct 2025",
                listOf(
                    OrderHistory(
                        "Table 6",
                        "2 items",
                        "11:30 AM",
                        "₹300",
                        "Sneha Joshi",
                        "+91 987654567",
                        "Accepted"
                    ),
                    OrderHistory(
                        "Table 7",
                        "3 items",
                        "12:45 PM",
                        "₹400",
                        "Karan Mehta",
                        "+91 987654678",
                        "Accepted"
                    ),
                    OrderHistory(
                        "Table 8",
                        "1 item",
                        "01:15 PM",
                        "₹150",
                        "Neha Singh",
                        "+91 987654789",
                        "Accepted"
                    )
                )
            ),
            OrderSection(
                "04 Oct 2025",
                listOf(
                    OrderHistory(
                        "Table 9",
                        "5 items",
                        "02:00 PM",
                        "₹700",
                        "Vikram Sharma",
                        "+91 987654890",
                        "Accepted"
                    ),
                    OrderHistory(
                        "Table 10",
                        "2 items",
                        "02:30 PM",
                        "₹250",
                        "Tina Reddy",
                        "+91 987655001",
                        "Accepted"
                    )
                )
            ),
            OrderSection(
                "05 Oct 2025",
                listOf(
                    OrderHistory(
                        "Table 11",
                        "3 items",
                        "11:00 AM",
                        "₹450",
                        "Suresh Kumar",
                        "+91 987655112",
                        "Accepted"
                    ),
                    OrderHistory(
                        "Table 12",
                        "4 items",
                        "11:30 AM",
                        "₹500",
                        "Anjali Desai",
                        "+91 987655223",
                        "Accepted"
                    ),
                    OrderHistory(
                        "Table 13",
                        "2 items",
                        "12:00 PM",
                        "₹300",
                        "Manish Gupta",
                        "+91 987655334",
                        "Accepted"
                    )
                )
            )
        )

        val adapter = OrderHistoryAdapter(sampleSections)
        recyclerView.adapter = adapter

    }

    private fun onNavItemClicked(id: Int) {
        when (id) {
            R.id.nav_home -> {
                setSelected(navHome)
                drawerLayout.closeDrawer(GravityCompat.START)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
                // Don't restart HomeActivity if you're already in it
            }

            R.id.nav_order_history -> {
                setSelected(navOrderHistory)
                drawerLayout.closeDrawer(GravityCompat.START)
//                startActivity(Intent(this, OrderHistoryActivity::class.java))
            }

            R.id.nav_profile -> {
                setSelected(navProfile)
                drawerLayout.closeDrawer(GravityCompat.START)
//                startActivity(Intent(this, ProfileActivity::class.java))
            }

            R.id.nav_privacy -> {
                setSelected(navPrivacy)
                drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.nav_terms -> {
                setSelected(navTerms)
                drawerLayout.closeDrawer(GravityCompat.START)
//                startActivity(Intent(this, TermsActivity::class.java))
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
