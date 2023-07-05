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
        linkSite1.text = "https://www.themangofactory.com/growing-mangoes/pests-disease/mango-pests-and-diseases/"
        linkSite2.text = "https://www.agrifarming.in/mango-pests-diseases-and-control-methods-a-full-guide"
        linkSite3.text = "https://scholars.direct/Articles/plant-pathology/jppr-4-012.php?jid=plant-pathology"
        linkSite4.text = "https://www.agrifarming.in/mango-farming-in-philippines-how-to-start-varieties-planting-care-and-harvesting"

        // Enable auto-linking of URLs
        Linkify.addLinks(linkSite1, Linkify.WEB_URLS)
        Linkify.addLinks(linkSite2, Linkify.WEB_URLS)
        Linkify.addLinks(linkSite3, Linkify.WEB_URLS)
        Linkify.addLinks(linkSite4, Linkify.WEB_URLS)

        // Handle link clicks (optional)
        linkSite1.setOnClickListener { openUrl("http://www.pcaf.da.gov.ph/wp-content/uploads/2022/06/Philippine-Mango-Industry-Roadmap-2021-2025.pdf") }
        linkSite2.setOnClickListener { openUrl("https://www.agrifarming.in/mango-pests-diseases-and-control-methods-a-full-guide") }
        linkSite3.setOnClickListener { openUrl("https://scholars.direct/Articles/plant-pathology/jppr-4-012.php?jid=plant-pathology") }
        linkSite4.setOnClickListener { openUrl("https://www.agrifarming.in/mango-farming-in-philippines-how-to-start-varieties-planting-care-and-harvesting") }

        return rootView
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}