package jp.ac.asojuku.st.myminislot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    val defaultmycoin=1000
    var mycoin=defaultmycoin
    var betcoin=10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mycoin = intent.getIntExtra("MY_COIN",1000)
        txtv_mycoin.setText(mycoin.toString())
        btn_betup.setOnClickListener { if (mycoin>betcoin){betcoin += 10;txtv_betcoin.setText(betcoin.toString()) }}
        btn_betdown.setOnClickListener { if (betcoin>10){betcoin -= 10;txtv_betcoin.setText(betcoin.toString()) }}
        btn_reset.setOnClickListener { mycoin = 1000
            txtv_mycoin.setText(mycoin.toString()) }
        btn_start.setOnClickListener { onStartButtunTapped(mycoin) }
    }

    fun onStartButtunTapped(mycoin:Int?){
        startActivity<GameActivity>("MY_COIN" to mycoin,"BET_COIN" to betcoin)
    }



}
