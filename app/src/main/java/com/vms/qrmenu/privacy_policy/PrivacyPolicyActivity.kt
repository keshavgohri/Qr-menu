package com.vms.qrmenu.privacy_policy

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.vms.qrmenu.R
import com.vms.qrmenu.databinding.ActivityPrivacyPolicyBinding
import com.vms.qrmenu.home.HomeActivity
import com.vms.qrmenu.login.LoginActivity

class PrivacyPolicyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrivacyPolicyBinding
    private lateinit var webPrivacy: WebView
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
        binding = ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webPrivacy = binding.webPrivacy
        drawerLayout = binding.drawerPrivacy
        menuIcon = binding.menuIcon
        navLogout = binding.navLogout

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

        // loading http://www.google.com/ url in the WebView.
        webPrivacy.loadUrl("https://en.wikipedia.org/wiki/Astronaut")

        // this will enable the javascript.
        webPrivacy.settings.javaScriptEnabled = true

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webPrivacy.webViewClient = WebViewClient()

        menuIcon.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        navHome = binding.navHome
        navOrderHistory = binding.navOrderHistory
        navProfile = binding.navProfile
        navPrivacy = binding.navPrivacy
        navTerms = binding.navTerms

        navItems = listOf(navHome, navOrderHistory, navProfile, navPrivacy, navTerms)

        navItems.forEach { it.setOnClickListener { view -> onNavItemClicked(view.id) } }
        setSelected(navPrivacy)

        binding.imgBackArrow.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
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