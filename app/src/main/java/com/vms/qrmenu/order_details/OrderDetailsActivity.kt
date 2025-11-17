package com.vms.qrmenu.order_details

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.vms.qrmenu.R
import com.vms.qrmenu.databinding.ActivityOrderDetailsBinding
import com.vms.qrmenu.login.LoginActivity
import com.vms.qrmenu.privacy_policy.PrivacyPolicyActivity

class OrderDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderDetailsBinding

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var menuIcon: ImageView

    private lateinit var navHome: LinearLayout
    private lateinit var navOrderHistory: LinearLayout
    private lateinit var navProfile: LinearLayout
    private lateinit var navPrivacy: LinearLayout
    private lateinit var navTerms: LinearLayout
    private lateinit var navItems: List<LinearLayout>
    private lateinit var navLogout: LinearLayout
    private lateinit var txtAccept : TextView
    private lateinit var txtDecline : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        menuIcon = binding.menuIcon
        navHome = binding.navHome
        navOrderHistory = binding.navOrderHistory
        navProfile = binding.navProfile
        navPrivacy = binding.navPrivacy
        navTerms = binding.navTerms
        navLogout = binding.navLogout
        txtAccept = binding.txtAccept
        txtDecline = binding.txtDecline

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

        menuIcon.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        binding.imgBackArrow.setOnClickListener {
            finish()
        }

        txtAccept.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_accept_order, null)

            val dialog = AlertDialog.Builder(this, R.style.TransparentAlertDialog)
                .setView(dialogView)
                .setCancelable(false)
                .create()

            dialogView.findViewById<TextView>(R.id.btn_accept).setOnClickListener {
                dialog.dismiss()
                finish()
            }

            dialogView.findViewById<ImageView>(R.id.img_cross).setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

        txtDecline.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_decline_order, null)

            val dialog = AlertDialog.Builder(this, R.style.TransparentAlertDialog)
                .setView(dialogView)
                .setCancelable(false)
                .create()

            dialogView.findViewById<TextView>(R.id.btn_decline).setOnClickListener {
                dialog.dismiss()
                finish()
            }

            dialogView.findViewById<ImageView>(R.id.img_cross).setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    private fun onNavItemClicked(id: Int) {
        when (id) {
            R.id.nav_home -> {
                setSelected(navHome)
                drawerLayout.closeDrawer(GravityCompat.START)
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
                startActivity(Intent(this, PrivacyPolicyActivity::class.java))
                finish()
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
