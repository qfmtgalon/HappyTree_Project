package com.example.happytree

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class MoreInfoFragment : Fragment() {

    private lateinit var linkSite1: TextView
    private lateinit var linkSite2: TextView
    private lateinit var linkSite3: TextView
    private lateinit var linkSite4: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_more_info, container, false)
        linkSite1 = rootView.findViewById(R.id.linkSite1)
        linkSite2 = rootView.findViewById(R.id.linkSite2)
        linkSite3 = rootView.findViewById(R.id.linkSite3)
        linkSite4 = rootView.findViewById(R.id.linkSite4)

        // Set the text and URLs for each link
        linkSite1.text = "Website 1: https://www.agrifarming.in/mango-pests-diseases-and-control-methods-a-full-guide"
        linkSite2.text = "Website 2: www.example2.com"
        linkSite3.text = "Website 3: www.example3.com"
        linkSite4.text = "Website 4: www.example4.com"

        // Enable auto-linking of URLs
        Linkify.addLinks(linkSite1, Linkify.WEB_URLS)
        Linkify.addLinks(linkSite2, Linkify.WEB_URLS)
        Linkify.addLinks(linkSite3, Linkify.WEB_URLS)
        Linkify.addLinks(linkSite4, Linkify.WEB_URLS)

        // Handle link clicks (optional)
        linkSite1.setOnClickListener { openUrl("https://www.agrifarming.in/mango-pests-diseases-and-control-methods-a-full-guide") }
        linkSite2.setOnClickListener { openUrl("https://www.example2.com") }
        linkSite3.setOnClickListener { openUrl("https://www.example3.com") }
        linkSite4.setOnClickListener { openUrl("https://www.example4.com") }

        return rootView
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}