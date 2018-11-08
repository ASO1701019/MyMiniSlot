package jp.ac.asojuku.st.myminislot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.startActivity

class GameActivity : AppCompatActivity() {

    var slot1num=9
    var slot2num=9
    var slot3num=9

    var slotnum=0

    var slot1id=0
    var slot2id=0
    var slot3id=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    override fun onResume() {
        super.onResume()
        var mycoin = intent.getIntExtra("MY_COIN",0)
        var betcoin = intent.getIntExtra("BET_COIN",0)

        if(mycoin!=0) {
            var result = 0
            btn_stop1.setOnClickListener {
                if (mycoin != 0) {
                    slot1id = slotrandom(); slot1.setImageResource(slot1id);slot1num = slotnum
                    if (slot1num != 9 && slot2num != 9 && slot3num != 9) {
                        result = slotresult(slot1num, slot2num, slot3num)
                        mycoin = mycoin + betcoin * result
                        txtv_mycoin.setText(mycoin.toString())
                    }
                }
            }
            btn_stop2.setOnClickListener {
                if(mycoin!=0) {
                    slot2id = slotrandom(); slot2.setImageResource(slot2id);slot2num = slotnum
                    if (slot1num != 9 && slot2num != 9 && slot3num != 9) {
                        result = slotresult(slot1num, slot2num, slot3num)
                        mycoin = mycoin + betcoin * result
                        txtv_mycoin.setText(mycoin.toString())
                    }
                }
            }
            btn_stop3.setOnClickListener {
                if (mycoin != 0) {
                    slot3id = slotrandom(); slot3.setImageResource(slot3id);slot3num = slotnum
                    if (slot1num != 9 && slot2num != 9 && slot3num != 9) {
                        result = slotresult(slot1num, slot2num, slot3num)
                        mycoin = mycoin + betcoin * result
                        txtv_mycoin.setText(mycoin.toString())
                    }
                }
            }
            txtv_mycoin.setText(mycoin.toString())
            txtv_betcoin.setText(betcoin.toString())
        }

        btn_back.setOnClickListener { startActivity<MainActivity>("MY_COIN" to mycoin,"BET_COIN" to betcoin) }

    }

    private fun slotrandom():Int{
        slotnum = (Math.random()*9).toInt()
        var slotid=0
        when(slotnum){
            0->{slotid=R.drawable.grape}
            1->{slotid=R.drawable.banana}
            2->{slotid=R.drawable.waltermelon}
            3->{slotid=R.drawable.lemon}
            4->{slotid=R.drawable.cherry}
            5->{slotid=R.drawable.orange}
            6->{slotid=R.drawable.bar}
            7->{slotid=R.drawable.bigwin}
            8->{slotid=R.drawable.seven}
//            9->{slotid=R.drawable.question}
        }
        return slotid
    }

    private fun slotresult(slot1num:Int,slot2num:Int,slot3num:Int):Int{
        var rt = -1 //ハズレ
        if(slot1num==slot2num&&slot1num==slot3num){
            //3つ揃い
            if (slot1num==8) {
                //seven
                rt=20
            }else if(slot1num==7){
                //bigwin
                rt=10
            }else if(slot1num==6){
                //bar
                rt=5
            }else{
                //etc
                rt=2
            }
        }else if(slot1num==slot2num||slot2num==slot3num||slot1num==slot3num){
            //2つ揃い
            if(slot1num+slot2num==16||slot2num+slot3num==16||slot1num+slot3num==16){
                //seven
                rt=3
            }else{
                //etc
                rt=1
            }
        }else if(slot1num==2||slot2num==2||slot3num==2){
            //waitermelon1つ
            rt=1
        }
        if(slot1num==5&&slot2num==4&&slot3num==3){
            //左からorange,cherry,lemon
            rt=30
        }else if(slot1num==2&&slot2num==1&&slot3num==0){
            //左からwaltermelon,banana,grape
            rt=10
        }
        return rt
    }
}
