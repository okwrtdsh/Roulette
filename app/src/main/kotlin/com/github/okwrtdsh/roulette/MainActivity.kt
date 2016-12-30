package com.github.okwrtdsh.roulette

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.bindView
import com.github.okwrtdsh.roulette.model.Pocket
import java.security.SecureRandom

class MainActivity : AppCompatActivity() {

    val recyclerView: RecyclerView by bindView(R.id.pockets)
    val pockets: List<Pocket> = (1..100).map {
        Pocket(
            it,
            when {
                it % 15 == 0 -> "FizzBuzz"
                it % 3 == 0  -> "Fizz"
                it % 5 == 0  -> "Buzz"
                else         -> it.toString()
            },
            SecureRandom().nextInt(100)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
                adapter = PocketRecyclerAdapter(pockets) {
                    smoothScrollToPosition(Int.MAX_VALUE - 1)
                }
            }
            smoothScrollToPosition(Int.MAX_VALUE - 1)
        }
    }
}
