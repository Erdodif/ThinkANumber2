package hu.petrik.thinkanumber

import hu.petrik.thinkanumber.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import android.widget.ImageView
import android.widget.TextView
import android.view.ViewGroup
import kotlin.random.Random
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var egyKocka = false;
    private var kocka1 = 1;
    private var kocka2 = 1;

    fun ujjView(szoveg: String) {
        var view = TextView(this)
        view.setText(szoveg)
        view.setLayoutParams(
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        binding.scrollLayout0.addView(view)
    }

    fun lekovet() {
        if (egyKocka) {
            binding.imageView2Kocka.visibility = View.GONE
        } else {
            binding.imageView2Kocka.visibility = View.VISIBLE
        }
        binding.imageView1Kocka.setImageResource(R.drawable.kocka1)
        binding.imageView2Kocka.setImageResource(R.drawable.kocka1)
    }

    fun kockaKovetes(view: ImageView, szam: Int) {
        when (szam) {
            1 -> view.setImageResource(R.drawable.kocka1)
            2 -> view.setImageResource(R.drawable.kocka2)
            3 -> view.setImageResource(R.drawable.kocka3)
            4 -> view.setImageResource(R.drawable.kocka4)
            5 -> view.setImageResource(R.drawable.kocka5)
            6 -> view.setImageResource(R.drawable.kocka6)
        }
    }

    fun dobas(): List<Int> {
        var randomszam: Int
        var list = LinkedList<Int>()
        if (!egyKocka) {
            randomszam = Random.nextInt(1, 6)
            kockaKovetes(binding.imageView2Kocka, randomszam)
            list.add(randomszam)
        }
        randomszam = Random.nextInt(1, 6)
        kockaKovetes(binding.imageView1Kocka, randomszam)
        list.add(randomszam)
        return list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.button1Kocka.setOnClickListener {
            egyKocka = true
            lekovet()
        }
        binding.button2Kocka.setOnClickListener {
            egyKocka = false
            lekovet()
        }
        binding.buttonDobas.setOnClickListener {
            val list = dobas()
            if (egyKocka) {
                val kiad = "${list.get(0)}"
                ujjView(kiad)
            } else {
                val kiad = "${list.get(1) + list.get(0)} (${list.get(1)}+${list.get(0)})"
                ujjView(kiad)
            }
        }
        binding.buttonReset.setOnClickListener {
            var ask = AlertDialog.Builder(this)
            ask.setTitle("Reset")
            ask.setMessage("Biztos, hogy t??r??lni szeretn?? az eddigi dob??sokat?")
            ask.setNegativeButton("nem") { _, _ -> }
            ask.setPositiveButton("igen") { _, _ ->
                binding.scrollLayout0.removeAllViews()
                lekovet()
            }
            ask.show()
        }
    }
}