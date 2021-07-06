package c.com.githubrepolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import c.com.githubrepolist.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val lang:String = intent.getStringExtra("lang").toString()

        findViewById<TextView>(R.id.textView).text = "Language: $lang"
    }
}