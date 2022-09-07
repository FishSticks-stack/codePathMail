package com.example.codepathmail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity()
{
    lateinit var emails: MutableList<Email>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailsRV = findViewById<RecyclerView>(R.id.emailsRV)
        emails = EmailFetcher.getEmails()
        val adapter = emailAdapter(emails)
        emailsRV.adapter = adapter
        emailsRV.layoutManager = LinearLayoutManager(this)

        // load 5 more emails portion
        findViewById<Button>(R.id.loadbt).setOnClickListener { val newEmails = EmailFetcher.getNext5Emails()
            // emails changed to mutable cuz u cannot change regular lists
            emails.addAll(newEmails)
            adapter.notifyDataSetChanged()
        }
    }
}