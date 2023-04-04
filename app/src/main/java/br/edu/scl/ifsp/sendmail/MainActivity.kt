package br.edu.scl.ifsp.sendmail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.scl.ifsp.sendmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //initializing amb
    private val activityMainBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //clearing all inputs on form
        activityMainBinding.cleanBt.setOnClickListener {
            with(activityMainBinding) {
                toEt.setText("")
                ccEt.setText("")
                bccEt.setText("")
                subjectEt.setText("")
                messageEt.setText("")
            }
        }

        //triggering email intent
        activityMainBinding.sendBt.setOnClickListener {
            val sendEmailIntent = Intent(Intent.ACTION_SENDTO) //creating email Intent

            //setting bundle to be added to the email Intent
            with(sendEmailIntent) {
                //recipient
                putExtra(Intent.EXTRA_EMAIL, arrayOf(activityMainBinding.toEt.text.toString()))

                //in copy
                putExtra(Intent.EXTRA_CC, arrayOf(activityMainBinding.ccEt.text.toString()))

                //in hidden copy
                putExtra(Intent.EXTRA_BCC, arrayOf(activityMainBinding.bccEt.text.toString()))

                //subject
                putExtra(Intent.EXTRA_SUBJECT, arrayOf(activityMainBinding.subjectEt.text.toString()))

                //message
                putExtra(Intent.EXTRA_TEXT, arrayOf(activityMainBinding.messageEt.text.toString()))

                //document type / request for comment
                type = "message/rfc822"
                data = Uri.parse("mailto:") //prefix to identify as an email
            }

            //show possible email options to user
            val chooserIntent = Intent(Intent.ACTION_CHOOSER)

            chooserIntent.putExtra(Intent.EXTRA_INTENT, sendEmailIntent) //when the platform is chosen, the form bundle is sent with it

            startActivity(chooserIntent) //starting email activity


        }
    }
}