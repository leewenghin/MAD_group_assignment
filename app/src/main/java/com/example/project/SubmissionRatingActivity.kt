package com.example.project

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.showAlignTop

class SubmissionRatingActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission_rating)

        fun balloon(tooltipText: String) = createBalloon(baseContext) {
            setArrowSize(10)
//            setWidthRatio(1f)
//            setHeight(250)
            setWidth(BalloonSizeSpec.WRAP) // sets width size depending on the content's size.
            setHeight(BalloonSizeSpec.WRAP) // sets height size depending on the content's size.
            setArrowPosition(0.89f)
            setCornerRadius(8f)
            setArrowAlignAnchorPadding(10)
            setAlpha(0.9f)
            setText(tooltipText)
            setTextGravity(paddingLeft)
            setPadding(15)
            setTextSize(15f)
            setIconSpace(-5)
//            setIconDrawable(ContextCompat.getDrawable(baseContext, R.drawable.warning_sign))
            setIconColor(resources.getColor(R.color.black))
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltipyellow)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)

//            setOnBalloonClickListener {
//                startActivity(Intent(this@SubmissionRatingActivity, SubmissionRatingActivity::class.java))
//            }
        }

        val textArray =
            arrayOf(
                "Laporan proposal (P01, C4(analisis)) : 5%  pengetahuan\n" +
                        "\n" +
                        "Perlu mempunyai elemen berikut:\n" +
                        "•\tPengenalan tentang projek dinyatakan dengan jelas  \n" +
                        "•\tPernyataan masalah dinyatakan dengan jelas\n" +
                        "•\tKajian literatur adalah relevan dengan pernyataan masalah\n" +
                        "•\tObjektif yang hendak dicapai selaras dengan pernyataan masalah\n" +
                        "•\tSkop projek bersesuaian dengan tahap prasiswazah \n" +
                        "•\tJangkaan hasil yang boleh dicapai\n" +
                        "•\tPerancangan projek dirangka dengan tersusun",

                        "Rekabentuk Projek (CPS9-P4(mekanisme)) - 5%  psikomotor",

                        "Konfigurasi persekitaran projek (CPS9-P5(respons ketara kompleks)) – 5%  psikomotor. Pelajar berupaya menunjukkan  kemahiran berikut:\n" +
                                "•\tmelaksanakan sebahagian langkah-langkah pembangunan atau implementasi projek\n" +
                                "•\tmengkonfigurasi atau memanipulasi persekitaran pembangunan dan pelaksanaan projek  \n",

                        "Pemilihan metodologi/teknik/perisian (CSP4-CTPS) CT1(kebolehan mengenalpasti dan menganalisis masalah dalam situasi kompleks dan kabur, serta membuat penilaian yang berjustifikasi) 5%",

                        "0 - Abstrak tiada dalam tesis." +
                        "\n1 - Abstrak merangkumi kurang daripada tiga elemen berikut iaitu pengenalan, pernyataan masalah, objektif, metodologi, hasil kajian dan kesimpulan DAN tidak ditulis menggunakan bahasa penulisan yang baik dalam Bahasa Melayu atau Bahasa Inggeris." +
                        "\n2 - Abstrak merangkumi sekurang-kurangnya tiga elemen berikut iaitu pengenalan, pernyataan masalah, objektif, metodologi, hasil kajian dan kesimpulan DAN ditulis menggunakan bahasa penulisan yang baik dalam Bahasa Melayu dan Bahasa Inggeris." +
                        "\n3 - Abstrak merangkumi enam elemen berikut iaitu pengenalan, pernyataan masalah, objektif, metodologi, hasil kajian dan kesimpulan yang mengambarkan kandungan keseluruhan projek DAN ditulis menggunakan bahasa penulisan yang baik dalam Bahasa Melayu dan Bahasa Inggeris.",

                        "0 - Pendahuluan tiada dalam tesis." +
                        "\n1 - Pendahuluan merangkumi mana-mana satu elemen berikut iaitu pernyataan masalah, objektif, jangkaan hasil projek dan organisasi tesis tetapi tidak berkaitan dengan projek yang dijalankan DAN ditulis menggunakan gaya penulisan yang kurang tersusun." +
                        "\n2 - Pendahuluan merangkumi mana-mana dua elemen berikut iaitu pernyataan masalah, objektif, jangkaan hasil projek dan organisasi tesis yang tidak mengambarkan projek DAN ditulis menggunakan gaya penulisan kurang tersusun." +
                        "\n3 - Pendahuluan merangkumi mana-mana tiga elemen berikut iaitu pernyataan masalah, objektif, jangkaan hasil projek dan organisasi tesis yang mengambarkan projek DAN ditulis menggunakan gaya penulisan tersusun." +
                        "\n4 - Pendahuluan merangkumi semua empat elemen berikut iaitu pernyataan masalah, objektif, jangkaan hasil projek dan organisasi tesis yang mengambarkan projek DAN ditulis menggunakan gaya penulisan tersusun yang menunjukkan kesinambungan antara setiap elemen.",

                        "0 - Kajian literatur tiada dalam tesis." +
                        "\n1 - Kajian literatur yang tidak relevan dan tanpa rumusan." +
                        "\n2 - Kajian literatur yang relevan daripada pelbagai sumber dan tanpa rumusan." +
                        "\n3 - Kajian literatur yang relevan daripada pelbagai sumber dan beserta rumusan ringkas." +
                        "\n4 - Kajian literatur yang relevan daripada pelbagai sumber beserta pandangan dan rumusan secara kritis." +
                        "\n5 - Kajian literatur yang relevan dan terkini daripada pelbagai sumber, beserta pandangan dan rumusan secara kritis.",

                        "0 - Metodologi tiada dalam tesis." +
                        "\n1 - Pemilihan metodologi yang tidak sesuai dan tidak dihuraikan dengan baik." +
                        "\n2 - Pemilihan metodologi yang sesuai tetapi tidak dihuraikan dengan baik." +
                        "\n3 - Pemilihan metodologi yang sesuai serta dihuraikan dengan baik." +
                        "\n4 - Pemilihan metodologi yang sesuai serta dihuraikan dengan baik dan terperinci." +
                        "\n5 - Pemilihan metodologi yang sesuai serta dihuraikan dengan jelas, terperinci dan tersusun yang menunjukkan kesinambungan antara setiap langkah.",

                        "0 - Reka bentuk sistem tiada dalam tesis." +
                        "\n1 - Pemilihan reka bentuk sistem yang tidak sesuai." +
                        "\n2 - Pemilihan reka bentuk sistem yang sesuai tetapi tidak dihuraikan dengan baik." +
                        "\n3 - Pemilihan reka bentuk sistem yang sesuai serta dihuraikan dengan baik." +
                        "\n4 - Pemilihan reka bentuk sistem yang sesuai serta dihuraikan dengan baik, betul dan terperinci." +
                        "\n5 - Pemilihan reka bentuk sistem yang sesuai serta dihuraikan dengan jelas dan terperinci yang digambarkan menggunakan perisian yang bersesuaian seperti CASE tool.",

                        "0 - Perlaksanaan tiada dalam tesis.")

        for (id in 1..14) {
            val tooltip =
                findViewById<ImageView>(resources.getIdentifier("tooltip$id", "id", packageName))
            tooltip.setOnClickListener {
                tooltip.showAlignTop(balloon(textArray[id-1]))
            }
        }


//        val tooltips = arrayOf(R.id.tooltip1, R.id.tooltip2,
//            R.id.tooltip3, R.id.tooltip4,R.id.tooltip5,
//            R.id.tooltip6, R.id.tooltip7, R.id.tooltip8,
//            R.id.tooltip9, R.id.tooltip10,R.id.tooltip11,
//            R.id.tooltip12, R.id.tooltip13, R.id.tooltip14)
//
//        for(i in textArray) {
//            for (id in tooltips){
//                val tooltip = findViewById<ImageView>(id)
//                tooltip.setOnClickListener {
//                    tooltip.showAlignTop(textArray[i])
//                }
//            }
//        }
    }
}