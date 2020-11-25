package com.example.kotlinhw_list

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cubee_list.view.*

data class Item(
        val photo: Int, //圖片
        val name: String //id
)
class  MyAdapter constructor(private val layout: Int, private val data: ArrayList<Item>) : BaseAdapter() { //繼承BaseAdapter
    //回傳資料來源筆數
    override fun getCount() = data.size
    //回傳某筆項目
    override fun getItem(position: Int) = data[position]
    //回傳某筆項目id
    override fun getItemId(position: Int) = 0L
    //取得畫面元件
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = View.inflate(parent?.context, layout, null)
        //根據position把圖片顯示到ImageView
        view.imageView.setImageResource(data[position].photo)
        //根據position把字串顯示到TextView
        view.name.text = data[position].name
        //取得xml畫面
        return view
    }
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //建立字串，並從R類別讀取圖片
        val transNameArray = arrayOf("腳踏車", "機車", "汽車", "巴士", "飛機", "船")
        val item1 = ArrayList<Item>()
        val array1 = resources.obtainTypedArray(R.array.train)
        for (i in 0 until array1.length())
            item1.add(Item(array1.getResourceId(i, 0), "${transNameArray[i]}"))
        array1.recycle()
        //建立字串，並從R類別讀取圖片
        val cubeeNameArray = arrayOf("哭哭", "發抖", "再見", "生氣", "昏倒", "竊笑", "很棒", "你好", "驚嚇", "大笑")
        val item2 = ArrayList<Item>()
        val array2 = resources.obtainTypedArray(R.array.cubee)
        for (i in 0 until array2.length())
            item2.add(Item(array2.getResourceId(i, 0), "${cubeeNameArray[i]}"))
        array2.recycle()
        //連結Adapter，並傳入trans_list作為畫面
        spinner.adapter = MyAdapter(R.layout.trans_list, item1)
        //設定橫向顯示列數
        gridView.numColumns = 3
        //連結Adapter，並傳入cubee_list作為畫面
        gridView.adapter = MyAdapter(R.layout.cubee_list, item2)
        //建立Adapter物件，並放入字串
        listView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                arrayListOf("訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6"))

    }
}