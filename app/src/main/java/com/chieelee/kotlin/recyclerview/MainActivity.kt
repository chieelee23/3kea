package com.chieelee.kotlin.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chieelee.kotlin.recyclerview.databinding.ItemPersonBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 가짜 데이터
        val people = arrayListOf<Person>()
        for (i in 0..30){
            people.add(Person("홍길동 $i", 10))
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PersonAdapter(people){
                // 파라미터
                Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
                // it = person
                // person ->, person
            }

        }
    }
}

// 데이터
// 네트워크 메소드 추가
// 로컬에서 바로 받기

// onclick java - 임플리먼트, kotlin 함수로 매게변수로 전달해 보자

class PersonAdapter (val items: List<Person>,
                     private val clickListener: (person: Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    class PersonViewHolder(val binding: ItemPersonBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        val viewHolder = PersonViewHolder(ItemPersonBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int =  items.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.person = items[position]
    }
}

