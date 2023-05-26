package com.example.bhakti.Fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bhakti.*
import com.example.bhakti.Activity.*
import com.example.bhakti.Adapter.CharVedAdapter
import com.example.bhakti.ModelClass.CharVedModel
import com.example.bhakti.databinding.FragmentBookBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class BookFragment : Fragment() {

//    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var contentBinding: FragmentBookBinding
    private lateinit var adapter: CharVedAdapter
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book, container, false)

        contentBinding =FragmentBookBinding.bind(view)


        // Doha view

        val myRef = FirebaseDatabase.getInstance().reference.child("slock")
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                contentBinding.doha.text = snapshot.child("doha").value.toString()
                //text animation
                contentBinding.doha.isSelected =true


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        // Bharat ke mandir
        contentBinding.mandirText.setOnClickListener{
            startActivity(Intent(context, MandirActivity::class.java))
        }

        //        Ashtakam
        contentBinding.ashtakamText.setOnClickListener{
            startActivity(Intent(context, AshtakamActivity::class.java))
        }

        //        Ram
        contentBinding.ramLogo.setOnClickListener {

            startActivity(Intent(context, ShreeRam::class.java))
        }


//        Hanuman ji
        contentBinding.hanumanLogo.setOnClickListener {

            startActivity(Intent(context, HanumanJi::class.java))
        }


//        Durga maa

        contentBinding.maaDurgaLogo.setOnClickListener {

            startActivity(Intent(context, MaaDurga::class.java))
        }


//        Bholenath
        contentBinding.bholenathLogo.setOnClickListener {

            startActivity(Intent(context, BholenathActivity::class.java))
        }




//        image slider
        val imageList = ArrayList<SlideModel>()
        FirebaseDatabase.getInstance().reference.child("Slider")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data in snapshot.children)
                        imageList.add(SlideModel(data.child("url").value.toString(),
                            data.child("title").value.toString(),
                            ScaleTypes.FIT))

                    contentBinding.imageSlider.setImageList(imageList, ScaleTypes.FIT)

                    contentBinding.imageSlider.setItemClickListener(object : ItemClickListener {
                        override fun onItemSelected(position: Int) {
                            for(i in 0 until imageList.size ){
                                if(i == 0 && position == 0){

                                    val ref1 = FirebaseDatabase.getInstance().getReference("story")
                                    val ref2 = ref1.child("story3")
                                    ref2.addValueEventListener(object: ValueEventListener{
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val intent = Intent(contentBinding.imageSlider.context, ViewPdf::class.java)
                                            intent.putExtra("textName", snapshot.child("name").value.toString())
                                            intent.putExtra("purl", snapshot.child("purl").value.toString())
                                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                            contentBinding.imageSlider.context.startActivity(intent)

                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })



                                }
                                else if(i == 1 && position == 1){
                                    val ref11 = FirebaseDatabase.getInstance().getReference("story")
                                    val ref22 = ref11.child("bhagwanKiAdbhutRachnaMaa")
                                    ref22.addValueEventListener(object: ValueEventListener{
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val intent = Intent(contentBinding.imageSlider.context, ViewPdf::class.java)
                                            intent.putExtra("textName", snapshot.child("name").value.toString())
                                            intent.putExtra("purl", snapshot.child("purl").value.toString())
                                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                            contentBinding.imageSlider.context.startActivity(intent)

                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })



                                }
                                else if(i == 2 && position == 2){

                                    val ref12 = FirebaseDatabase.getInstance().getReference("story")
                                    val ref22 = ref12.child("story2")
                                    ref22.addValueEventListener(object: ValueEventListener{
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val intent = Intent(contentBinding.imageSlider.context, ViewPdf::class.java)
                                            intent.putExtra("textName", snapshot.child("name").value.toString())
                                            intent.putExtra("purl", snapshot.child("purl").value.toString())
                                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                            contentBinding.imageSlider.context.startActivity(intent)

                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })



                                }
                            }
                        }

                    })

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        // Char ved
        contentBinding.charVedRecycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        contentBinding.charVedRecycleView.itemAnimator = null

        val options: FirebaseRecyclerOptions<CharVedModel> = FirebaseRecyclerOptions.Builder<CharVedModel>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("ved"), CharVedModel::class.java)
            .build()

        adapter = CharVedAdapter(options)
        contentBinding.charVedRecycleView.adapter = adapter



        return view
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


}